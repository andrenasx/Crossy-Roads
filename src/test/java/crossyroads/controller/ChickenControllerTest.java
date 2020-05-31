package crossyroads.controller;

import crossyroads.model.*;
import crossyroads.view.GuiGame;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ChickenControllerTest {
    GameModel gameModel;
    ChickenController chickenController;

    @Before
    public void init() {
        gameModel = Mockito.mock(GameModel.class);
        Mockito.when(gameModel.getWidth()).thenReturn(40);
        Mockito.when(gameModel.getHeight()).thenReturn(35);
        Mockito.when(gameModel.getChicken()).thenReturn(new Chicken(1,2));

        chickenController = new ChickenController(gameModel);
    }

    @Test
    public void chickenStaysInScreenTest(){
        Position position1 = new Position(1, 2);
        Position position2 = new Position(46, -30);
        Position position3 = new Position(35, 50);
        Position position4 = new Position(39, 34);
        assertTrue(chickenController.chickenStaysInScreen(position1));
        assertFalse(chickenController.chickenStaysInScreen(position2));
        assertFalse(chickenController.chickenStaysInScreen(position3));
        assertTrue(chickenController.chickenStaysInScreen(position4));
    }

    @Test
    public void moveChickenTest(){
        Mockito.when(gameModel.getCurrentLevel()).thenReturn(Mockito.mock(Level.class));

        Chicken chicken = gameModel.getChicken();
        Position position1 = new Position(10, 20);
        Position position2 = new Position(50, 15);
        chickenController.moveChicken(position1);
        assertEquals(chicken.getPosition(), position1);
        Position position = chicken.getPosition();
        chickenController.moveChicken(position2);
        assertEquals(chicken.getPosition(), position);
    }

    @Test
    public void UpTest() {
        Mockito.when(gameModel.getCurrentLevel()).thenReturn(Mockito.mock(Level.class));

        Chicken chicken = gameModel.getChicken();
        Position position = chicken.getPosition();
        chickenController.execute(GuiGame.COMMAND.UP);
        assertEquals(chicken.getPosition(), new Position(position.getX(), position.getY()-1));
        chicken.setPosition(new Position(chicken.getPosition().getX(), 0));
        position = chicken.getPosition();
        chickenController.execute(GuiGame.COMMAND.UP);
        assertEquals(chicken.getPosition(), position);
    }

    @Test
    public void DownTest() {
        Mockito.when(gameModel.getCurrentLevel()).thenReturn(Mockito.mock(Level.class));

        Chicken chicken = gameModel.getChicken();
        Position position = chicken.getPosition();
        chickenController.execute(GuiGame.COMMAND.DOWN);
        assertEquals(chicken.getPosition(), new Position(position.getX(), position.getY()+1));
        chicken.setPosition(new Position(chicken.getPosition().getX(), 34));
        position = chicken.getPosition();
        chickenController.execute(GuiGame.COMMAND.DOWN);
        assertEquals(chicken.getPosition(), position);
    }

    @Test
    public void LeftTest() {
        Mockito.when(gameModel.getCurrentLevel()).thenReturn(Mockito.mock(Level.class));

        Chicken chicken = gameModel.getChicken();
        Position position = chicken.getPosition();
        chickenController.execute(GuiGame.COMMAND.LEFT);
        assertEquals(chicken.getPosition(), new Position(position.getX()-1, position.getY()));
        chicken.setPosition(new Position(0, chicken.getPosition().getY()));
        position = chicken.getPosition();
        chickenController.execute(GuiGame.COMMAND.LEFT);
        assertEquals(chicken.getPosition(), position);
    }

    @Test
    public void RightTest() {
        Mockito.when(gameModel.getCurrentLevel()).thenReturn(Mockito.mock(Level.class));

        Chicken chicken = gameModel.getChicken();
        Position position = chicken.getPosition();
        chickenController.execute(GuiGame.COMMAND.RIGHT);
        assertEquals(chicken.getPosition(), new Position(position.getX()+1, position.getY()));
        chicken.setPosition(new Position(gameModel.getWidth(), chicken.getPosition().getY()));
        position = chicken.getPosition();
        chickenController.execute(GuiGame.COMMAND.RIGHT);
        assertEquals(chicken.getPosition(), position);
    }

    @Test
    public void checkVehicleCollisionTest(){
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Truck(1, 2 ,"left"));
        Level level = Mockito.mock(Level.class);
        Mockito.when(level.getVehicles()).thenReturn(vehicles);

        Mockito.when(gameModel.getCurrentLevel()).thenReturn(level);

        Chicken chicken = gameModel.getChicken();
        int life = chicken.getLives();
        chickenController.checkVehicleCollision(chicken.getPosition());
        assertEquals(life - 1, chicken.getLives());
    }

   @Test
    public void checkCoinCollisionTest(){
        List<Coin> coins = new ArrayList<>();
        Coin coin = new Coin(1, 2);
        coins.add(coin);
        Level level = Mockito.mock(Level.class);
        Mockito.when(level.getCoins()).thenReturn(coins);

        Mockito.when(gameModel.getCurrentLevel()).thenReturn(level);

        Chicken chicken = gameModel.getChicken();
        int score = chicken.getScore();
        chickenController.checkCollisions(chicken.getPosition());
        assertEquals(score+ 1, chicken.getScore());
        assertTrue(gameModel.getCurrentLevel().getCoins().isEmpty());

    }

    @Test
    public void startTest(){
        chickenController.start(GuiGame.COMMAND.NOTHING);
        Mockito.verify(gameModel, Mockito.times(1)).getChicken();
    }
}
