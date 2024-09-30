//package com.hotel.hotel;
//
//
//
//import com.vaadin.flow.component.grid.Grid;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.router.Route;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
//@Route("kitchen")
//public class KitchenView extends VerticalLayout {
//    private final Grid<KitchenOrder> kitchenOrderGrid;
//    private final RestTemplate restTemplate;
//
//    public KitchenView() {
//        kitchenOrderGrid = new Grid<>(KitchenOrder.class);
//        kitchenOrderGrid.setColumns("tableNumber", "items", "quantity");
//        restTemplate = new RestTemplate();
//
//        add(kitchenOrderGrid);
//        loadKitchenOrders();
//    }
//
//    private void loadKitchenOrders() {
//        List<KitchenOrder> orders = restTemplate.getForObject("http://localhost:8080/api/orders/kitchen", List.class);
//        kitchenOrderGrid.setItems(orders);
//    }
//}
//
//class KitchenOrder {
//    private int tableNumber;
//    private String items;
//    private String quantity;
//
//    public KitchenOrder() {}
//
//    public KitchenOrder(int tableNumber, String items, String quantity) {
//        this.tableNumber = tableNumber;
//        this.items = items;
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
//
//    public String getItems() {
//        return items;
//    }
//
//    public void setItems(String items) {
//        this.items = items;
//    }
//
//    public String getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(String quantity) {
//        this.quantity = quantity;
//    }
//}
//
