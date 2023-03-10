package animals.predators;

import animals.Animal;
import animals.Predators;
import field.Cell;
import field.InitialField;

import java.util.HashMap;
import java.util.Map;

public class Bear extends Predators {
    public Bear(InitialField initialField, int energyCapacity,int givenEnergyIfEaten ) {
        super(initialField);
        this.energyCapacity = energyCapacity;
        this.givenEnergyIfEaten = givenEnergyIfEaten;
    }

    @Override
    public Animal getNewAnimal(InitialField initialField) {
        return new Bear(initialField,5,5);
    }

    private int energyCapacity;
    private int givenEnergyIfEaten;

    public int getGivenEnergyIfEaten() {
        return givenEnergyIfEaten;
    }

    public void setEnergyCapacity(int energyCapacity) {
        this.energyCapacity = energyCapacity;
    }

    public int getEnergyCapacity() {
        return energyCapacity;
    }

    private Cell position;
    private Map<String,Integer> chanceToEat=new HashMap<>(){{
        put("snake",80);
        put("horse",40);
        put("deer",80);
        put("rabbit",80);
        put("mouse",90);
        put("sheep",70);
        put("boar",50);
        put("goat",70);
        put("buffalo",20);
        put("duck",10);
    }};

    @Override
    public Map<String, Integer> getChanceToEatList() {
        return chanceToEat;
    }


    @Override
    public Cell getPosition() {
        return position;
    }

    @Override
    public void setPosition(Cell positionCell) {
        this.position = positionCell;
    }



    @Override
    public String toString() {
        return "bear";
    }

    @Override
    public String getIcon() {
        return "🐻";
    }
}
