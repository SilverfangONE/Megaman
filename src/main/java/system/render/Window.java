package system.render;

import objects.screen.Room;
import util.PropertiesLoader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Window extends JFrame {

    // window size
    public final int WIN_HEIGHT;
    public final int WIN_WIDTH;

    public Window ( Camera camera ) throws IOException {
        super();
        this.WIN_WIDTH = Integer.parseInt(PropertiesLoader.getProperty("game.resolution.width"));
        this.WIN_HEIGHT = Integer.parseInt(PropertiesLoader.getProperty("game.resolution.height"));
        settings(camera);
    }

    /**
     * setup Main Frame
     */
    private void settings(Camera camera) {

        // close by clicking exit

        this.setLayout(null);
        this.add(camera);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(WIN_WIDTH, WIN_HEIGHT);
        this.setVisible(true);
    }
}
