package entity;

public class CommonPlayerFactory implements PlayerFactory {

    @Override
    public Player create(String tag, String info) {
        return new CommonPlayer(tag, info);
    }
}
