package src;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private String customerName;
    private List<LineItem> lineItems;

    public Invoice(String customerName) {
        this.customerName = customerName;
        this.lineItems = new ArrayList<>();
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void addLineItem(LineItem lineItem) {
        this.lineItems.add(lineItem);
    }

    public double getTotalAmount() {
        double total = 0;
        for (LineItem item : lineItems) {
            total += item.getItemTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("===== INVOICE =====\n");
        sb.append("Customer: ").append(customerName).append("\n");
        sb.append("--------------------\n");
        for (LineItem item : lineItems) {
            sb.append(item.getProduct().getName()).append(" x ").append(item.getQuantity())
                    .append(" @ $").append(String.format("%.2f", item.getProduct().getPrice()))
                    .append(" = $").append(String.format("%.2f", item.getItemTotal())).append("\n");
        }
        sb.append("--------------------\n");
        sb.append("Total: $").append(String.format("%.2f", getTotalAmount())).append("\n");
        sb.append("=====================\n");
        return sb.toString();
    }
}