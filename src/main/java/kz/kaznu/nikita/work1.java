package kz.kaznu.nikita;

public class work1 {
    public static void main(String[] args) {
        // === 1. Пример с циклом while ===
        System.out.println("=== 1. Цикл while ===");
        int i = 1;
        while (i <= 5) {
            System.out.println("Итерация номер: " + i);
            i++;
        }

        System.out.println("\n------------------------------------\n");

        // === 2. Пример с одномерным массивом ===
        System.out.println("=== 2. Одномерный массив ===");
        int[] numbers = {10, 20, 30, 40, 50};

        for (int j = 0; j < numbers.length; j++) {
            System.out.println("Элемент с индексом " + j + " = " + numbers[j]);
        }
    }
}