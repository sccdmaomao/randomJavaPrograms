package ui;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class DisplayPanel extends JPanel{

    JLabel history;
    JLabel current;
    LinkedList<String> memory;
    DisplayPanel(LinkedList<String> memory) {
        super();
        this.memory = memory;
        setBackground(Color.gray);
        setLayout(new GridLayout(2,1));
        setPreferredSize(new Dimension(200, 50));
        history = new JLabel();
        history.setAlignmentY(Label.CENTER);
        current = new JLabel("0");
        current.setAlignmentY(Label.CENTER);
        add(history);
        add(current);
    }

    public void reload() {
        String original = parse(memory.toString());
        switch (memory.getLast()) {
            case "*":
                history.setText(original);
                current.setText("");
                break;
            case "/":
                history.setText(original);
                current.setText("");
                break;
            case "+":
                history.setText(original);
                current.setText("");
                break;
            case "-":
                history.setText(original);
                current.setText("");
                break;
            default: // When input is a number
                if (current.getText() == "0" || current.getText() == null) {
                    current.setText(memory.getLast());
                }
                else {
                    current.setText(current.getText() + memory.getLast());
                }
        }

    }

    public void calculate() {
        String original = parse(memory.toString());
        history.setText(original);
        String ans = "ANSWER";
        current.setText(ans);
    }

    public void clear() {
        memory.clear();
        history.setText("");
        current.setText("");
    }

    public void negate() {
        if (current.getText().charAt(0) == '-') {
            current.setText(current.getText().substring(1));
        }
        else {
            current.setText("-" + current.getText());
        }
    }

    public void percent() {
        String number = current.getText();
        int integer = Integer.parseInt(number);
        double result = integer / 100.00;
        current.setText(Double.toString(result));
    }

    private String parse(String listOfNumber) {
        String result = "";
        for (int i = 0; i < listOfNumber.length(); i++) {
            if (listOfNumber.charAt(i) == '[' || listOfNumber.charAt(i) == ']'
                    || listOfNumber.charAt(i) == ',' || listOfNumber.charAt(i) == ' ') {
                continue;
            }
            else {
                result = result + listOfNumber.charAt(i);
            }
        }
        return result;
    }
}
