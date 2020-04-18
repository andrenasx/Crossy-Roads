package Controller;

import Model.GameMap;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class QuitCommand extends Command {
    private GameMap map;
    private Screen screen;

    public QuitCommand(GameMap map, Screen screen) {
        this.map = map;
        this.screen = screen;
    }

    @Override
    public void execute() {
        map.gameFinish();
        try {
            screen.close();
        } catch (IOException e){
            //nothing to do
        }
    }
}
