import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PizzaGUIFrame extends JFrame implements ActionListener {
    private JPanel mainPanel, crustPanel, sizePanel, toppingsPanel, orderPanel, buttonPanel;
    private JRadioButton thinCrust, regularCrust, deepDish;
    private JComboBox<String> sizeComboBox;
    private JCheckBox topping1, topping2, topping3, topping4, topping5, topping6;
    private JTextArea orderTextArea;
    private JButton orderButton, clearButton, quitButton;

    public PizzaGUIFrame() {
        setTitle("Pizza Order");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mainPanel = new JPanel(new GridLayout(4, 1));

        // Crust Panel
        crustPanel = new JPanel(new GridLayout(1, 3));
        crustPanel.setPreferredSize(new Dimension(400, 50)); // Set preferred size for crust panel
        crustPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Crust", TitledBorder.CENTER, TitledBorder.TOP));
        thinCrust = new JRadioButton("Thin");
        regularCrust = new JRadioButton("Regular");
        deepDish = new JRadioButton("Deep-dish");
        ButtonGroup crustGroup = new ButtonGroup();
        crustGroup.add(thinCrust);
        crustGroup.add(regularCrust);
        crustGroup.add(deepDish);
        crustPanel.add(thinCrust);
        crustPanel.add(regularCrust);
        crustPanel.add(deepDish);

        // Size Panel
        sizePanel = new JPanel();
        sizePanel.setPreferredSize(new Dimension(100, 20)); // Set preferred size for size panel
        sizePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Size", TitledBorder.CENTER, TitledBorder.TOP));
        String[] sizes = {"Small", "Medium", "Large", "Super"};
        sizeComboBox = new JComboBox<>(sizes);
        sizePanel.add(sizeComboBox);

        // Toppings Panel
        toppingsPanel = new JPanel(new GridLayout(2, 3));
        toppingsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Toppings", TitledBorder.CENTER, TitledBorder.TOP));
        topping1 = new JCheckBox("Pepperoni");
        topping2 = new JCheckBox("Mushrooms");
        topping3 = new JCheckBox("Olives");
        topping4 = new JCheckBox("Pineapple");
        topping5 = new JCheckBox("Bacon");
        topping6 = new JCheckBox("Anchovies");
        toppingsPanel.add(topping1);
        toppingsPanel.add(topping2);
        toppingsPanel.add(topping3);
        toppingsPanel.add(topping4);
        toppingsPanel.add(topping5);
        toppingsPanel.add(topping6);

        // Order Panel
        orderPanel = new JPanel(new BorderLayout()); // Changed layout to BorderLayout
        orderPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Order Summary", TitledBorder.CENTER, TitledBorder.TOP));
        orderTextArea = new JTextArea(15, 30);
        orderTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderTextArea); // Added JScrollPane
        scrollPane.setPreferredSize(new Dimension(400, 300)); // Increased size of the JScrollPane
        orderPanel.add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        buttonPanel = new JPanel(new FlowLayout());
        orderButton = new JButton("Order");
        clearButton = new JButton("Clear");
        quitButton = new JButton("Quit");
        orderButton.addActionListener(this);
        clearButton.addActionListener(this);
        quitButton.addActionListener(this);
        buttonPanel.add(orderButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(quitButton);

        mainPanel.add(crustPanel);
        mainPanel.add(sizePanel);
        mainPanel.add(toppingsPanel);
        mainPanel.add(orderPanel);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == orderButton) {
            generateOrder();
        } else if (e.getSource() == clearButton) {
            clearForm();
        } else if (e.getSource() == quitButton) {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    private void generateOrder() {
        StringBuilder order = new StringBuilder("----- Pizza Order -----\n\n");
        double baseCost = 0;
        double toppingCost = 0;
        double subTotal = 0;
        double tax = 0.07;
        double totalCost = 0;

        // Calculate base cost
        switch (sizeComboBox.getSelectedIndex()) {
            case 0:
                baseCost = 8.00;
                break;
            case 1:
                baseCost = 12.00;
                break;
            case 2:
                baseCost = 16.00;
                break;
            case 3:
                baseCost = 20.00;
                break;
        }

        // Add crust cost
        if (thinCrust.isSelected()) {
            order.append("Crust: Thin\n\n");
        } else if (regularCrust.isSelected()) {
            order.append("Crust: Regular\n\n");
        } else if (deepDish.isSelected()) {
            order.append("Crust: Deep-dish\n\n");
        }

        // Add toppings and calculate topping cost
        order.append("Toppings:\n\n");
        if (topping1.isSelected()) {
            order.append("- Pepperoni\n");
            toppingCost += 1.00;
        }
        if (topping2.isSelected()) {
            order.append("- Mushrooms\n");
            toppingCost += 1.00;
        }
        if (topping3.isSelected()) {
            order.append("- Olives\n");
            toppingCost += 1.00;
        }
        if (topping4.isSelected()) {
            order.append("- Pineapple\n");
            toppingCost += 1.00;
        }
        if (topping5.isSelected()) {
            order.append("- Bacon\n");
            toppingCost += 1.00;
        }
        if (topping6.isSelected()) {
            order.append("- Anchovies\n");
            toppingCost += 1.00;
        }

        // Add horizontal line after "Pizza Order"
        order.append("--------------------------\n");

        // Calculate sub-total
        subTotal = baseCost + toppingCost;

        // Format order
        order.append(String.format("\nBase Cost: $%.2f\n", baseCost));
        order.append(String.format("Topping Cost: $%.2f\n", toppingCost));
        order.append(String.format("Sub-Total: $%.2f\n", subTotal));

        // Add horizontal line before "Total Cost"
        order.append("--------------------------\n");

        // Calculate total cost
        totalCost = subTotal * (1 + tax);

        order.append(String.format("Tax: $%.2f\n", subTotal * tax));
        order.append("--------------------------\n");
        order.append(String.format("Total Cost: $%.2f\n", totalCost));
        order.append("================\n");

        orderTextArea.setText(order.toString());
    }

    private void clearForm() {
        thinCrust.setSelected(false);
        regularCrust.setSelected(false);
        deepDish.setSelected(false);
        sizeComboBox.setSelectedIndex(0);
        topping1.setSelected(false);
        topping2.setSelected(false);
        topping3.setSelected(false);
        topping4.setSelected(false);
        topping5.setSelected(false);
        topping6.setSelected(false);
        orderTextArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PizzaGUIFrame());
    }
}
