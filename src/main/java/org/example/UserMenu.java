package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.io.File;

public class UserMenu {
    public static double totalRestaurantPrice = 0;
    public static List<Feedback> feedbacks = new ArrayList<>();

    public static int getUserInput(Scanner scanner, int[] validChoices) {
        int choice = -1;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.print("Ваш выбор: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                for (int validChoice : validChoices) {
                    if (choice == validChoice) {
                        isValidInput = true;
                        break;
                    }
                }
                if (!isValidInput) {
                    System.out.println("Некорректный ввод. Пожалуйста, Выберите нужный пункт.\n");
                }
            } else {
                System.out.println("Некорректный ввод. Пожалуйста, введите номер пункта.\n");
                scanner.next();
            }
        }

        return choice;
    }

    public static List<RestaurantClient> clients = new ArrayList<>();
    static void Autorization(){
        if(!Orders.getInstance().orders.isEmpty()){
            for(RestaurantClient client : clients) {
                if(client.order.getStatus().equals("Готов")){
                    client.payForOrder();
                    Orders.getInstance().removeOrder(client.order);
                    feedbackOffer();
                }
            }
        }
        Scanner scanner = new Scanner(System.in);
        int[] validchoices = {0, 1, 2};
        int choice;

        System.out.println("""
                \nВыберите свой статус:
                1. Администратор
                2. Клиент
                0. Завершить работу программы""");
        choice = getUserInput(scanner, validchoices);
        System.out.println();

        if (choice == 1) {
            RestaurantAdminInterface();
        } else if (choice == 2) {
            if (clients.isEmpty()){
                RestaurantClient newClient = new RestaurantClient();
                clients.add(newClient);
                System.out.println("Вы клиент " + newClient.id + "\n");
                RestaurantClientInterface(clients.get(0));
            } else {
                System.out.print("Вы авторизованы? [y/n]: ");
                String input = scanner.next();
                if(input.equals("y")) {
                    System.out.print("Вы клиент: ");
                    choice = scanner.nextInt();
                    if (choice > 0 && choice <= clients.size()) {
                        RestaurantClientInterface(clients.get(choice-1));
                    } else {
                        System.out.println("Некорректный выбор!");
                        Autorization();
                    }
                } else if (input .equals("n")){
                    RestaurantClient newClient = new RestaurantClient();
                    clients.add(newClient);
                    System.out.println("Вы клиент " + newClient.id + "\n");
                    RestaurantClientInterface(clients.get(newClient.id-1));
                } else {
                    System.out.println("Некорректный выбор!");
                    Autorization();
                }
            }
        } else if (choice == 0) {
            return;
        } else {
            System.out.println("Некорректный выбор!");
            Autorization();
        }
    }

    static void RestaurantAdminInterface(){
        Scanner scanner = new Scanner(System.in);
        int[] validchoices = {1, 2, 3, 4};
        int choice;


        System.out.println("""
                Выберите пункт:
                1. Посмотреть заказы
                2. Редактировать меню
                3. Вернуться к авторизации
                4. Посмотреть отзывы
                """);
        choice = getUserInput(scanner, validchoices);
        System.out.println();

        if (choice == 1) {
            Orders.getInstance().displayOrders();
            System.out.println();
            RestaurantAdminInterface();
        } else if (choice == 2) {
            Menu.getInstance().editMenu();
            RestaurantAdminInterface();
        } else if (choice == 3) {
            Autorization();
        } else if(choice == 4) {
            displayFeedbacks(feedbacks);
            Autorization();
        } else {
            System.out.println("Некорректный выбор!");
        }
    }

    static void RestaurantClientInterface(RestaurantClient client){
        Scanner scanner = new Scanner(System.in);
        int[] validchoices = {1, 2, 3, 4};
        int choice;

        System.out.println("""
                Выберите пункт:
                1. Сделать заказ
                2. Добавить блюдо в заказ
                3. Отменить заказ
                4. Вернуться к авторизации""");
        choice = getUserInput(scanner, validchoices);
        System.out.println();

        if (choice == 1) {
            if (Order.menu.getDishes().isEmpty()) {
                System.out.println("Извините, в меню пока нет блюд.");
            } else if (!client.order.getDishes().isEmpty()) {
                System.out.println("Вы уже сделали заказ.");
            } else {
                client.makeAnOrder();
            }
            RestaurantClientInterface(client);
        } else if (choice == 2) {
            if (Order.menu.getDishes().isEmpty()) {
                System.out.println("Извините, в меню пока нет блюд.");
            } else {
                if (client.order.getDishes().isEmpty()){
                    System.out.println("Вы еще не сделали заказ!");
                } else {
                    client.needWait = true;
                    Menu.getInstance().displayMenu();
                    client.addDishToOrder();
                    client.needWait = false;
                }
            }
            RestaurantClientInterface(client);
        } else if (choice == 3) {
            if (client.order.getDishes().isEmpty()){
                System.out.println("Вы еще не сделали заказ!");
            } else {
                client.order.cancelOrder();
                Orders.getInstance().removeOrder(client.order);
                client.orderClean();
            }
            Autorization();
        } else if (choice == 4) {
            Autorization();
        } else {
            System.out.println("Некорректный выбор!");
        }
    }

    public static void feedbackOffer(){
        System.out.println("""
    Хотите ли Вы оценить заказ?
    1. Да
    2. Нет
    """);
        int[] validChoices = {1, 2};
        Scanner scanner = new Scanner(System.in);
        int choice = UserMenu.getUserInput(scanner, validChoices);
        if (choice == 1) {
            int estimation = Feedback.getUsersEstimation();
            String comment = Feedback.getUsersComment();
            feedbacks.add(new Feedback(estimation, comment));
        } else {
            return;
        }
    }

    public static void displayFeedbacks(List<Feedback> feedbacks) {
        if(feedbacks.isEmpty()){
            System.out.println("\nОтзывов пока нет\n");
        } else {
            System.out.println("\nОтзывы:\n");
            for(Feedback feedback : feedbacks) {
                System.out.println("Оценка: " + feedback.estimation + "\nОтзыв: " + feedback.comment +"\n");
            }
        }
    }

    public static void saveMenuToCSV(String fileName) {
        Menu menu = Menu.getInstance();

        try {
            File folder = new File("menu_files");
            if (!folder.exists()) {
                folder.mkdir();
            }

            File file = new File(folder, fileName);


            try (FileWriter writer = new FileWriter(file)) {
                writer.append("Название,Цена,Время готовки (в секундах)\n");

                Iterator<Dish> iterator = menu.iterator();
                while (iterator.hasNext()) {
                    Dish dish = iterator.next();
                    writer.append(dish.getName()).append(",")
                            .append(String.valueOf(dish.getPrice())).append(",")
                            .append(String.valueOf(dish.getCookingTime())).append("\n");
                }

                System.out.println("Меню успешно сохранено в файл: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Ошибка при сохранении меню в файл: " + e.getMessage());
            }
        } catch (SecurityException e) {
            System.out.println("Ошибка доступа к файловой системе: " + e.getMessage());
        }
    }
}
