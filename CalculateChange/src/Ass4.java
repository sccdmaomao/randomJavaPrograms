import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ass4 {

    static List<String> foodsOrder = new ArrayList<String>();
    static List<String> foods = new ArrayList<String>();
    static ArrayList<Double> prices = new ArrayList<Double>();
    static boolean isPrice = false;
    static double cost;
    static String hint = "\t Enter a food: \n" +
            "Or enter End to finish the order.";
    static JTextArea inputHint;

    public static void main(String args[]) throws FileNotFoundException {

        // Read file data

        Scanner reader = new Scanner(new FileReader("Foods.txt"));
        String line;
        while (reader.hasNextLine()) {
            line = reader.nextLine();
            foods.add(line);
        }
        reader.close();
        Scanner s = new Scanner(new File("price.txt"));
        while (s.hasNext()) {
            prices.add(s.nextDouble());
        }
        s.close();

        // Create UI components

        // Create main frame
        JFrame frame = new JFrame("A4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        JPanel rootPanel = new JPanel();
        rootPanel.setPreferredSize(new Dimension(600, 400));
        rootPanel.setLayout(new GridBagLayout());
        frame.setSize(600, 400);
        frame.setResizable(false);
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        frame.getContentPane().add(rootPanel);


        // Create components
        GridBagConstraints c;

        // Create menu
        String menuText = "\t Menu";
        for (int i = 0; i < foods.size(); i++) {
            String food = foods.get(i);
            Double price = prices.get(i);
            String newLine = "\n " + food + ": \t $" + price;
            menuText = menuText + newLine;
        }
        JTextArea menu = new JTextArea(menuText);
        menu.setEnabled(false);
        menu.setPreferredSize(new Dimension(270, 350));
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 2;
        c.insets = new Insets(3, 3, 3, 3);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
        menu.setBorder(border);
        rootPanel.add(menu, c);

        // Create Hint

        inputHint = new JTextArea(hint);
        Color color = rootPanel.getBackground();
        inputHint.setBackground(color);
        inputHint.setEnabled(false);
        inputHint.setPreferredSize(new Dimension(250, 200));
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        rootPanel.add(inputHint, c);

        // Create input
        JTextField foodInput = new JTextField();  // 1,2
        foodInput.setPreferredSize(new Dimension(300, 150));
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        rootPanel.add(foodInput, c);


        // Key listener
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    // Enter
                    if (isPrice) {
                        String input = foodInput.getText();
                        double paid = -1;
                        try {
                            paid = Double.parseDouble(input);
                        } catch (Exception exception) {
                            JOptionPane.showMessageDialog(frame, "Please enter numbers only.");
                        }
                        foodInput.setText("");
                        if (paid != -1) {
                            showChange(paid);
                        }
                    } else {
                        String input = foodInput.getText();
                        if (!input.toLowerCase().equals("end")) {
                            foodInput.setText("");
                            foodsOrder.add(input);

                            if (foods.contains(input)) {
                                double price = prices.get(foods.indexOf(input));
                                JOptionPane.showMessageDialog(frame,
                                        "Order Placed: " + input + " which costs $ " + price,
                                        "Confirmation",
                                        JOptionPane.PLAIN_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(frame, "It's not a valid order");
                            }

                        } else {
                            cost = calculateCost();
                            foodInput.setText("");
                            String message = "Enter the $-amount tendered: " + "\n"
                                    + "your cost: " + cost;
                            inputHint.setText(message);
                            isPrice = true;
                        }
                    }

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        foodInput.addKeyListener(keyListener);

        frame.pack();
        frame.setVisible(true);
    }

    public static double calculateCost() {
        double sum_cost = 0;
        for (String food: foodsOrder) {
            int index = foods.indexOf(food);
            if (index > -1) {
                sum_cost += prices.get(index);
            }
        }
        return sum_cost;
    }

    static void showChange(double paid) {
        double change = paid - cost;
        double changeDue = change;
        int hundries = (int) change / 100;
        change = change % 100;
        int fifties = (int) change / 50;
        change = change % 50;
        int twenties = (int) change / 20;
        change = change % 20;
        int tens = (int) change / 10;
        change = change % 10;
        int fives = (int) change / 5;
        change = change % 5;
        int ones = (int) change / 1;
        change = change % 1;
        double twentyfivecents = change / 0.25;
        change = change % 0.25;
        double tencents = change / 0.1;
        change = change % 0.1;
        double fivecents = change / 0.05;
        change = change % 0.05;

        String receipt = "100 * " + hundries + "\n" +
                "50* " + fifties + "\n" +
                "20* " + twenties + "\n" +
                "10* " + tens + "\n" +
                "5* " + fives + "\n" +
                "1* " + ones + "\n" +
                "0.25* " + twentyfivecents + "\n" +
                "0.1* " + tencents + "\n" +
                "0.05* " + fivecents + "\n" +
                "Cost of Item $" + cost + "\n" +
                "Amount Paid $" + paid + "\n" +
                "Change Due $" + changeDue + "";
        JOptionPane.showMessageDialog(null, receipt);

        restart();
    }

    static void restart() {
        foodsOrder = new ArrayList<String>();
        isPrice = false;
        inputHint.setText(hint);
        cost = 0;
    }
}

