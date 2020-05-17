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

        this.chickenController = new ChickenController(gameModel);
        this.vehicleController = new VehicleController(gameModel);
    }

    public void start() throws IOException {
        MusicPlayer player = new MusicPlayer("src/main/resources/piu.wav");
        //player.startMusic();

        while(!gameModel.isChickenDead()) {
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

            int score = gameModel.getScore();
            int lives = gameModel.getLives();
            if(gameModel.isLevelFinished()){
                if(gameModel.isFinalLevel()){
                    break;
                }
                gameModel.increaseLevel();
                gameModel.resetChickenPosition();
                step = 0;
            }


        }

        player.stopMusic();
        System.exit(0);
    }

}

