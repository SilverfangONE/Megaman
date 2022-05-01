package game.system.render;

import objects.screen.Room;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.IOException;

@Getter
@Setter
/**
 * Main Frame
 */
public class Camera extends Canvas implements game.objects.Updatable {

    private Room room;

    public Camera () throws IOException {
        this.res = res;
        this.camera = new RenderManager(res, Room.readRaw("./TileMap/test_01.json"));
        settings();
    }

    /**
     * setup Main Frame
     */
    private void settings () {
        camera.setBounds(-16, -16, camera.getLevel().getWidth() * camera.getLevel().getTileWidth(), camera.getLevel().getHeight() * camera.getLevel().getTileHeight());
    }

    /**
     * painting the component
     */
    public void paint ( Graphics g ) {
        // TODO
    }

    @Override
    public void update () {

    }

    public
}
