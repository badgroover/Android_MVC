package utils;

import utils.state_machine.Actions;
import utils.state_machine.State;
import utils.state_machine.StateManager;

/**
 * Created by shrikanth on 12/29/17.
 */

public class ApiInit extends State{

    public ApiInit(StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public void execute() {
        MockApi.getData(new MockApi.ResponseHandler() {
            @Override
            public void onSuccess() {
                ApiInit.this.stateManager.performAction(Actions.SUCCESS);
            }

            @Override
            public void onError() {
                ApiInit.this.stateManager.performAction(Actions.FAIL);
            }
        });

        this.stateManager.performAction(Actions.PENDING);
    }
}
