package MVC;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    public BaseController   getControllerForLifecycleOwner(PMLifecycleRegistryOwner owner) {
        BaseController controller;
        UUID identifier = owner.getIdentifier();
        if(map.containsKey(identifier)) {
            controller = map.get(identifier);
            return controller;
        } else {
            return null;
        }
    }

    public BaseController   getControllerForLifecycleOwner(UUID ownerId) {
        BaseController controller;
        if(map.containsKey(ownerId)) {
            controller = map.get(ownerId);
            return controller;
        } else {
            return null;
        }
    }

    public <T extends BaseController> T createControllerForLifecycleOwner(PMLifecycleRegistryOwner owner, Class<T> controllerClass) {
        BaseController controller;
        UUID identifier = owner.getIdentifier();
        if(map.containsKey(identifier)) {
            controller = map.get(identifier);
            controller.setLifecycleRegistryOwner(owner);
            owner.getLifecycle().addObserver(controller);
            return controllerClass.cast(controller);
        } else {
            Constructor<?> ctor = null;
            try {
                Class clzz = owner.getViewInterface();
                if(clzz.isAssignableFrom(owner.getClass())){
                    ctor = controllerClass.getConstructor(owner.getViewInterface());
                    controller = (BaseController) ctor.newInstance(new Object[] { owner });
                    owner.getLifecycle().addObserver(controller);
                    map.put(identifier, controller);
                    return controllerClass.cast(controller);
                }else{
                    throw new RuntimeException("Class: " + owner.getClass() + " is not implementing " + owner.getViewInterface());
                }

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
            BaseController controller = map.get(fragmentId);
            controller.setControllerState(false);
            map.remove(fragmentId);
        }
    }
}
