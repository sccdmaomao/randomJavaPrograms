package ui;

import java.util.LinkedList;
import java.util.Map;
import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {

    private final int WIDTH = 200;
    private final int HEIGHT = 300;
    DisplayPanel display_panel;

    public Calculator(Map<String, Action> buttons, LinkedList<String>  memory) {
        super("My Calculator");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));   // 2 rows 1 column.

        display_panel = new DisplayPanel(memory);

        ButtonPanel buttons_panel = new ButtonPanel(buttons, this);

        add(display_panel);
        add(buttons_panel);

        pack();
        setVisible(true);
    }

    public DisplayPanel getDisplayPanel() {
        return display_panel;
    }
}
