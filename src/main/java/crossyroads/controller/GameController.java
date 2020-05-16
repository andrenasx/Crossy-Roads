package crossyroads.controller;

import crossyroads.model.GameModel;
import crossyroads.model.MusicPlayer;
import crossyroads.view.Gui;

import java.io.IOException;

public class GameController {
    private ChickenController chickenController;
    private VehicleController vehicleController;
    private Gui gui;
    private GameModel gameModel;
    private final int FPS = 10;
    private int step = 0;

    public GameController(Gui gui, GameModel gameModel) {
        this.gui = gui;
        this.gameModel = gameModel;

        this.chickenController = new ChickenController(gameModel.getCurrentLevel());
        this.vehicleController = new VehicleController(gameModel.getCurrentLevel());
    }

    public void start() throws IOException {
        MusicPlayer player = new MusicPlayer("src/main/resources/piu.wav");
        //player.startMusic();

        while(!gameModel.getCurrentLevel().isChickenDead()) {
            long time = System.currentTimeMillis();
            step++; 
          
            Gui.COMMAND command = gui.getNextCommand();
            if(command == Gui.COMMAND.EOF) break;

            chickenController.start(command);
            vehicleController.start(step);
            gui.draw();

            long elapsed = System.currentTimeMillis() - time;

            try {
                Thread.sleep(1000 / FPS - elapsed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println("score: "+ gameModel.getCurrentLevel().getScore());
            //System.out.println("life: " + gameModel.getCurrentLevel().getLives());
            if(gameModel.getCurrentLevel().isLevelFinished())
                gameModel.increaseLevel();
        }

        player.stopMusic();
        System.exit(0);
    }

}

