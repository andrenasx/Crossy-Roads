package Controller;

import Model.GameMap;
import Model.Position;

public class MoveChickenDown extends Command {
    private GameMap map;

    public MoveChickenDown(GameMap map) {
        this.map = map;
    }

    @Override
    public void execute() {
        Position position = map.getChickenPosition().down();
        map.moveChicken(position);
    }
}
