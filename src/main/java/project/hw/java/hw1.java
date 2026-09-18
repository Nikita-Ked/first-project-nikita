package project.hw.java;

import java.util.Random;
import java.util.Scanner;

public class hw1 {

    public static void greetings() {
        System.out.println("Hello");
        System.out.println("World");
        System.out.println("from");
        System.out.println("Java");
    }

    public static void checkSign(int a, int b, int c) {
        int sum = a + b + c;
        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void selectColor() {
        int data = 15;
        if (data <= 10) {
            System.out.println("Красный");
        } else if (data <= 20) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    public static void compareNumbers() {
        int a = 10;
        int b = 20;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    public static void addOrSubtractAndPrint(int initValue, int delta, boolean increment) {
        if (increment) {
            System.out.println(initValue + delta);
        } else {
            System.out.println(initValue - delta);
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Последовательный вызов методов ===");
        greetings();
        checkSign(5, -2, 3);
        selectColor();
        compareNumbers();
        addOrSubtractAndPrint(10, 5, true);

        System.out.println("\n======================================");


        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Введите число от 1 до 5 для запуска метода: ");
        int choice = scanner.nextInt();

        System.out.println("\n--- Результат выполнения случайного вызова ---");
        switch (choice) {
            case 1:
                greetings();
                break;
            case 2:
                int a = random.nextInt(101) - 50;
                int b = random.nextInt(101) - 50;
                int c = random.nextInt(101) - 50;
                System.out.println("Сгенерированы аргументы: a=" + a + ", b=" + b + ", c=" + c);
                checkSign(a, b, c);
                break;
            case 3:
                selectColor();
                break;
            case 4:
                compareNumbers();
                break;
            case 5:
                int initValue = random.nextInt(100);
                int delta = random.nextInt(50);
                boolean increment = random.nextBoolean();
                System.out.println("Сгенерированы аргументы: initValue=" + initValue +
                        ", delta=" + delta + ", increment=" + increment);
                addOrSubtractAndPrint(initValue, delta, increment);
                break;
            default:
                System.out.println("Ошибка: нужно ввести число от 1 до 5!");
                break;
        }

        scanner.close();
    }
}