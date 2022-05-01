import game.objects.Renderable;
import game.objects.Updatable;
import game.system.render.Camera;
import game.system.update.Clock;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
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

    public final Camera frame;
    public Clock clock;

    public static final int RES_HEIGHT = Integer.parseInt(props.getProperty("game.resolution.height"));
    public static final int RES_WIDTH = Integer.parseInt(props.getProperty("game.resolution.width"));
    public static final int RES_SCALE_FACTOR = Integer.parseInt(props.getProperty("game.resolution.scale"));

    public final String TITLE = props.getProperty("game.title");

    public static void main(String[] args) throws IOException {
        new Game().start();
    }

    public Game () throws IOException {
        this.logger.debug("Init Game");
        this.frame = new Camera(this.TITLE, new Dimension(RES_SCALE_FACTOR * this.RES_WIDTH, RES_SCALE_FACTOR * this.RES_HEIGHT));
    }

    /**
     * start game
     */
    public void start () {

        this.logger.debug("Start Game");

        Updatable[] updatable = { this.frame.getCamera() };
        Renderable[] renderable = { this.frame.getCamera() };

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
