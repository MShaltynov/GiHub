package animals;
import field.Cell;
import field.InitialField;
import java.util.Map;

public class Predators extends Animal{
    private Cell position;
    public int getEnergyCapacity() {
        return 0;
    }
    private int energyCapacity;
    public void setEnergyCapacity(int energyCapacity) {
        this.energyCapacity = energyCapacity;
    }
    public Predators(InitialField initialField) {
        super(initialField);
    }

    @Override
    public int getGivenEnergyIfEaten() {
        return 0;
    }

    @Override
    public Animal getNewAnimal(InitialField initialField) {
        return null;
    }

    @Override
    public Cell getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Cell positionCell) {
        this.position = positionCell;
    }

    @Override
    public Map<String, Integer> getChanceToEatList() {
        return null;
    }

}
