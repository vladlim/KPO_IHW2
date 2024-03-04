package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Menu implements Iterable<Dish> {
    private static Menu instance;
    private List<Dish> dishes;

    private Menu() {
        dishes = new ArrayList<>();
        dishes.add(new Dish("Борщ", 200, 20));
        dishes.add(new Dish("Омлет", 150, 10));
        dishes.add(new Dish("Салат", 120, 5));
    }

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public void editMenu() {
        Scanner scanner = new Scanner(System.in);
        int[] validChoices = {1, 2, 3};
        int choice = -1;

        System.out.println("""
                Выберите пункт:
                1. Посмотреть меню
                2. Добавить блюдо
                3. Удалить блюдо
                """);
        choice = UserMenu.getUserInput(scanner, validChoices);
        System.out.println();

        if (choice == 1) {
            Order.menu.displayMenu();
        } else if (choice == 2){
            Dish newDish = readDishFromInput();
            Order.menu.addDish(newDish);
        } else if (choice == 3) {
            if (Order.menu.dishes.isEmpty()) {
                System.out.println("Меню пустое.\n");
            } else {
                System.out.println("Укажите номер блюда в меню, которое хотите удалить:");
                Order.menu.displayMenu();
                int[] menuDishes = new int[Menu.getInstance().dishes.size()];
                for (int i = 0; i < menuDishes.length; i++) {
                    menuDishes[i] = i + 1;
                }
                int number = UserMenu.getUserInput(scanner, menuDishes);
                System.out.println();
                removeDish(number);
            }
        } else {
            System.out.println("Некорректный выбор!");
        }
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public void removeDish(int dishNumber) {
        if (dishNumber >= 1 && dishNumber <= dishes.size()) {
            dishes.remove(dishNumber - 1);
            System.out.println("Блюдо успешно удалено из меню.");
        } else {
            System.out.println("Некорректный номер блюда.");
        }
    }

    public void displayMenu(){
        System.out.println("Меню:");
        int i = 1;
        for (Dish dish : Order.menu.getDishes()) {
            System.out.println(i + ". " + dish.getName() + " - " + dish.getPrice());
            i++;
        }
        System.out.println();
    }

    private static Dish readDishFromInput() {
        Scanner scanner = new Scanner(System.in);
        String name = "";
        double price = -1;
        int cookingTime = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Введите данные в формате: <название> <цена> <время готовки>");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            if (parts.length == 3) {
                try {
                    name = parts[0];
                    price = Double.parseDouble(parts[1]);
                    cookingTime = Integer.parseInt(parts[2]);
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Некорректный формат цены или времени готовки. Повторите ввод.");
                }
            } else {
                System.out.println("Некорректный формат ввода. Повторите ввод.");
            }
        }
        return new Dish(name, price, cookingTime);
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    @Override
    public Iterator<Dish> iterator() {
        return dishes.iterator();
    }
}
