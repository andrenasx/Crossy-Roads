package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Terrain {
    private String filename;
    private String terrainStrings;

    public Terrain(String filename) {
        this.filename = filename;
        this.terrainStrings = readFile(this.filename);
    }

    private String readFile(String filename){
        String terrain = "";
        String filePath = "src/main/resources/" + filename;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String st;
            while ((st = br.readLine()) != null){
                terrain = st;
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return terrain;
    }

    public String getTerrainStrings() {
        return terrainStrings;
    }
}
