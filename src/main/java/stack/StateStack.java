package stack;

import lombok.NoArgsConstructor;
import stack.state.State;

import java.awt.*;
import java.util.LinkedList;

@NoArgsConstructor
public class StateStack {

    private LinkedList<State> list = new LinkedList<>();

    public StateStack(State initState) {
        push(initState);
    }

    protected void push(State state) {
        list.push(state);
    }

    protected State pop() {
        return list.pop();
    }

    public int getSize() {
        return this.list.size();
    }

    protected void renderStates( Graphics g ) {
        for(State state: list) {
            state.render(g);
        }
    }
}
