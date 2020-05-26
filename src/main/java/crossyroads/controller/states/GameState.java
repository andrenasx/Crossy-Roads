package crossyroads.controller.states;

import crossyroads.controller.AppController;
import crossyroads.controller.ChickenController;
import crossyroads.controller.VehicleController;
import crossyroads.model.GameModel;
import crossyroads.model.GameModelCreator;
import crossyroads.model.MusicPlayer;
import crossyroads.view.GuiGame;
import crossyroads.view.ScreenFactory;

import java.io.IOException;

public class GameState implements State{
    private AppController appController;
    private ChickenController chickenController;
    private VehicleController vehicleController;
    private GuiGame gui;
    private GameModel gameModel;
    private final int FPS = 10;
    private int step = 0;

    public GameState(AppController ap) {
        appController  = ap;
        gameModel = new GameModelCreator().createGameModel(40, 35, 5);


        try {
            gui = new GuiGame(gameModel, ScreenFactory.getScreen());
            gui.draw();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.chickenController = new ChickenController(gameModel);
        this.vehicleController = new VehicleController(gameModel);
    }

    public void step() throws IOException {
        MusicPlayer player = new MusicPlayer("src/main/resources/piu.wav");
        //player.startMusic();

        while(!gameModel.isChickenDead()) {
            long time = System.currentTimeMillis();
            step++;

            GuiGame.COMMAND command = gui.getNextCommand();

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
                if(gameModel.isFinalLevel())
                    break;
                gameModel.increaseLevel();
                gameModel.resetChickenPosition();
                step = 0;
            }
        }
        player.stopMusic();
        if(gameModel.isChickenDead())
            appController.setCurrentState(new LostState(appController, gameModel.getCurrentLevelInt()));
        else
            appController.setCurrentState(new WonState(appController, gameModel.getScore(), gameModel.getLives(), gameModel.getChicken().getCountSteps()));
    }
}

