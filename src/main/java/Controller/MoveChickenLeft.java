package Controller;

import Model.GameMap;
import Model.Position;

public class MoveChickenLeft extends Command {
    private GameMap map;

    public MoveChickenLeft(GameMap map) {
        this.map = map;
    }

    @Override
    public void execute() {
        Position position = map.getChickenPosition().left();
        map.moveChicken(position);
    }
}
