package Controller;

import Model.GameMap;
import View.Gui;


import java.io.IOException;

public class GameController {
    private Gui gui;
    private GameMap map;

    public GameController(Gui gui, GameMap map) {
        this.gui = gui;
        this.map = map;
    }

    public void execute() throws IOException {
            Gui.COMMAND command = gui.getNextCommand();

            if(command == Gui.COMMAND.UP)
                map.moveChicken(map.getChickenPosition().up());
            if(command == Gui.COMMAND.DOWN)
                map.moveChicken(map.getChickenPosition().down());
            if(command == Gui.COMMAND.LEFT)
                map.moveChicken(map.getChickenPosition().left());
            if(command == Gui.COMMAND.RIGHT)
                map.moveChicken(map.getChickenPosition().right());

            }

    }

