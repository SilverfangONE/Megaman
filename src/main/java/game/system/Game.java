package game.system;

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

    public final MainFrame frame;
    public Clock clock;

    public final int RES_HEIGHT = Integer.parseInt(props.getProperty("game.resolution.width"));
    public final int RES_WIDTH = Integer.parseInt(props.getProperty("game.resolution.width"));
    public final String TITLE = props.getProperty("game.title");

    public Game () throws IOException {
        this.logger.debug("Init Game");
        this.frame = new MainFrame(this.TITLE, new Dimension(this.RES_HEIGHT, this.RES_WIDTH));
    }

    /**
     * start game
     */
    public void start () {

        this.logger.debug("Start Game");

        Updatable[] updatable = {};
        Renderable[] renderable = {};

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
