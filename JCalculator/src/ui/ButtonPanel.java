package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {

    ButtonPanel(Map<String, Action> buttons, JFrame parent) {
        super();
        GridLayout layout = new GridLayout(5, 4);
        setPreferredSize(new Dimension(200, 250));
        setLayout(layout);  // 19 buttons in total
        for (final String caption : buttons.keySet()) {
            JButton button = new JButton(caption);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttons.get(caption).doAction();
                    parent.requestFocusInWindow();
                }
            });
            button.setPreferredSize(new Dimension(50, 50));
            add(button);
        }
    }
}
