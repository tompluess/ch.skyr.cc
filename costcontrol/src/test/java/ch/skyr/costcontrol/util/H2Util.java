package ch.skyr.costcontrol.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
public class H2Util {
    private static final boolean startWebConsoleAlways = false;

    public static void mayStartWebConsole(final EntityManagerFactory emf, final boolean startWebConsole) throws SQLException {
        if (startWebConsoleAlways || startWebConsole) {
            final Connection conn =
                DriverManager
                    .getConnection(getEmfProperty(emf, "hibernate.connection.url"), getEmfProperty(emf, "hibernate.connection.username"), getEmfProperty(emf, "hibernate.connection.password"));
            org.h2.tools.Server.startWebServer(conn);
        }
    }

    private static String getEmfProperty(final EntityManagerFactory emf, final String key) {
        return (String) emf.getProperties().get(key);
    }
}
