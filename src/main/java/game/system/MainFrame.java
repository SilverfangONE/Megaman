package game.system;


import game.entity.Level;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

@Getter
@Setter
public class MainFrame extends JFrame {

    private final Camera camera;
    private final Dimension res;

    public MainFrame(String title , Dimension res) throws IOException {
        super(title);
        this.res = res;
        this.camera = new Camera(res, Level.readRaw("./TileMap/test_01.json"));
        settings();
    }

    /**
     * setup Main Frame
     */
    private void settings() {

        // close by clicking exit
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setSize(res);
        this.add(camera);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
