package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order {
    public static Menu menu = Menu.getInstance();
    private List<Dish> dishes;
    private String status;

    public Order() {
        dishes = new ArrayList<>();
        status = "Принят";
    }

    public void addDish() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Выберите номер блюда для добавления в заказ: ");
        int choice = scanner.nextInt();
        if (choice > 0 && choice <= menu.getDishes().size()) {
            Dish selectedDish = menu.getDishes().get(choice - 1);
            System.out.println("Вы выбрали: " + selectedDish.getName() + "\n");
            if (status.equals("Готов")){
                System.out.println("Извините, заказ уже готов.\n");
            } else {
                dishes.add(selectedDish);
            }
        } else {
            System.out.println("Некорректный выбор.");
        }
    }

    public void cancelOrder() {
        if (!status.equals("Готов")) {
            status = "Отменен";
            System.out.println("Заказ успешно отменен.");
        } else {
            System.out.println("Невозможно отменить заказ. Заказ уже готов.");
        }
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String value) {
        status = value;
    }
}
