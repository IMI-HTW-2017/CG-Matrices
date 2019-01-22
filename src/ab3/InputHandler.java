package ab3;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;

class InputHandler {

    private List<KeyAction> keyActions;

    InputHandler(long window) {
        keyActions = new ArrayList<>();
        glfwSetKeyCallback(window, ((window1, key, scancode, action, mods) -> {
            for (KeyAction keyAction : keyActions)
                if (keyAction.getKeyCode() == key)
                    keyAction.setPressed(action != GLFW_RELEASE);
        }));
    }

    void registerKeyCallback(int keyCode, Runnable callback) {
        keyActions.add(new KeyAction(keyCode, callback));
    }

    void processInput() {
        for (KeyAction keyAction :
                keyActions) {
            keyAction.executeAction();
        }
    }

    private class KeyAction {
        private int keyCode;
        private boolean pressed;
        private Runnable actionCallback;

        private KeyAction(int keyCode, Runnable actionCallback) {
            this.keyCode = keyCode;
            this.actionCallback = actionCallback;
            pressed = false;
        }

        private void setPressed(boolean pressed) {
            this.pressed = pressed;
        }

        private int getKeyCode() {
            return keyCode;
        }

        private void executeAction() {
            if (pressed)
                actionCallback.run();
        }
    }
}
