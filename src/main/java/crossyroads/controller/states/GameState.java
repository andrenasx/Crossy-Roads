package crossyroads.controller.states;

import crossyroads.controller.AppController;
import crossyroads.controller.ChickenController;
import crossyroads.controller.VehicleController;
import crossyroads.model.GameModel;
import crossyroads.model.Highscore;
import crossyroads.view.*;

import java.io.IOException;

public class GameState implements State{
    private AppController appController;
    private ChickenController chickenController;
    private VehicleController vehicleController;
    private GuiGame gui;
    private GameModel gameModel;
    private final int FPS = 10;
    private int step = 0;

    public GameState(AppController appController, GuiGame gui, GameModel gameModel, ChickenController chickenController, VehicleController vehicleController) {
        this.appController = appController;
        this.gameModel = gameModel;
        this.gui = gui;
        this.chickenController = chickenController;
        this.vehicleController = vehicleController;
    }

    public void step() throws IOException {
        while(!gameModel.isChickenDead()) {
            long time = System.currentTimeMillis();
            step++;

            Gui.COMMAND command = gui.getNextCommand();
            if(command == GuiGame.COMMAND.PAUSE){
                appController.setCurrentState(new PauseState(appController, new GuiPauseMenu(gui.getScreen()), this));
                return;
            }

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

        Highscore highscore = new Highscore("highscores.txt", "main");
        highscore.addNewScore(gameModel.getCurrentLevelInt(), gameModel.getScore(), gameModel.getChicken().getCountSteps());
        highscore.writeFile("highscores.txt", "main");

        if(gameModel.isChickenDead())
            appController.setCurrentState(new LostState(appController, new GuiLost(gui.getScreen(), gameModel.getCurrentLevelInt())));
        else {
            appController.setCurrentState(new WonState(appController, new GuiWon(gameModel.getScore(), gameModel.getLives(), gameModel.getChicken().getCountSteps(), gui.getScreen())));
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        return getClass() == obj.getClass();
    }
}

