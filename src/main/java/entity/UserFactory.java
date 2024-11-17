package entity;

public class UserFactory {
    public User create(String username, int trophies, String tag) {
        return new User(username, trophies, tag);
    }
}