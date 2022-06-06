package frame;

import util.PropertiesLoader;

import javax.swing.*;

public class Window extends JFrame {
    public Window ( Camera camera ) {
        super();
        this.setTitle(PropertiesLoader.getStr("game.title"));
        this.setLayout(null);
        this.add(camera);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(
                PropertiesLoader.getInt("game.resolution.width"),
                PropertiesLoader.getInt("game.resolution.height"));
        this.setVisible(true);
    }
}
