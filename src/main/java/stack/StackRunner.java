package stack;

import frame.Window;
import lombok.extern.slf4j.Slf4j;
import frame.Camera;
import util.PropertiesLoader;

@Slf4j
public class StackRunner implements Runnable{

    private Camera camera;
    private Window window;
    private final Long OPTIMAL_TIME;
    private Boolean run;

    public StackRunner() {
        this.camera = new Camera();
        this.window = new Window(this.camera);
        this.run = true;
        this.OPTIMAL_TIME =  (long) (Math.pow(10,9) / PropertiesLoader.getInt("game.fps"));
    }

    /**
     * Game Loop
     */
    @Override
    public void run () {

        log.debug("Start Stack Runner");

        int fps = 0;

        while(run) {

            fps++;
            if(fps == 60) { log.debug("FPS:{}", fps); fps = 0;}

            Long start = System.nanoTime();

            processState();

            /* skip if updates takes to long */
            if(System.nanoTime() - start > OPTIMAL_TIME) { continue; }

            render();

            /* sleep until overtime is over */
            try {
                Thread.sleep((OPTIMAL_TIME - (System.nanoTime() - start))/(long)(Math.pow(10, 6)));
            } catch (InterruptedException e) {
                log.debug(" {}", e.getMessage());
            }
        }
    }

    /**
     * process/updates only State on top of stack
     */
    private void processState() {
        StackSupervisor.requestPop().update();
    }

    /**
     * render all States from renderStack
     */
    private void render() {
        camera.repaint();
    }
}
