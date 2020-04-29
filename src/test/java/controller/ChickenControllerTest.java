package controller;

import model.GameMap;
import model.Position;
import org.junit.Test;
import view.Gui;

import java.io.IOException;

import static org.junit.Assert.*;

public class ChickenControllerTest {
    @Test
    public void chickenStaysInScreenTest() throws IOException {
        GameMap map = new GameMap(40, 35);
        Gui gui = new Gui(map);
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
    public void moveChickenTest() throws IOException {
        GameMap map = new GameMap(40, 35);
        Gui gui = new Gui(map);
        ChickenController chickenController = new ChickenController(gui, map);
        Position position1 = new Position(10, 20);
        Position position2 = new Position(50, 15);
        chickenController.moveChicken(position1);
        assertEquals(map.getChickenPosition(), position1);
        Position position = map.getChickenPosition();
        chickenController.moveChicken(position2);
        assertEquals(map.getChickenPosition(), position);

    }

    @Test
    public void executeTest() throws IOException {
        GameMap map = new GameMap(40, 35);
        Gui gui = new Gui(map);
        ChickenController chickenController = new ChickenController(gui, map);
        Position position1 = map.getChickenPosition();
        chickenController.execute(Gui.COMMAND.UP);
        assertEquals(map.getChickenPosition(), new Position(position1.getX(), position1.getY()-1));
        Position position2 = map.getChickenPosition();
        chickenController.execute(Gui.COMMAND.DOWN);
        assertEquals(map.getChickenPosition(), new Position(position2.getX(), position2.getY()+1));
        Position position3 = map.getChickenPosition();
        chickenController.execute(Gui.COMMAND.RIGHT);
        assertEquals(map.getChickenPosition(), new Position(position3.getX()+1, position3.getY()));
        Position position4 = map.getChickenPosition();
        chickenController.execute(Gui.COMMAND.LEFT);
        assertEquals(map.getChickenPosition(), new Position(position4.getX()-1, position4.getY()));

    }
}
