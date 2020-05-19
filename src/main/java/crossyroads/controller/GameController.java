package crossyroads.controller;

import crossyroads.model.GameModel;
import crossyroads.model.MusicPlayer;
import crossyroads.view.Gui;
import crossyroads.view.GuiSquare;

import java.io.IOException;

public class GameController {
    private ChickenController chickenController;
    private VehicleController vehicleController;
    private GuiSquare gui;
    private GameModel gameModel;
    private final int FPS = 10;
    private int step = 0;

    public GameController(GuiSquare gui, GameModel gameModel) {
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
          
            GuiSquare.COMMAND command = gui.getNextCommand();
            if(command == GuiSquare.COMMAND.EOF) break;
            if(command != GuiSquare.COMMAND.NOTHING)
                gameModel.getChicken().increaseCount();

            chickenController.start(command);
            vehicleController.start(step);
            gui.draw();

            long elapsed = System.currentTimeMillis() - time;

            try {
                Thread.sleep(1000 / FPS - elapsed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(gameModel.isLevelFinished()){
                if(gameModel.isFinalLevel()){
                    break;
                }
                gameModel.increaseLevel();
                gameModel.resetChickenPosition();
                step = 0;
            }


        }
        //System.out.println("Number of commands: " + gameModel.getChicken().getCountSteps());
        player.stopMusic();
        System.exit(0);
    }

}

