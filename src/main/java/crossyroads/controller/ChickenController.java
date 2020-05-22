package crossyroads.controller;

import crossyroads.model.*;
import crossyroads.view.Gui;
import crossyroads.view.GuiSquare;

import java.util.List;

public class ChickenController {
    private GameModel gameModel;

    public ChickenController(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void execute(GuiSquare.COMMAND command) {
        Chicken chicken = gameModel.getChicken();
        if(command == GuiSquare.COMMAND.UP)
            moveChicken(chicken.getPosition().up());
        if(command == GuiSquare.COMMAND.DOWN)
            moveChicken(chicken.getPosition().down());
        if(command == GuiSquare.COMMAND.LEFT)
            moveChicken(chicken.getPosition().left());
        if(command == GuiSquare.COMMAND.RIGHT)
            moveChicken(chicken.getPosition().right());

    }

    public void start(GuiSquare.COMMAND command){
        execute(command);
    }

    public boolean chickenStaysInScreen(Position position){
        return (position.getX()>=0 && position.getX()<gameModel.getWidth() && position.getY()>=0 && position.getY()<gameModel.getHeight());
    }

    public void moveChicken(Position position){
        Chicken chicken = gameModel.getChicken();
        if (chickenStaysInScreen(position))
            chicken.setPosition(position);
        checkCollisions(position);
        chicken.increaseCount();
    }

    public void checkCollisions(Position position) {
        checkVehicleCollision(position);

        Coin coin = (Coin) getCollidingElement(position, gameModel.getCurrentLevel().getCoins());
        if (coin != null) {
            gameModel.getChicken().raiseScore(coin.getValue());
            gameModel.getCurrentLevel().getCoins().remove(coin);
        }
    }

    public void checkVehicleCollision(Position position){
        for (Vehicle vehicle: gameModel.getCurrentLevel().getVehicles()){
            if (vehicle.checkCollision(position)){
                gameModel.getChicken().removeLife();
                gameModel.resetChickenPosition();
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
