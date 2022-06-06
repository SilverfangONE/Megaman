package stack;

import stack.state.State;

import java.awt.*;

public class StackSupervisor {

    private static StateStack stack = new StateStack();
    private static Boolean blocked = false;

    public static void requestPush(State s) {
        if(blocked) {
            return;
        }
        stack.push(s);
    }

    public static State requestPop() {
        if(blocked) {
            return null;
        }
        return stack.pop();
    }

    public static void renderStates( Graphics g ) {
        if(blocked) {
            return;
        }
        stack.renderStates(g);
    }
}
