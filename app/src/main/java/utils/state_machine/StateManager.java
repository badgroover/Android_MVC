package utils.state_machine;

import android.util.SparseArray;

import MVC.BaseController;

/**
 * Created by shrikanth on 12/29/17.
 */

public abstract class StateManager {

    protected BaseController controller;
    protected SparseArray<State> actionStateMap = new SparseArray<>();
    private int currentAction = -1;
    private StateObserver observer;
    private boolean isRunning = false;

    public StateManager() {
        addActions();
    }

    public void setController(BaseController controller) {
        this.controller = controller;
    }

    public BaseController getController() {
        return controller;
    }

    public void setObserver(StateObserver observer) {
        this.observer = observer;
    }

    protected void addState(int action, State state){
        if(actionStateMap.get(action) != null){
            throw new RuntimeException("Action " + action + " already exists");
        }else {
            actionStateMap.put(action, state);
        }
    }

    public void run(){
        if(currentAction == -1){
            currentAction = getInitialAction();
        }
        if(!isRunning)
            performAction(currentAction);
        else
            notifyObserver();

    }


    public void performAction(int action){
        currentAction = action;
        notifyObserver();
        execute();
    }

    private void notifyObserver(){
        if(controller.isResumed()) {
            if (observer != null) {
                observer.onAction(currentAction);
            }
        }
    }
    private void execute(){
        State currentState = actionStateMap.get(currentAction);

        if(controller.isResumed()){
            isRunning = true;
            currentState.execute();
        }else{
            isRunning = false;
        }
    }

    public boolean isRunning() {
        return isRunning;
    }


    public abstract void addActions();
    public abstract int getInitialAction();
}
