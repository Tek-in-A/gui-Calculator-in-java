import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator {

    private JFrame frame;
    private JTextField display;
    private JPanel panel;
    private double result;
    private String lastCommand;
    private boolean start;

    public Calculator() {
        frame = new JFrame("Calculator");
        display = new JTextField("0");
        panel = new JPanel();
        result = 0;
        lastCommand = "=";
        start = true;

        panel.setLayout(new GridLayout(4, 4));

        ActionListener insert = new InsertAction();
        ActionListener command = new CommandAction();

        addButton("7", insert);
        addButton("8", insert);
        addButton("9", insert);
        addButton("/", command);

        addButton("4", insert);
        addButton("5", insert);
        addButton("6", insert);
        addButton("*", command);

        addButton("1", insert);
        addButton("2", insert);
        addButton("3", insert);
        addButton("-", command);

        addButton("0", insert);
        addButton(".", insert);
        addButton("=", command);
        addButton("+", command);

        frame.add(display, "North");
        frame.add(panel, "Center");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    private void addButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        panel.add(button);
    }

    private class InsertAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String input = event.getActionCommand();
            if (start) {
                display.setText("");
                start = false;
            }
            display.setText(display.getText() + input);
        }
    }

    private class CommandAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();
            if (start) {
                if (command.equals("-")) {
                    display.setText(command);
                    start = false;
                } else {
                    lastCommand = command;
                }
            } else {
                calculate(Double.parseDouble(display.getText()));
                lastCommand = command;
                start = true;
            }
        }
    }

    public void calculate(double x) {
        if (lastCommand.equals("+")) {
            result += x;
        } else if (lastCommand.equals("-")) {
            result -= x;
        } else if (lastCommand.equals("*")) {
            result
