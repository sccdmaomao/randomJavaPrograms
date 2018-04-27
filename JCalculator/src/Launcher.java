import ui.Calculator;

import ui.Action;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Launcher {

    private static Map<String, Action> buttons;
    private static  LinkedList<String> memory;
    private static Calculator mainWindow;

    public static void main(String[] args) {
        buttons = new HashMap<>();
        memory = new LinkedList<String>();
        /*
            Construct the number buttons
         */
        addNumberButtons();
        addArithmeticButtons();
        addEqualSign();
        addSpecialButtons();

        mainWindow = new Calculator(buttons, memory);
    }

    private static void addNumberButtons() {
        for (int i=0; i<10;i++) {
            String number = String.valueOf(i);
            Action action = new Action() {
                @Override
                public void doAction() {
                    memory.add(number);
                    mainWindow.getDisplayPanel().reload();
                }
            };
            buttons.put(String.valueOf(i), action);
        }
    }

    private static void addArithmeticButtons() {
        String divide = "/";
        String multiply = "*";
        String addition = "+";
        String subtract = "-";

        Action divideAction = new Action() {
            @Override
            public void doAction() {
                memory.add(divide);
                mainWindow.getDisplayPanel().reload();
            }
        };
        buttons.put(divide, divideAction);

        Action multiplyAction = new Action() {
            @Override
            public void doAction() {
                memory.add(multiply);
                mainWindow.getDisplayPanel().reload();
            }
        };
        buttons.put(multiply, multiplyAction);

        Action addAction = new Action() {
            @Override
            public void doAction() {
                memory.add(addition);
                mainWindow.getDisplayPanel().reload();
            }
        };
        buttons.put(addition, addAction);

        Action subtractAction = new Action() {
            @Override
            public void doAction() {
                memory.add(subtract);
                mainWindow.getDisplayPanel().reload();
            }
        };
        buttons.put(subtract, subtractAction);
    }

    private static void addEqualSign() {
        String equal = "=";
        Action equalAction = new Action() {
            @Override
            public void doAction() {
                memory.add(equal);
                mainWindow.getDisplayPanel().calculate();
            }
        };
        buttons.put(equal, equalAction);
    }

    private static void addSpecialButtons() {
        String clear = "AC";
        Action clearAction = new Action() {
            @Override
            public void doAction() {
                mainWindow.getDisplayPanel().clear();
            }
        };
        buttons.put(clear, clearAction);

        String negate = "+/-";
        Action negateAction = new Action() {
            @Override
            public void doAction() {
                mainWindow.getDisplayPanel().negate();
            }
        };
        buttons.put(negate, negateAction);

        String dot = ".";
        Action dotAction = new Action() {
            @Override
            public void doAction() {
                memory.add(dot);
                mainWindow.getDisplayPanel().reload();
            }
        };
        buttons.put(dot, dotAction);

        String percent = "%";
        Action percentAction = new Action() {
            @Override
            public void doAction() {
                mainWindow.getDisplayPanel().percent();
            }
        };
        buttons.put(percent, percentAction);
    }
}
