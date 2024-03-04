package org.example;

import java.util.Iterator;
import java.util.Scanner;

public class RestaurantClient {
    boolean needWait = false;
    public static int numberOfClient = 1;
    int id;
    Thread thread;
    Order order;
    double orderPrice = 0;

    public int totalTime;
    public int remainingTime;
    public void orderMaking() {
        thread = new Thread(() -> {
            int counter = totalTime;
            while (counter != 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                --counter;
                if(needWait) {
                    while(totalTime == remainingTime){
                        totalTime=totalTimeCount(order);
                    }
                    counter += totalTime-remainingTime;
                    remainingTime = totalTime;
                }
            }
            order.setStatus("Готов");
        });
        thread.start();
    }

    public RestaurantClient() {
        id = numberOfClient;
        numberOfClient++;
        order = new Order();
    }

    public void makeAnOrder() {
        Scanner scanner = new Scanner(System.in);
        int[] validChoices = {1, 2};
        int choice = -1;
        while(choice != 2) {
            System.out.println("""
                    Выберите пункт:
                    1. Добавить блюдо
                    2. Завершить составление заказа\n""");
            choice = UserMenu.getUserInput(scanner, validChoices);
            if (choice == 1) {
                Order.menu.displayMenu();
                order.addDish();
            } else if (choice == 2){
                if (order.getDishes().isEmpty()){
                    System.out.println("Вы не заказали ни одного блюда.\n");
                    choice = -1;
                } else {
                    order.setStatus("Готовится");
                    Orders.getInstance().addOrder(order);
                    totalTime = totalTimeCount(order);
                    remainingTime = totalTime;
                    System.out.println("\nЗаказ клиента " + id + " готовится...\n");
                    orderMaking();
                }
            } else {
                System.out.println("Некорректный выбор!");
            }
        }
    }

    public synchronized void addDishToOrder(){
        if (order.getStatus().equals("Готов")){
            System.out.println("Заказ уже готов. Невозможно добавить блюдо.\n");
        } else {
            order.addDish();
        }
        return;
    }

    public int totalTimeCount(Order order){
        int totalTime = 0;
        for (Dish dish : order.getDishes()){
            totalTime += dish.getCookingTime();
        }
        return totalTime;
    }

    public void payForOrder(){
        orderPrice = orderPriceCount();
        System.out.println("\nЗаказ клиента " + id + " готов.\nСумма: " + orderPriceCount());
        System.out.println("Введите '1' для оплаты: ");
        Scanner scanner = new Scanner(System.in);
        int[] validChoices = {1};
        UserMenu.getUserInput(scanner, validChoices);
        UserMenu.totalRestaurantPrice+=orderPrice;
    }

    public double orderPriceCount(){
        double counter = 0;
        for (Dish dish : order.getDishes()){
            counter += dish.getPrice();
        }
        return counter;
    }

    public void orderClean(){
        order = new Order();
        order.setStatus("Принят");
    }
}
