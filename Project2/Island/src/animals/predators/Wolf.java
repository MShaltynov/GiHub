package animals.predators;

import animals.Animal;
import animals.Predators;
import field.Cell;
import field.InitialField;

import java.util.HashMap;
import java.util.Map;

public class Wolf extends Predators {
    public Wolf(InitialField initialField, int energyCapacity,int givenEnergyIfEaten) {
        super(initialField);
        this.energyCapacity = energyCapacity;
        this.givenEnergyIfEaten = givenEnergyIfEaten;
    }
    @Override
    public Animal getNewAnimal(InitialField initialField) {
        return new Wolf(initialField,5,5);
    }
    private int givenEnergyIfEaten;

    public int getGivenEnergyIfEaten() {
        return givenEnergyIfEaten;
    }
    private int energyCapacity;

    public void setEnergyCapacity(int energyCapacity) {
        this.energyCapacity = energyCapacity;
    }

    public int getEnergyCapacity() {
        return energyCapacity;
    }

    private Cell position;
    private Map<String,Integer> chanceToEat=new HashMap<>(){{
        put("horse",10);
        put("deer",15);
        put("rabbit",60);
        put("mouse",80);
        put("goat",60);
        put("sheep",70);
        put("boar",15);
        put("buffalo",10);
        put("duck",40);
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
        return "wolf";
    }

    @Override
    public String getIcon() {
        return  "🐺";
    }
}
