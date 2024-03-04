package org.example;


public class Main {
    public static void main(String[] args) {
        UserMenu.Autorization();
        System.out.println("Общая выручка: " + UserMenu.totalRestaurantPrice);
        String fileName = "menu.csv";
        UserMenu.saveMenuToCSV(fileName);
    }
}
