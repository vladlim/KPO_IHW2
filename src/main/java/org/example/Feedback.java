package org.example;

import java.util.Scanner;

public class Feedback {
    int estimation;
    String comment;

    public Feedback(int estimation, String comment){
        this.estimation = estimation;
        this.comment = comment;
    }

    public static int getUsersEstimation() {
        System.out.println("Дайте оценку от 1 до 5 своему заказу: ");
        int[] validEstimation = {1, 2, 3, 4, 5};
        Scanner scanner = new Scanner(System.in);
        int estimation = -1;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.print("Ваша оценка: ");
            if (scanner.hasNextInt()) {
                estimation = scanner.nextInt();
                for (int validChoice : validEstimation) {
                    if (estimation == validChoice) {
                        isValidInput = true;
                        break;
                    }
                }
                if (!isValidInput) {
                    System.out.println("Некорректный ввод. Пожалуйста, оцените заказ.\n");
                }
            } else {
                System.out.println("Некорректный ввод. Пожалуйста, введите число от 1 до 5.\n");
                scanner.next();
            }
        }

        return estimation;
    }

    public static String getUsersComment() {
        System.out.println("Оставьте отзыв одной строкой:");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
