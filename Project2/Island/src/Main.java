import engine.GameEngine;
import field.Island;
import render.GameRender;

public class Main {
    public static void main(String[] args)  {
        Island island = new Island(5,5);
        GameRender gameRender = new GameRender();
        GameEngine gameEngine = new GameEngine(1000,island,gameRender);
        gameEngine.start();
        //TODO stop condition

    }
}
