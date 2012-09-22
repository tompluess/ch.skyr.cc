package ch.skyr.costcontrol.entities.user;

import java.io.Serializable;

import ch.skyr.costcontrol.annotations.Model;
import ch.skyr.costcontrol.annotations.ModelKey;
import ch.skyr.costcontrol.annotations.Persistent;
@Model(name = "profile")
public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Persistent
    @ModelKey
    private String userId;
    @Persistent
    private String name;
    @Persistent
    private String firstname;
    @Persistent
    private String profession;
    @Persistent
    private String address;
    @Persistent
    private String description;

    public Profile() {
        super();
    }

    public Profile(final String userId) {
        this.userId = userId;
    }

    public String getProfession() {
        return profession;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public void setProfession(final String profession) {
        this.profession = profession;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String location) {
        this.address = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getLabelName() {
        return String.format("%s %s", getFirstname(), getName());
    }

    @Override
    public String toString() {
        return "Profile [userId=" + userId + ", name=" + name + ", firstname=" + firstname + "]";
    }
}
