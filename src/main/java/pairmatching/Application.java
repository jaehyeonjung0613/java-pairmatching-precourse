package pairmatching;

import pairmatching.infrastructure.Game;
import pairmatching.infrastructure.GameManager;

public class Application {
    public static void main(String[] args) {
        Game game = new GameManager().build();
        try {
            game.run();
        } finally {
            game.finish();
        }
    }
}
