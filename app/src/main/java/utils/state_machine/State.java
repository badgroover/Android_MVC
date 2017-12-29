package utils.state_machine;

/**
 * Created by shrikanth on 12/29/17.
 */

public abstract class State {
    protected StateManager stateManager;

    public State(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    abstract public void  execute();
}
