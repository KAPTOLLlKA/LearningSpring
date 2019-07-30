package demo.data.user;

public class Subscription {
    private int userId;
    private int subId;

    public Subscription() {
    }

    public Subscription(int userId, int subId) {
        this.userId = userId;
        this.subId = subId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }
}
