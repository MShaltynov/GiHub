package animals.predators;

import animals.Animal;
import animals.Predators;
import field.Cell;
import field.InitialField;

import java.util.HashMap;
import java.util.Map;

public class Snake extends Predators {
    public Snake(InitialField initialField, int energyCapacity,int givenEnergyIfEaten) {
        super(initialField);
        this.energyCapacity = energyCapacity;
        this.givenEnergyIfEaten = givenEnergyIfEaten;
    }
    @Override
    public Animal getNewAnimal(InitialField initialField) {
        return new Snake(initialField,5,5);
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
        put("fox",15);
        put("rabbit",20);
        put("mouse",40);
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
        return "snake";
    }

    @Override
    public String getIcon() {
        return  "🐍";
    }
}
