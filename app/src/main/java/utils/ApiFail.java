package utils;

import utils.state_machine.State;
import utils.state_machine.StateManager;

/**
 * Created by shrikanth on 12/29/17.
 */

public class ApiFail extends State{

    public ApiFail(StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public void execute() {

    }
}
