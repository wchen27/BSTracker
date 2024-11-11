package interface_adapter.user_lookup;

public class UserLookupState {
    private String username;

    private int trophyCount;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTrophyCount() {
        return trophyCount;
    }

    public void setTrophyCount(int trophyCount) {
        this.trophyCount = trophyCount;
    }
}
