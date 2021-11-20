package proxet.tournament.generator.dto;

public class PlayerInQueue extends Player {
    private final int timeInQueue;

    public PlayerInQueue(String nickname, int vehicleType) {
        super(nickname, vehicleType);
        this.timeInQueue = 0;
    }

    public PlayerInQueue(String nickname, int timeInQueue, int vehicleType) {
        super(nickname, vehicleType);
        this.timeInQueue = timeInQueue;
    }
    public PlayerInQueue(String[] data) {
        super(data[0], Integer.parseInt(data[2]));
        this.timeInQueue = Integer.parseInt(data[1]);
    }

    public int getTimeInQueue() {
        return timeInQueue;
    }

    public Player getPlayer() {
        return new Player(getNickname(), getVehicleType());
    }
}
