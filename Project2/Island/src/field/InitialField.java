package field;

import animals.Animal;
import animals.Grass;
import animals.predators.*;
import animals.vegans.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class InitialField {
    private Bear bear;
    private Snake snake;
    private Boar boar;
    private Buffalo buffalo;
    private Caterpillar caterpillar;
    private Deer deer;
    private Duck duck;
    private Eagle eagle;
    private Fox fox;
    private Goat goat;
    private Horse horse;
    private Mouse mouse;
    private Rabbit rabbit;
    private Sheep sheep;
    private Wolf wolf;
    private Grass grass;

    public Bear getBear() {
        return new Bear(this, 100, 100);
    }
    public Snake getSnake() {
        return new Snake(this, 20, 20);
    }
    public Boar getBoar() {
        return new Boar(this, 60, 80);
    }
    public Buffalo getBuffalo() {
        return new Buffalo(this, 80, 80);
    }
    public Caterpillar getCaterpillar() {
        return new Caterpillar(this, 5, 5);
    }
    public Deer getDeer() {
        return new Deer(this, 70, 70);
    }
    public Duck getDuck() {
        return new Duck(this, 30, 20);
    }
    public Eagle getEagle() {
        return new Eagle(this, 30, 20);
    }
    public Fox getFox() {
        return new Fox(this, 50, 40);
    }
    public Goat getGoat() {
        return new Goat(this, 60, 60);
    }
    public Horse getHorse() {
        return new Horse(this, 80, 100);
    }
    public Mouse getMouse() {
        return new Mouse(this, 10, 5);
    }
    public Rabbit getRabbit() {
        return new Rabbit(this, 20, 20);
    }
    public Sheep getSheep() {
        return new Sheep(this, 60, 50);
    }
    public Wolf getWolf() {
        return new Wolf(this, 80, 50);
    }
    public Grass getGrass() {
        return new Grass(this, 50, 50);
    }


    private Map<Animal, String> animalStringMap;
    private static List<Animal> allAnimals = new ArrayList<>();
    private static List<Animal> eatenAnimals = new ArrayList<>();
    public static void addEatenAnimals(Animal animal) {
        eatenAnimals.add(animal);
    }

    Island island;

    public InitialField(Island island) {
        this.island = island;
    }

    public Island getIsland() {
        return island;
    }

    public static List<Animal> getAllAnimals() {


        return allAnimals;
    }

    public void populateIsland(Island island) {
        for (int i = 0; i < island.xDimension; i++) {
            for (int j = 0; j < island.yDimension; j++) {
                populateCell(island.islandGrid[i][j], island);
            }
        }
    }

    private void populateCell(Cell cell, Island island) {
        int animalcount = 0;
        HashMap<Animal, Integer> animalHashMap = new HashMap<>();
        //getChanceToAppearFromTXT();
        for (Map.Entry<Animal, Integer> entry : getChanceToAppearFromTXT().entrySet()) {
            Animal key = entry.getKey();
            Integer value = entry.getValue();
            if (calculateChanceToEmerge(value)) {
                cell.addAnimal(key);
                allAnimals.add(key);
                key.setPosition(cell);
                //Animal.setPosition(cell);
                System.out.print(key.getIcon());
                animalcount++;
            } else {
            }
        }
        System.out.printf("\t" + "cell %s populated with %s animals ", cell, animalcount);
        System.out.println();
    }


    private Map<Animal, Integer> getChanceToAppearFromTXT() {
        HashMap<Animal, Integer> mapFromTXT = new HashMap<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("Island/src/animalBase.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                try {
                    line = br.readLine();
                    if (line == null) {
                        break;
                    }
                    for (Map.Entry<Animal, String> entry : getAnimalStringMap().entrySet()) {
                        String str = entry.getKey().toString();
                        if (line.contains(str)) {
                            mapFromTXT.put(entry.getKey(), getChanceFromString(line, 1));
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            String everything = sb.toString();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return mapFromTXT;
    }

    public int getDataFromTXT(String string, int column) {
        int returnNumber = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("Island/src/animalBase.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                try {
                    line = br.readLine();
                    if (line == null) {
                        break;
                    }

                    if (line.contains(string)) {
                        String[] words = line.split("\\W+");
                        returnNumber = Integer.parseInt(words[column].toString());
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            String everything = sb.toString();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return returnNumber;
    }

    private boolean calculateChanceToEmerge(int chancePercentage) {
        Random random = new Random();
        int randomChance = random.nextInt(100);
        if (randomChance < chancePercentage) {
            return true;
        } else {
            return false;
        }

    }

    public Map<Animal, String> getAnimalStringMap() {
        animalStringMap = new HashMap<>() {{
            put(getBear(), "bear");
            put(getBoar(), "boar");
            put(getBuffalo(), "buffalo");
            put(getCaterpillar(), "caterpillar");
            put(getDeer(), "deer");
            put(getDuck(), "duck");
            put(getEagle(), "eagle");
            put(getFox(), "fox");
            put(getGoat(), "goat");
            put(getHorse(), "horse");
            put(getMouse(), "mouse");
            put(getRabbit(), "rabbit");
            put(getSheep(), "sheep");
            put(getSnake(), "snake");
            put(getWolf(), "wolf");
            put(getGrass(), "grass");
        }};

        return animalStringMap;
    }

    private int getChanceFromString(String string, int position) {
        String[] words = string.split("\\W+");
        return Integer.parseInt(words[position]);
    }


    private int getEnergyConsumptionFromTXT(Animal animal) {
        int energyCapasity = 0;
        for (Map.Entry<Animal, String> stringAnimal : getAnimalStringMap().entrySet()) {
            if (stringAnimal.getKey().toString().equals(animal.toString())) {
                energyCapasity = getDataFromTXT(stringAnimal.getValue(), 4);
            }
        }
        return energyCapasity;
    }

    public int getSpeedFromString(Animal currentAnimal) {
        int speed = 0;
        for (Map.Entry<Animal, String> stringAnimal : getAnimalStringMap().entrySet()) {
            if (stringAnimal.getKey().toString().equals(currentAnimal.toString())) {
                speed = getDataFromTXT(stringAnimal.getValue(), 2);
            }
        }
        return speed;
    }

    public void setEnergy() {
        Cell[][] cells = island.islandGrid;
        for (int x = 0; x < island.xDimension; x++) {
            for (int y = 0; y < island.yDimension; y++) {
                for (Animal animal : cells[x][y].getAnimalList()) {
                    animal.getEnergyCapacity();
                    int energyConsumption = getEnergyConsumptionFromTXT(animal);
                    animal.setEnergyCapacity(animal.getEnergyCapacity() - energyConsumption);
                }
            }
        }
    }

    public void printEatenAnimals() {
        System.out.print("\uD83C\uDF57Eaten animals: ");
        System.out.print(eatenAnimals.size() + "pcs: ");
        for (Animal animal : eatenAnimals
        ) {
            System.out.print(animal.getIcon());
        }
        System.out.println();
        eatenAnimals.clear();
    }

    public void breed() {
        ArrayList<Animal> newAnimalsList = new ArrayList<>();
        for (int x = 0; x < getIsland().xDimension; x++) {
            for (int y = 0; y < getIsland().yDimension; y++) {
                List<Animal> loveList = getIsland().islandGrid[x][y].getAnimalList();
                List<Animal> newAnimals = new ArrayList<>();
                for (int i = 0; i < loveList.size(); i++) {
                    Animal animal = loveList.get(i);
                    for (Animal checkAnimal : loveList) {
                        boolean firstCondition = checkAnimal.getClass().equals(animal.getClass());
                        boolean secondCondition = checkAnimal instanceof Grass;
                        boolean thirdCondition = checkAnimal.equals(animal);
                        boolean forthCondition = isAlreadyBreed(newAnimals, animal);
                        boolean fifthCondition = calculateChanceToEmerge(getDataFromTXT(animal.toString(), 1));
                        if (firstCondition && !secondCondition && !thirdCondition && !forthCondition && fifthCondition) {
                            //System.out.print(checkAnimal.getClass() + " " + checkAnimal.hashCode() + "/");
                            //System.out.print(animal.getClass() + " " + animal.hashCode());
                            //System.out.println(animal.getPosition());
                            newAnimals.add(animal.breed(animal.getPosition()));
                            newAnimalsList.add(animal);
                            break;
                        }
                    }
                }
            }
        }
        System.out.print("\uD83D\uDC76New animals: ");
        System.out.print(" " + newAnimalsList.size() + "pcs");
        for (Animal newAnimal : newAnimalsList) {
            System.out.print(newAnimal.getIcon());
        }
        System.out.println();
    }

    private boolean isAlreadyBreed(List<Animal> newAnimals, Animal animal) {
        for (Animal findAnimal : newAnimals) {
            if (animal.getClass().equals(findAnimal.getClass())) {
                return true;
            }
        }
        return false;
    }
}
