package animals.vegans;

import animals.Animal;
import animals.Vegans;
import animals.predators.Bear;
import field.Cell;
import field.InitialField;

import java.util.HashMap;
import java.util.Map;

public class Deer extends Vegans {
    public Deer(InitialField initialField, int energyCapacity,int givenEnergyIfEaten) {
        super(initialField);
        this.energyCapacity = energyCapacity;
        this.givenEnergyIfEaten = givenEnergyIfEaten;
    }
    @Override
    public Animal getNewAnimal(InitialField initialField) {
        return new Deer(initialField,5,5);
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
        put("grass",100);
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
        return "deer";
    }

    @Override
    public String getIcon() {
        return  "🦌";
    }
}
