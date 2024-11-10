package entity;

public class UserFactory {
    @Override
    public User create(String username, int trophies) {
        return new User(username, trophies);
    }
}