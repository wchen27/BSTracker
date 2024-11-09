package entity;

public class CommonPlayer implements Player {

    private final String playerTag;
    private final String playerInfo;

    public CommonPlayer(String playerTag, String playerInfo) {
        this.playerTag = playerTag;
        this.playerInfo = playerInfo;
    }

    @Override
    public String getTag() {
        return playerTag;
    }

    @Override
    public String getInfo() {
        return playerInfo;
    }

}
