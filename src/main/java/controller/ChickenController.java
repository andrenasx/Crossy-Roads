package controller;

import model.Chicken;
import model.GameMap;
import model.Position;
import view.Gui;

import java.io.IOException;

public class ChickenController {
    private GameMap map;
    private Gui gui;

    public ChickenController(Gui gui, GameMap map) {
        this.map = map;
        this.gui = gui;
    }

    public void execute() throws IOException {
        Gui.COMMAND command = gui.getNextCommand();

        if(command == Gui.COMMAND.UP)
            moveChicken(map.getChickenPosition().up());
        if(command == Gui.COMMAND.DOWN)
            moveChicken(map.getChickenPosition().down());
        if(command == Gui.COMMAND.LEFT)
            moveChicken(map.getChickenPosition().left());
        if(command == Gui.COMMAND.RIGHT)
            moveChicken(map.getChickenPosition().right());

    }

    public void start(){
        while(!map.isGameFinished()){
            try {
                gui.getNextCommand();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean chickenStaysInScreen(Position position){
        return (position.getX()>=0 && position.getX()<map.getWidth() && position.getY()>=0 && position.getY()<map.getHeight());
    }

    public void moveChicken(Position position){
        Chicken chicken = map.getChicken();
        if (chickenStaysInScreen(position))
            chicken.setPosition(position);
        map.notifyObservers();
    }
}
