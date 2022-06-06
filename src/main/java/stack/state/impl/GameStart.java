package stack.state.impl;

import stack.StackSupervisor;
import stack.state.State;

import java.awt.*;

public class GameStart implements State {

    public GameStart() {
        StackSupervisor.requestPush(new World());
        StackSupervisor.requestPush(new Text("Hello World"));
    }

    @Override
    public void update () {
        StackSupervisor.requestPop();
    }

    @Override
    public void render ( Graphics g ) {}
}
