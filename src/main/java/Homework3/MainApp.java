package Homework3;

public class MainApp {

    public static void main(String[] args) {
        float[] xValues = {1e2f, 1e4f, 1e6f, 1e7f, 1e8f};

        System.out.printf("%-10s | %-16s | %-16s | %-16s%n",
                "x", "Наивный", "Устойчивый", "Отн. погрешность");
        System.out.println("");

        for (float x : xValues) {
            // Наивный алгоритм: sqrt(x + 1) - sqrt(x)
            float naive = (float) (Math.sqrt(x + 1.0f) - Math.sqrt(x));

            // Устойчивый алгоритм: 1 / (sqrt(x + 1) + sqrt(x))
            float stable = (float) (1.0f / (Math.sqrt(x + 1.0f) + Math.sqrt(x)));

            // Эталонное высокое качество (double)
            double exact = 1.0 / (Math.sqrt((double) x + 1.0) + Math.sqrt((double) x));

            // Относительная погрешность
            double relError = Math.abs(naive - exact) / exact;

            System.out.printf("%-10.0e | %-16.8e | %-16.8e | %-16.8e%n",
                    x, naive, stable, relError);
        }
    }
}