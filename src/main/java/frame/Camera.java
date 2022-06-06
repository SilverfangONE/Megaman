package frame;

import stack.StackSupervisor;
import util.PropertiesLoader;

import java.awt.*;

public class Camera extends Canvas {

    public static final int width = PropertiesLoader.getInt("game.resolution.width");
    public static final int height = PropertiesLoader.getInt("game.resolution.height");

    @Override
    public void paint ( Graphics g ) {
        StackSupervisor.renderStates(g);
    }

    public Camera() {
        super();
        this.setSize(width, height);
    }
}