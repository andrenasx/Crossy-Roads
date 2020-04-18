package Controller;

import Model.GameMap;
import Model.Position;

public class MoveChickenUp extends Command {
    private GameMap map;

    public MoveChickenUp(GameMap map) {
        this.map = map;
    }

    @Override
    public void execute() {
        Position position = map.getChickenPosition().up();
        map.moveChicken(position);
    }
}
