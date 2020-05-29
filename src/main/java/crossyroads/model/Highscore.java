package crossyroads.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Highscore {
    private String filename;
    private String highscore;
    private String directory;
    private List<Score> highscores;

    public Highscore(String filename, String directory) {
        this.filename = filename;
        this.directory = directory;
        this.highscores = new ArrayList<>();
        readFile(this.filename, this.directory);
    }

    private void readFile(String filename, String directory){
        String filePath = "src/" + directory + "/resources/" + filename;
        BufferedReader br;
        List<String> allLines = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(filePath));
            String st;
            while ((st = br.readLine()) != null){
                allLines.add(st);

            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.highscore = allLines.get(0);
        for(int i = 1; i < allLines.size(); i++){
            addElement(allLines.get(i));
        }
        Collections.sort(highscores, new ScoreComparator());
    }

    public void writeFile(String filename, String directory){
        String filePath = "src/" + directory + "/resources/" + filename;
        BufferedWriter bw = null;
        String text = "Level - Coins - Steps";
        for (Score score : highscores){
            text += "\n";
            text += score.getLevel() + ",";
            text += score.getCoins() + ",";
            text += score.getSteps() + ",";
            //text.substring(0, text.length() - 1);
        }

        try {
            bw = new BufferedWriter(new FileWriter(filePath));
            String st;
            bw.write(text);
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addElement(String line){
        String[] array_of_strings = line.split(",");
        int level = Integer.parseInt(array_of_strings[0]);
        int coins = Integer.parseInt(array_of_strings[1]);
        int steps = Integer.parseInt(array_of_strings[2]);
        Score score = new Score(level, coins, steps);
        highscores.add(score);

    }

    public void addNewScore(int level, int coins, int steps){
        Score result = new Score();
        result.setLevel(level);
        result.setCoins(coins);
        result.setSteps(steps);
        highscores.add(result);
        Collections.sort(highscores, new ScoreComparator());
    }

    public List<Score> getHighscores(){ return highscores;}


}
