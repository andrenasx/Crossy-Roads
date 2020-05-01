package crossyroads.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Terrain {
    private String filename;
    private String terrainStrings;
    private String directory;

    public Terrain(String filename, String directory) {
        this.filename = filename;
        this.directory = directory;
        this.terrainStrings = readFile(this.filename, this.directory);
    }

    private String readFile(String filename, String directory){
        String terrain = "";
        String filePath = "src/" + directory + "/resources/" + filename;
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
