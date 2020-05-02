package crossyroads.controller;

import com.googlecode.lanterna.screen.TerminalScreen;
import crossyroads.model.*;
import org.junit.Test;
import crossyroads.view.Gui;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.*;

public class ChickenControllerTest {
    @Test
    public void chickenStaysInScreenTest(){
        GameMap map = new GameMap(40, 35);
        Gui gui = Mockito.mock(Gui.class);
        ChickenController chickenController = new ChickenController(gui, map);
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
        GameMap map = new GameMap(40, 35);
        Gui gui = Mockito.mock(Gui.class);
        ChickenController chickenController = new ChickenController(gui, map);
        Chicken chicken = map.getChicken();
        Position position1 = new Position(10, 20);
        Position position2 = new Position(50, 15);
        chickenController.moveChicken(position1);
        assertEquals(chicken.getPosition(), position1);
        Position position = chicken.getPosition();
        chickenController.moveChicken(position2);
        assertEquals(chicken.getPosition(), position);
    }

    @Test
    public void UpTest() throws IOException {
        GameMap map = new GameMap(40, 35);
        Gui gui = Mockito.mock(Gui.class);
        ChickenController chickenController = new ChickenController(gui, map);
        Chicken chicken = map.getChicken();
        Position position1 = chicken.getPosition();
        Mockito.when(gui.getNextCommand()).thenReturn(Gui.COMMAND.UP);
        chickenController.execute();
        assertEquals(chicken.getPosition(), new Position(position1.getX(), position1.getY()-1));
        chicken.setPosition(new Position(chicken.getPosition().getX(), 0));
        Position position2 = chicken.getPosition();
        Mockito.when(gui.getNextCommand()).thenReturn(Gui.COMMAND.UP);
        chickenController.execute();
        assertEquals(chicken.getPosition(), position2);
    }

    @Test
    public void DownTest() throws IOException {
        GameMap map = new GameMap(40, 35);
        Gui gui = Mockito.mock(Gui.class);
        ChickenController chickenController = new ChickenController(gui, map);
        Chicken chicken = map.getChicken();
        Position position1 = chicken.getPosition();
        Mockito.when(gui.getNextCommand()).thenReturn(Gui.COMMAND.DOWN);
        chickenController.execute();
        assertEquals(chicken.getPosition(), position1);
        chicken.setPosition(new Position(chicken.getPosition().getX(), chicken.getPosition().getY()-4));
        Position position2 = chicken.getPosition();
        Mockito.when(gui.getNextCommand()).thenReturn(Gui.COMMAND.DOWN);
        chickenController.execute();
        assertEquals(chicken.getPosition(), new Position(position2.getX(), position2.getY()+1));
    }

    @Test
    public void LeftTest() throws IOException {
        GameMap map = new GameMap(40, 35);
        Gui gui = Mockito.mock(Gui.class);
        ChickenController chickenController = new ChickenController(gui, map);
        Chicken chicken = map.getChicken();
        Position position1 = chicken.getPosition();
        Mockito.when(gui.getNextCommand()).thenReturn(Gui.COMMAND.LEFT);
        chickenController.execute();
        assertEquals(chicken.getPosition(), new Position(position1.getX()-1, position1.getY()));
        chicken.setPosition(new Position(0, chicken.getPosition().getY()));
        Position position2 = chicken.getPosition();
        Mockito.when(gui.getNextCommand()).thenReturn(Gui.COMMAND.LEFT);
        chickenController.execute();
        assertEquals(chicken.getPosition(), position2);
    }

    @Test
    public void RightTest() throws IOException {
        GameMap map = new GameMap(40, 35);
        Gui gui = Mockito.mock(Gui.class);
        ChickenController chickenController = new ChickenController(gui, map);
        Chicken chicken = map.getChicken();
        Position position1 = chicken.getPosition();
        Mockito.when(gui.getNextCommand()).thenReturn(Gui.COMMAND.RIGHT);
        chickenController.execute();
        assertEquals(chicken.getPosition(), new Position(position1.getX()+1, position1.getY()));
        chicken.setPosition(new Position(map.getWidth(), chicken.getPosition().getY()));
        Position position2 = chicken.getPosition();
        Mockito.when(gui.getNextCommand()).thenReturn(Gui.COMMAND.RIGHT);
        chickenController.execute();
        assertEquals(chicken.getPosition(), position2);
    }

    @Test
    public void checkVehicleCollisionTest(){
        GameMap map = new GameMap(35, 40);
        Gui gui = Mockito.mock(Gui.class);
        ChickenController chickenController = new ChickenController(gui, map);
        Chicken chicken = map.getChicken();
        chicken.setPosition(new Position(1, 2));
        int life = chicken.getLives();
        Truck truck = new Truck(1, 2 ,"left");
        map.addElement(truck);
        chickenController.checkVehicleCollision(chicken.getPosition());
        assertEquals(life - 1, chicken.getLives());
        assertEquals(chicken.getPosition(), new Position(map.getWidth()/2, map.getHeight()-1));
    }



   @Test
    public void checkCollisionTest(){
        GameMap map = new GameMap(35, 40);
        Gui gui = Mockito.mock(Gui.class);
        ChickenController chickenController = new ChickenController(gui, map);
        Chicken chicken = map.getChicken();
        Coin coin = new Coin(1, 2, 3);
        map.addElement(coin);
        chicken.setPosition(new Position(1, 2));
        int score = chicken.getScore();
        chickenController.checkCollisions(chicken.getPosition());
        assertEquals(score+coin.getValue(), chicken.getScore());
        assertTrue(map.getCoins().isEmpty());

    }

    @Test
    public void startTest(){
        ChickenController chickenController = Mockito.mock(ChickenController.class);
        chickenController.start();
        Mockito.verify(chickenController, Mockito.times(1)).start();
    }
}
