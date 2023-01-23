package engine;

import animals.Animal;
import animals.Grass;
import animals.vegans.Caterpillar;
import field.InitialField;
import field.Island;
import render.GameRender;

public class GameEngine {
    private int delay;
    private GameRender gameRender;
    Island island;
    int dayNumber = 1;
    InitialField initialField;

    public GameEngine(int delay, Island island, GameRender gameRender) {
        this.delay = delay;
        this.island = island;
        this.gameRender = gameRender;
        initialField = new InitialField(island);
    }

    public void start() {
        initialField.populateIsland(island);

        while (island.getAllAnimals().stream().count() > 0) {
            try {
                System.out.println("===============================================================================");
                System.out.println("Day: " + dayNumber + ", Animal amount= " + gameRender.getTotalAmount(island));
                nextDay();
                gameRender.printMap(island);
                Thread.sleep(delay);
                dayNumber++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void nextDay() {

        for (Animal currentAnimal : island.getAllAnimals()
        ) {
            if ((currentAnimal instanceof Grass) || (currentAnimal instanceof Caterpillar)) {
            } else {
                currentAnimal.move(initialField.getSpeedFromString(currentAnimal));
            }
            currentAnimal.eat();
            currentAnimal.checkEnergy();
        }
        initialField.printEatenAnimals();
        initialField.breed();
        initialField.setEnergy();
        System.out.println("");
    }


}
