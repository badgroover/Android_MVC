package MVC;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import MVC.BaseController;
import views.PMFragment;

/**
 * Created by nsohoni on 14/10/17.
 */

public class GlobalControllerFactory {


    private static GlobalControllerFactory INSTANCE;
    public synchronized static GlobalControllerFactory getInstance(){
        if(INSTANCE == null)
            INSTANCE = new GlobalControllerFactory();

        return INSTANCE;
    }

    Map<UUID, BaseController> map = new HashMap<>();

    private GlobalControllerFactory() {
    }

    public <T extends BaseController> T  getControllerForFragment(PMFragment fragment, Class<T> controllerClass) {
        BaseController controller;
        UUID fragmentId = fragment.getFragmentId();
        if(map.containsKey(fragmentId)) {
            controller = map.get(fragmentId);
            fragment.getLifecycle().addObserver(controller);
            return controllerClass.cast(controller);
        } else {
            Constructor<?> ctor = null;
            try {
                ctor = controllerClass.getConstructor(PMFragment.class);
                controller = (BaseController) ctor.newInstance(new Object[] { fragment });
                fragment.getLifecycle().addObserver(controller);
                map.put(fragmentId, controller);
                return controllerClass.cast(controller);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    public void remove(UUID fragmentId) {
        if(map.containsKey(fragmentId)) {
            map.remove(fragmentId);
        }
    }
}
