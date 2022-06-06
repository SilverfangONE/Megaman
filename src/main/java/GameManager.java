
import frame.Window;
import stack.StackRunner;

/**
 * Entry point for game
 *
 * @author Luis Boden
 */
public class GameManager {
    public static void main(String[] args) {
        GameManager.start();
    }

    public static void start () {
        new Thread(new StackRunner()).start();
    }
}
