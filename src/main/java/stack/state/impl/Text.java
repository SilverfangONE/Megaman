package stack.state.impl;

import frame.Camera;
import stack.StackSupervisor;
import stack.state.State;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Text implements State, KeyListener {

    private String text;
    private Boolean pressed;

    public Text(String text) {
        this.text = text;
        this.pressed = false;
    }

    @Override
    public void update () {
        // key input event
        if(pressed) {
            StackSupervisor.requestPop();
        }
    }

    @Override
    public void render ( Graphics g ) {
        // text field
        g.setColor(new Color(255, 255, 255));
        int y = 100;
        g.drawRect(0, y, Camera.width, Camera.height - y);
    }

    @Override
    public void keyTyped ( KeyEvent e ) {
        if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
            this.pressed = true;
        }
    }

    @Override
    public void keyPressed ( KeyEvent e ) {}

    @Override
    public void keyReleased ( KeyEvent e ) {}
}
