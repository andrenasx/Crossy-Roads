package Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;

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
}
