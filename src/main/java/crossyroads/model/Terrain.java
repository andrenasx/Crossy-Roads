package crossyroads.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Terrain {
    private String filename;
    private String background;
    private String directory;
    private List<Element> elements;

    public Terrain(String filename, String directory) {
        this.filename = filename;
        this.directory = directory;
        this.elements = new ArrayList<>();
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
        this.background = allLines.get(0);
        for(int i = 1; i < allLines.size()-1; i++){
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
        int x = Integer.valueOf(array_of_strings[1]);
        int y = Integer.valueOf(array_of_strings[2]);
        Position position = new Position(x, y);
        switch (type){
            case "C":
                Car car;
                if(array_of_strings[3].equals(">")){
                    car = new Car(position.getX(), position.getY(), "right");
                }
                else{
                    car = new Car(position.getX(), position.getY(), "left");
                }
                elements.add(car);
                break;
            case "T":
                Truck truck;
                if(array_of_strings[3].equals(">")){
                    truck = new Truck(position.getX(), position.getY(), "right");
                }
                else{
                    truck = new Truck(position.getX(), position.getY(), "left");
                }
                elements.add(truck);
                break;
            case "M":
                Coin coin = new Coin(position.getX(), position.getY(), Integer.valueOf(array_of_strings[3]));
                elements.add(coin);
                break;
        }

    }
}
