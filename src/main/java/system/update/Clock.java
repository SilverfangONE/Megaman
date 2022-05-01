package game.system.update;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Game game.system.update.Clock
 */
@RequiredArgsConstructor
@AllArgsConstructor
public class Clock implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(Clock.class);

    private Updatable[] updatable;
    private Renderable[] renderable;

    /**
     * Time interval for processing frame
     */
    private final long OPTIMAL_TIME = (long) (Math.pow(10,9) / Integer.parseInt(Game.props.getProperty("game.fps")));

    private final boolean run = true;

     /**
     * game loop/clock execution
     */
    @Override
    public void run () {

        logger.debug("Start game.system.update.Clock");

        int fps = 0;
        while(run) {

            fps++;
            if(fps == 60) { logger.debug("FPS:{}", fps); fps = 0;}

            Long start = System.nanoTime();

            update();

            /* skip if updates takes to long */
            if(System.nanoTime() - start > OPTIMAL_TIME) { continue; }

            render();

            /* sleep until overtime is over */
            try {
                Thread.sleep((OPTIMAL_TIME - (System.nanoTime() - start))/(long)(Math.pow(10, 6)));
            } catch (InterruptedException e) {
                logger.debug("game.system.update.Clock: {}", e.getMessage());
            }
        }
    }

    /**
     * update components in updatable array
     */
    private void update() {
        for(int i = 0; i < this.updatable.length; i++) {
            this.updatable[i].update();
        }
    }

    /**
     * render components in renderable array
     */
    private void render() {
        for(int i = 0; i < this.renderable.length; i++) {
            this.renderable[i].render();
        }
    }
}
