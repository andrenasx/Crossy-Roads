package crossyroads.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Terrain {
    private String filename;
    private String background;
    private String directory;
    private List<Element> elements;

    public Terrain(String filename, String directory) throws IOException {
        this.filename = filename;
        this.directory = directory;
        this.elements = new ArrayList<>();
        readFile(this.filename, this.directory);
    }

    private void readFile(String filename, String directory) throws IOException {
        String filePath = "src/" + directory + "/resources/" + filename;
        BufferedReader br;
        List<String> allLines = new ArrayList<>();

        br = new BufferedReader(new FileReader(filePath));
        String st;
        while ((st = br.readLine()) != null){
            allLines.add(st);

        }
        br.close();

        this.background = allLines.get(0);
        for(int i = 1; i < allLines.size(); i++){
            addElement(allLines.get(i));
        }
    }

    public String getBackground() {
        return background;
    }

    public List<Element> getElements(){
        return elements;
    }

    private void addElement(String line){
        String[] array_of_strings = line.split(",");
        String type = array_of_strings[0];
        int x = Integer.parseInt(array_of_strings[1]);
        int y = Integer.parseInt(array_of_strings[2]);
        switch (type){
            case "C":
                Car car;
                if(array_of_strings[3].equals(">")){
                    car = new Car(x, y, "right");
                }
                else{
                    car = new Car(x, y, "left");
                }
                elements.add(car);
                break;
            case "T":
                Truck truck;
                if(array_of_strings[3].equals(">")){
                    truck = new Truck(x, y, "right");
                }
                else{
                    truck = new Truck(x, y, "left");
                }
                elements.add(truck);
                break;
            case "M":
                Coin coin = new Coin(x, y);
                elements.add(coin);
                break;
            default:
                break;
        }

    }
}
