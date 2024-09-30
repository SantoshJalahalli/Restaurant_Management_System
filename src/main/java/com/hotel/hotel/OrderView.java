//package com.hotel.hotel;
//
//
//
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.grid.Grid;
//import com.vaadin.flow.component.notification.Notification;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.textfield.NumberField;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.router.Route;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Route("order")
//public class OrderView extends VerticalLayout {
//    private final List<MenuItem> menuItems;
//    private final Grid<MenuItem> grid;
//    private final TextField tableNumberField;
//    private final Button submitButton;
//    private final RestTemplate restTemplate;
//
//    public OrderView() {
//        menuItems = new ArrayList<>();
//        restTemplate = new RestTemplate();
//
//        // Initialize UI components
//        tableNumberField = new TextField("Table Number");
//        tableNumberField.setPlaceholder("Enter Table Number");
//
//        grid = new Grid<>(MenuItem.class);
//        grid.setItems(loadMenuItems());
//        grid.setColumns("item", "price", "quantity");
//
//        // Editable column for Quantity
//        grid.getColumns().forEach(column -> column.setAutoWidth(true));
//        grid.addComponentColumn(menuItem -> {
//            NumberField quantityField = new NumberField();
//            quantityField.setMin(0);
//            quantityField.setValue(0.0);
//            quantityField.addValueChangeListener(event -> menuItem.setQuantity(event.getValue().intValue()));
//            return quantityField;
//        }).setHeader("Quantity");
//
//        submitButton = new Button("Submit Order", event -> submitOrder());
//        add(tableNumberField, grid, submitButton);
//    }
//
//    private List<MenuItem> loadMenuItems() {
//        menuItems.add(new MenuItem("Pizza", 10.0));
//        menuItems.add(new MenuItem("Pasta", 15.0));
//        menuItems.add(new MenuItem("Salad", 5.0));
//        menuItems.add(new MenuItem("Burger", 8.0));
//        menuItems.add(new MenuItem("Sandwich", 6.0));
//        return menuItems;
//    }
//
//    private void submitOrder() {
//        String tableNumber = tableNumberField.getValue();
//        if (tableNumber.isEmpty()) {
//            Notification.show("Please enter a table number.");
//            return;
//        }
//
//        List<MenuItem> orderedItems = new ArrayList<>();
//        for (MenuItem item : menuItems) {
//            if (item.getQuantity() > 0) {
//                orderedItems.add(item);
//            }
//        }
//
//        if (orderedItems.isEmpty()) {
//            Notification.show("Please select at least one item.");
//            return;
//        }
//
//        // Create the order payload
//        OrderRequest orderRequest = new OrderRequest(
//                String.join(", ", getOrderedItemsNames(orderedItems)),
//                String.join(", ", getOrderedItemsPrices(orderedItems)),
//                String.join(", ", getOrderedItemsQuantities(orderedItems)),
//                Integer.parseInt(tableNumber)
//        );
//
//        // Make a POST request to the backend
//        try {
//            restTemplate.postForEntity("http://localhost:8080/api/orders/place", orderRequest, String.class);
//            Notification.show("Order placed successfully!");
//        } catch (Exception e) {
//            Notification.show("Failed to place order.");
//        }
//    }
//
//    private List<String> getOrderedItemsNames(List<MenuItem> items) {
//        List<String> names = new ArrayList<>();
//        items.forEach(item -> names.add(item.getItem()));
//        return names;
//    }
//
//    private List<String> getOrderedItemsPrices(List<MenuItem> items) {
//        List<String> prices = new ArrayList<>();
//        items.forEach(item -> prices.add(String.valueOf(item.getPrice())));
//        return prices;
//    }
//
//    private List<String> getOrderedItemsQuantities(List<MenuItem> items) {
//        List<String> quantities = new ArrayList<>();
//        items.forEach(item -> quantities.add(String.valueOf(item.getQuantity())));
//        return quantities;
//    }
//}
//
//class MenuItem {
//    private String item;
//    private double price;
//    private int quantity;
//
//    public MenuItem() {}
//
//    public MenuItem(String item, double price) {
//        this.item = item;
//        this.price = price;
//        this.quantity = 0;
//    }
//
//    public String getItem() {
//        return item;
//    }
//
//    public void setItem(String item) {
//        this.item = item;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//}
//
//class OrderRequest {
//    private String items;
//    private String price;
//    private String quantity;
//    private int tableNumber;
//
//    public OrderRequest(String items, String price, String quantity, int tableNumber) {
//        this.items = items;
//        this.price = price;
//        this.quantity = quantity;
//        this.tableNumber = tableNumber;
//    }
//
//    public String getItems() {
//        return items;
//    }
//
//    public void setItems(String items) {
//        this.items = items;
//    }
//
//    public String getPrice() {
//        return price;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
//
//    public String getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(String quantity) {
//        this.quantity = quantity;
//    }
//
//    public int getTableNumber() {
//        return tableNumber;
//    }
//
//    public void setTableNumber(int tableNumber) {
//        this.tableNumber = tableNumber;
//    }
//}
