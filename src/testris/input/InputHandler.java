package testris.input;

import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.HashMap;

public class InputHandler {

    private static InputHandler instance = null;
    public HashMap<Integer, String> keyMapping;
    public ArrayList<String> inputNames;

//    Ensure that only one instance can exist
    protected InputHandler() {}

    public static InputHandler getInstance() {
        if (instance == null) {
            instance = new InputHandler();
        }
        return instance;
    }

    public void setKeyBindings(HashMap mapping) {
        keyMapping = mapping;
    }

    private void pollInput() {
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            System.out.println("Exiting");
            System.exit(0);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            System.out.println("You hit space!");
        }

        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKey() == Keyboard.KEY_A) {
                    System.out.println("A Pressed");
                }
            } else {
                if (Keyboard.getEventKey() == Keyboard.KEY_A) {
                    System.out.println("A Key Released");
                }
            }
        }
    }
//    Set the names for values that we want the player to be able to input on
    public void setInputNames(ArrayList inNames) {
        inputNames = inNames;
    }
//    With the set of values that we want to the player to use return a dict or list of the
//    values that have been input in this iteration. For example if the player pressed the
//    key that corresponds to move the block left return that in either a list or a dict with
//    that key set to true for the input
//    Need to figure out the return type
    public HashMap<String, Boolean> getPressedInput() {
        HashMap<String, Boolean> pressedInput = new HashMap<>();
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                String inputPressed = keyMapping.get(Keyboard.getEventKey());
                if (inputPressed != null) {
                    pressedInput.put(inputPressed, true);
                }
            }
        }
        return pressedInput;
    }
}
