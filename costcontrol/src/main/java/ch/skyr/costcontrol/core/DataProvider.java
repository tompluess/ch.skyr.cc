package ch.skyr.costcontrol.core;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ch.skyr.costcontrol.entities.Account;
import ch.skyr.costcontrol.entities.Account.AccountActive;
import ch.skyr.costcontrol.entities.Position;
public class DataProvider {
    public static final String PERSISTENCE_UNIT_NAME = "costcontrol";
    private final EntityManager entityManager;

    public DataProvider() {
        this.entityManager = getEntityManager(PERSISTENCE_UNIT_NAME);
    }

    private EntityManager getEntityManager(final String persistanceUnitName) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistanceUnitName);
        return entityManagerFactory.createEntityManager();
    }

    public List<Account> loadAccounts() {
        //        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        //        final CriteriaQuery<Account> accountCriteriaQuery = criteriaBuilder.createQuery(Account.class);
        //        final Root<Account> accountRoot = accountCriteriaQuery.from(Account.class);
        //        final Predicate condition = criteriaBuilder.equal(accountRoot.get(Account_.active), AccountActive.ACTIVE);
        //        accountCriteriaQuery.where(condition);
        //        final TypedQuery<Account> accountQuery = entityManager.createQuery(accountCriteriaQuery);
        final TypedQuery<Account> accountQuery = entityManager.createQuery("from " + Account.class.getSimpleName() + " where active = " + AccountActive.ACTIVE, Account.class);
        return accountQuery.getResultList();
    }

    public void storeAccount(final Account account) {
        //        account.setId(accountPk);
        //        account.setName(accountName);
        //        account.setType(accountType);
        //        account.setActive(accountActive);
        entityManager.getTransaction().begin();
        entityManager.merge(account);
        entityManager.getTransaction().commit();
    }

    public Position loadPosition(final Long positionId) {
        return entityManager.find(Position.class, positionId);
    }

    public List<Position> loadPositions() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Position> accountCriteriaQuery = criteriaBuilder.createQuery(Position.class);
        final Root<Position> accountRoot = accountCriteriaQuery.from(Position.class);
        //        Predicate condition = criteriaBuilder.equal(accountRoot.get(Position_.active), AccountActive.ACTIVE);
        //        accountCriteriaQuery.where(condition);
        final TypedQuery<Position> accountQuery = entityManager.createQuery(accountCriteriaQuery);
        return accountQuery.getResultList();
    }

    public void storePosition(final Position position) {
        entityManager.getTransaction().begin();
        entityManager.merge(position);
        entityManager.getTransaction().commit();
    }
}
