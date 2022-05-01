package system.render;

import objects.PaintableObject;
import objects.RenderableObject;
import objects.UpdatableObject;
import objects.creatures.Player;
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
public class Camera extends Canvas implements UpdatableObject, PaintableObject, RenderableObject {

    private Room room;
    private Player player;

    public Camera ( Room entryRoom, Player player ) throws IOException {
        this.room = entryRoom;
        this.player = player;
    }

    /**
     * painting the component
     */
    @Override
    public void paint ( Graphics g ) {

        // from first to second layer
        room.paint(g);

        // player
        player.paint(g);
    }

    @Override
    public void update () {
        // update values

    }

    @Override
    public void render () {
        repaint();
    }
}