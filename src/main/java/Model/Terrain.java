package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Terrain {
    private String filename;
    private List<String> terrainStrings;

    public Terrain(String filename) {
        this.filename = filename;
        this.terrainStrings = readFile(this.filename);
    }

    private List<String> readFile(String filename){
        List<String> terrain = new ArrayList<>();
        String filePath = "src/main/resources/" + filename;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String st;
            while ((st = br.readLine()) != null){
                terrain.add(st);
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return terrain;
    }

    public List<String> getTerrainStrings() {
        return terrainStrings;
    }

    public String getTerrainColor(int y){
        String terrain = terrainStrings.get(y);
        switch (String.valueOf(terrain.charAt(0))) {
            case "g":
                return "#006600";
            case "r":
                return "#C8C8C8";
            case "d":   //finish line
                return "#013220";
        }
        return "000000";
    }
}
