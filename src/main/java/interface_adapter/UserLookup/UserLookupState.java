package interface_adapter.UserLookup;

public class UserLookupState {

    private String username;

    private int trophyCount;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTrophyCount(int trophyCount) {
        this.trophyCount = trophyCount;
    }

    public String getUsername() {
        return this.username;
    }

    public int getTrophyCount() {
        return trophyCount;
    }
}
