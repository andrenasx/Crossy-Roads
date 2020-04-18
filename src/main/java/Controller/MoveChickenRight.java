package Controller;

import Model.GameMap;
import Model.Position;

public class MoveChickenRight extends Command {
    private GameMap map;

    public MoveChickenRight(GameMap map) {
        this.map = map;
    }

    @Override
    public void execute() {
        Position position = map.getChickenPosition().right();
        map.moveChicken(position);
    }
}
