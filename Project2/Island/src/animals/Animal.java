package animals;

import field.Cell;
import field.InitialField;

import java.util.List;
import java.util.Map;
import java.util.Random;


public abstract class Animal {
    public abstract Map<String, Integer> getChanceToEatList();

    public abstract void setPosition(Cell positionCell);

    public abstract Cell getPosition();

    public abstract int getEnergyCapacity();

    public abstract void setEnergyCapacity(int energyCapacity);

    public abstract int getGivenEnergyIfEaten();

    public abstract Animal getNewAnimal(InitialField initialField);

    InitialField initialField;

    public Animal(InitialField initialField) {
        this.initialField = initialField;
    }

    public void move(int distance) {
        //System.out.println("Animal " + this + getIcon() + " started moving. Current position " + getPosition() + " distance: " + distance + " Energy: " + getEnergyCapacity());
        Random moveDecider = new Random();
        for (int i = 0; i < distance; i++) {
            boolean moveDecision = moveDecider.nextBoolean();
            if (moveDecision) {
                //System.out.print(getIcon() + "Animal will move to other cell");
                moveToOtherCell();
            } else {
                //System.out.println(getIcon() + "Animal decided to stay here " + getPosition());
            }
        }
        //System.out.println("Animal finished moving " + getPosition());
    }

    private void moveToOtherCell() {
        Random directionPicker = new Random();
        Direction direction;
        Direction[] directions = Direction.values();
        do {
            direction = directions[directionPicker.nextInt(directions.length)];
        } while (!directionValid(direction));
        changePosition(direction);
    }

    private boolean directionValid(Direction direction) {
        int xPosition = getPosition().x;
        int yPosition = getPosition().y;
        switch (direction) {
            case UP -> {
                return yPosition - 1 >= 0;
            }
            case DOWN -> {
                return yPosition + 1 < initialField.getIsland().yDimension;
            }
            case LEFT -> {
                return xPosition - 1 >= 0;
            }
            case RIGHT -> {
                return xPosition + 1 < initialField.getIsland().xDimension;
            }
            default -> throw new IllegalArgumentException("IllegalAccessException");
        }
    }

    private void changePosition(Direction direction) {
        int newX = 0;
        int newY = 0;
        switch (direction) {
            case UP -> {
                newX = getPosition().x;
                newY = getPosition().y - 1;
            }
            case DOWN -> {
                newX = getPosition().x;
                newY = getPosition().y + 1;
            }
            case LEFT -> {
                newX = getPosition().x - 1;
                newY = getPosition().y;
            }
            case RIGHT -> {
                newX = getPosition().x + 1;
                newY = getPosition().y;
            }
        }
        Cell newCell = initialField.getIsland().islandGrid[newX][newY];

        removeAnimal(this);
        this.setPosition(newCell);
        this.getPosition().addAnimal(this);
    }

    private void removeAnimal(Animal animal) {
        this.getPosition().removeAnimal(animal);
    }

    public void eat() {
        eatingProcess();
    }

    public boolean eatingProcess() {
        for (int x = 0; x < initialField.getIsland().xDimension; x++) {
            for (int y = 0; y < initialField.getIsland().yDimension; y++) {
                List<Animal> victimList = initialField.getIsland().islandGrid[x][y].getAnimalList();
                if (findVictim(victimList)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void swallow(Animal actualAnimal) {
        int actualEnergyifEaten = actualAnimal.getGivenEnergyIfEaten();
        int newEnergy = this.getEnergyCapacity() + actualEnergyifEaten;
        int maxEnergy = initialField.getDataFromTXT(actualAnimal.toString(), 3);
        if (newEnergy > maxEnergy) {
            setEnergyCapacity(maxEnergy);
        } else {
            setEnergyCapacity(newEnergy);
        }
    }

    private boolean findVictim(List<Animal> animalsInCell) {
        for (Map.Entry eatableAnimal : getChanceToEatList().entrySet()) {
            for (Animal actualAnimal : animalsInCell) {
                if (actualAnimal.toString().equals(eatableAnimal.getKey().toString())) {
                    if (calculateChance(Integer.parseInt(eatableAnimal.getValue().toString()))) {
                        swallow(this);
                        //System.out.println("Animal " + getIcon() + " has ate " + actualAnimal.getIcon());
                        if (actualAnimal instanceof Grass) {
                        } else {
                            initialField.addEatenAnimals(actualAnimal);
                            Cell findPosition = actualAnimal.getPosition();
                            findPosition.removeAnimal(actualAnimal);
                            growGrass(findPosition);
                            //System.out.println("???\uD83D\uDC80  " + actualAnimal.getIcon() + actualAnimal + " was eaten by " + getIcon() + " Position " + getPosition()+"Energy "+this.getEnergyCapacity());
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void growGrass(Cell position) {
        boolean findGrass = false;
        for (Animal animal : position.getAnimalList()) {
            if (animal instanceof Grass) {
                findGrass = true;
                setEnergyToGrass(animal.getPosition(), animal);
                break;
            }
        }
        if (!findGrass) {
            Animal newAnimal = new Grass(initialField, 10, 10);
            position.addAnimal(newAnimal);
            newAnimal.setPosition(position);
        }
    }

    private void setEnergyToGrass(Cell position, Animal animal) {
        animal.setEnergyCapacity(10);
    }

    private boolean calculateChance(int probability) {
        Random random = new Random();
        int randomChance = random.nextInt(100);
        if (randomChance < probability) {
            return true;
        } else {
            return false;
        }

    }

    public void checkEnergy() {
        if (getEnergyCapacity() < 0) {
            removeAnimal(this);
            if (this instanceof Grass) {
            } else {
                System.out.println("\uD83D\uDC80" + "Animal " + getIcon() + this + " died from starving in cell:" + this.getPosition());
                growGrass(this.getPosition());
            }

        }
    }

    public Animal breed(Cell position) {
        Animal newAnimal = getNewAnimal(initialField);
        position.addAnimal(newAnimal);
        newAnimal.setPosition(position);
        return newAnimal;
    }


    @Override
    public String toString() {
        return super.toString();
    }

    public String getIcon() {
        return "";
    }
}




