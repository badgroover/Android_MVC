package utils;

import utils.state_machine.Actions;
import utils.state_machine.StateManager;

/**
 * Created by shrikanth on 12/29/17.
 */

public class ApiStateManager extends StateManager {

    @Override
    public void addActions() {
        addState(Actions.INIT, new ApiInit(this));
        addState(Actions.PENDING, new ApiPending(this));
        addState(Actions.SUCCESS, new ApiSuccess(this));
        addState(Actions.FAIL, new ApiFail(this));
    }

    @Override
    public int getInitialAction() {
        return Actions.INIT;
    }
}
