package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InvoiceGUI extends JFrame {
    private JTextField customerNameField;
    private JPanel itemsPanel;
    private JButton addItemButton;
    private JButton generateInvoiceButton;
    private JTextArea invoiceTextArea;
    private JScrollPane invoiceScrollPane;

    private List<JPanel> itemRows = new ArrayList<>();

    public InvoiceGUI() {
        setTitle("Invoice Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Customer Name
        JPanel customerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        customerPanel.add(new JLabel("Customer Name:"));
        customerNameField = new JTextField(20);
        customerPanel.add(customerNameField);
        add(customerPanel, BorderLayout.NORTH);

        // Items Panel
        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(itemsPanel), BorderLayout.CENTER);
        addItemRow(); // Add an initial item row

        // Add Item Button
        JPanel addItemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addItemButton = new JButton("Add Item");
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItemRow();
                revalidate();
                repaint();
            }
        });
        addItemPanel.add(addItemButton);
        add(addItemPanel, BorderLayout.WEST);

        // Invoice Text Area
        invoiceTextArea = new JTextArea(10, 30);
        invoiceScrollPane = new JScrollPane(invoiceTextArea);
        add(invoiceScrollPane, BorderLayout.SOUTH);

        // Generate Invoice Button
        generateInvoiceButton = new JButton("Generate Invoice");
        generateInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateInvoice();
            }
        });
        JPanel generatePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        generatePanel.add(generateInvoiceButton);
        add(generatePanel, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addItemRow() {
        JPanel itemRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField nameField = new JTextField(15);
        JTextField priceField = new JTextField(8);
        JTextField quantityField = new JTextField(5);

        itemRow.add(new JLabel("Product Name:"));
        itemRow.add(nameField);
        itemRow.add(new JLabel("Price: $"));
        itemRow.add(priceField);
        itemRow.add(new JLabel("Quantity:"));
        itemRow.add(quantityField);

        itemsPanel.add(itemRow);
        itemRows.add(itemRow);
    }

    private void generateInvoice() {
        String customerName = customerNameField.getText();
        Invoice invoice = new Invoice(customerName);

        for (JPanel rowPanel : itemRows) {
            Component[] components = rowPanel.getComponents();
            JTextField nameField = (JTextField) components[1];
            JTextField priceField = (JTextField) components[3];
            JTextField quantityField = (JTextField) components[5];

            String productName = nameField.getText();
            String priceText = priceField.getText();
            String quantityText = quantityField.getText();

            try {
                double price = Double.parseDouble(priceText);
                int quantity = Integer.parseInt(quantityText);
                Product product = new Product(productName, price);
                LineItem lineItem = new LineItem(product, quantity);
                invoice.addLineItem(lineItem);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid price or quantity entered.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        invoiceTextArea.setText(invoice.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InvoiceGUI();
            }
        });
    }
}