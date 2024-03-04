package org.example;

import java.util.ArrayList;
import java.util.List;

public class Orders {
    public static Orders instance;
    protected List<Order> orders;

    public Orders() {
        orders = new ArrayList<>();
    }

    public static Orders getInstance() {
        if (instance == null) {
            instance = new Orders();
        }
        return instance;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("Нет заказов.");
        } else {
            System.out.println("\nСписок заказов:\n");
            for (int i = 0; i < orders.size(); i++) {
                System.out.println("Заказ " + (i + 1) + ":");
                displayOrderDetails(orders.get(i));
                System.out.println();
            }
        }
        System.out.println();
    }

    private void displayOrderDetails(Order order) {
        System.out.println("Статус: " + order.getStatus());
        System.out.println("Список блюд:");
        List<Dish> dishes = order.getDishes();
        for (Dish dish : dishes) {
            System.out.println(dish.getName() + " - " + dish.getPrice());
        }
        System.out.println();
    }
}
