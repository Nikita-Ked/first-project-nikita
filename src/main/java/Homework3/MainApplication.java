package Homework3;

public class MainApplication {

    public static void main(String[] args) {
        // 1. Поиск машинного eps для float
        float epsFloat = 1.0f;
        while ((1.0f + (epsFloat / 2.0f)) > 1.0f) {
            epsFloat /= 2.0f;
        }
        System.out.println("Вычисленное eps (float): " + epsFloat);
        System.out.println("Теоретическое 2^-23:     " + (float) Math.pow(2, -23));

        // 2. Поиск машинного eps для double
        double epsDouble = 1.0;
        while ((1.0 + (epsDouble / 2.0)) > 1.0) {
            epsDouble /= 2.0;
        }
        System.out.println("Вычисленное eps (double): " + epsDouble);
        System.out.println("Теоретическое 2^-52:      " + Math.pow(2, -52));

        // 3. Суммирование 0.1f в типе float
        float sum = 0.0f;
        float addend = 0.1f;
        long count = 0;

        while (true) {
            float newSum = sum + addend;
            if (newSum == sum) {
                break;
            }
            sum = newSum;
            count++;
        }
        System.out.println("Сумма перестала изменяться при N = " + count);
        System.out.println("Итоговая сумма: " + sum);
    }
}