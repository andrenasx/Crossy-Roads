package crossyroads.controller;

import crossyroads.model.*;
import crossyroads.view.GuiGame;

import java.util.List;

public class ChickenController {
    private GameModel gameModel;

    public ChickenController(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void execute(GuiGame.COMMAND command) {
        Chicken chicken = gameModel.getChicken();
        if(command == GuiGame.COMMAND.UP)
            moveChicken(chicken.getPosition().up());
        else if(command == GuiGame.COMMAND.DOWN)
            moveChicken(chicken.getPosition().down());
        else if(command == GuiGame.COMMAND.LEFT)
            moveChicken(chicken.getPosition().left());
        else if(command == GuiGame.COMMAND.RIGHT)
            moveChicken(chicken.getPosition().right());
    }

    public void start(GuiGame.COMMAND command){
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
        checkCoinCollision(position);
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

    public void checkCoinCollision(Position position){
        Coin coin = (Coin) getCollidingElement(position, gameModel.getCurrentLevel().getCoins());
        if (coin != null) {
            gameModel.getChicken().raiseScore(1);
            gameModel.getCurrentLevel().getCoins().remove(coin);
        }
    }

    private Element getCollidingElement(Position position, List<? extends Element> elements) {
        for (Element element : elements)
            if (element.getPosition().equals(position))
                return element;

        return null;
    }
}
