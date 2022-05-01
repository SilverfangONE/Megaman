package system.render;

import objects.screen.Room;

import javax.swing.*;
import java.io.IOException;

public class Window extends JFrame {

    // window size
    public final int WIN_HEIGHT;
    public final int WIN_WIDHT;

    public Window () throws IOException {
        super();
        this.res = res;
        this.camera = new game.system.render.Camera() new RenderManager(res, Room.readRaw("./TileMap/test_01.json"));
        settings();
    }

    /**
     * setup Main Frame
     */
    private void settings() {

        // close by clicking exit

        this.setLayout(null);
        this.add(camera);
        camera.setBounds(- 16, - 16,  camera.getLevel().getWidth() * camera.getLevel().getTileWidth(), camera.getLevel().getHeight() * camera.getLevel().getTileHeight());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(res);
        this.setVisible(true);
    }

}
