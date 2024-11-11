package entity;

public class UserFactory {
    public User create(String username, int trophies) {
        return new User(username, trophies);
    }
}