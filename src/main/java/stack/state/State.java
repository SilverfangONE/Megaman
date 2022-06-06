package stack.state;

import java.awt.*;

public interface State {
    void update();
    void render( Graphics g );
}
