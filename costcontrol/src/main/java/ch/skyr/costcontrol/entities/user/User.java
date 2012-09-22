package ch.skyr.costcontrol.entities.user;

public class User {
    private String userId;
    private String nickname;

    public User(final String userId, final String nickname) {
        super();
        this.userId = userId;
        this.nickname = nickname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }
}
