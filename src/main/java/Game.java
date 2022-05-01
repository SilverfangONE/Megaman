

import lombok.RequiredArgsConstructor;
import objects.PaintableObject;
import objects.RenderableObject;
import objects.UpdatableObject;
import objects.creatures.Player;
import objects.screen.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import system.render.Camera;
import system.update.Clock;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Entry point for game
 *
 * @author Luis Boden
 */
@RequiredArgsConstructor
public class Game {

    private static Logger logger = LoggerFactory.getLogger(Game.class);

    public static final Properties props = getProperties();

    public final Camera camera;
    public Clock clock;

    public static final int RES_HEIGHT = Integer.parseInt(props.getProperty("game.resolution.height"));
    public static final int RES_WIDTH = Integer.parseInt(props.getProperty("game.resolution.width"));
    public static final int RES_SCALE_FACTOR = Integer.parseInt(props.getProperty("game.resolution.scale"));
    public static final int TILE_HEIGHT = Integer.parseInt(props.getProperty("game.tile.height"));

    public final String TITLE = props.getProperty("game.title");

    public static void main(String[] args) throws IOException {
        new Game().start();
    }

    public Game () throws IOException {
        this.logger.debug("Init Game");

        Room entryRoom = Room.readRaw("./TileMap/test_01.json");
        Player player = new Player();

        this.camera = new Camera(entryRoom, player);
    }

    /**
     * start game
     */
    public void start () {

        this.logger.debug("Start Game");

        UpdatableObject[] updatable = { this.camera };
        RenderableObject[] renderable = { this.camera };

        this.clock = new Clock(updatable, renderable);
        new Thread(this.clock).start();
    }

    private static Properties getProperties()  {
        Properties p = new Properties();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("application.properties");

        try {
            p.load(is);
        } catch (IOException ioException) {
            logger.warn(ioException.getMessage());
        }
        return p;
    }
}
