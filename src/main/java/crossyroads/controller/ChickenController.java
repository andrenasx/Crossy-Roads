package crossyroads.controller;

import crossyroads.model.*;
import crossyroads.view.Gui;

import java.io.IOException;
import java.util.List;

public class ChickenController {
    private GameMap map;

    public ChickenController(GameMap map) {
        this.map = map;
    }

    public void execute(Gui.COMMAND command) {
        Chicken chicken = map.getChicken();
        if(command == Gui.COMMAND.UP)
            moveChicken(chicken.getPosition().up());
        if(command == Gui.COMMAND.DOWN)
            moveChicken(chicken.getPosition().down());
        if(command == Gui.COMMAND.LEFT)
            moveChicken(chicken.getPosition().left());
        if(command == Gui.COMMAND.RIGHT)
            moveChicken(chicken.getPosition().right());

    }

    public void start(Gui.COMMAND command){
        execute(command);
    }

    public boolean chickenStaysInScreen(Position position){
        return (position.getX()>=0 && position.getX()<map.getWidth() && position.getY()>=0 && position.getY()<map.getHeight());
    }

    public void moveChicken(Position position){
        Chicken chicken = map.getChicken();
        if (chickenStaysInScreen(position))
            chicken.setPosition(position);
        checkCollisions(position);
        map.notifyObservers();
    }

    public void checkCollisions(Position position) {
        checkVehicleCollision(position);

        Coin coin = (Coin) getCollidingElement(position, map.getCoins());
        if (coin != null) {
            map.getChicken().raiseScore(coin.getValue());
            map.getCoins().remove(coin);
        }
    }

    public void checkVehicleCollision(Position position){
        for (Vehicle vehicle: map.getVehicles()){
            if (vehicle.checkCollision(position)){
                map.getChicken().removeLife();
                map.resetChickenPosition();
                break;
            }
        }
    }

    private Element getCollidingElement(Position position, List<? extends Element> elements) {
        for (Element element : elements)
            if (element.getPosition().equals(position))
                return element;

        return null;
    }
}
