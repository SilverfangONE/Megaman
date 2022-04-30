package game.system;

import game.entity.Level;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Camera extends Canvas implements Updatable, Renderable {

    private final Level level;

    public Camera ( Dimension res, Level level ) {
        super();
        this.level = level;
        this.setSize(res);
    }

    @Override
    public void paint(Graphics g) {
        level.render(g);
    }

    @Override
    public void render () {

    }

    @Override
    public void update () {

    }
}
