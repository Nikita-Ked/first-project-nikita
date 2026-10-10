package Homework3;

public class MainApp {

    public static void main(String[] args) {
        // --- Численный эксперимент: Вариант 3 ---
        float[] xValues = {1e2f, 1e4f, 1e6f, 1e7f, 1e8f};

        System.out.printf("%-10s | %-16s | %-16s | %-16s%n",
                "x", "Наивный", "Устойчивый", "Отн. погрешность");
        System.out.println("-------------------------------------------------------------------------");

        for (float x : xValues) {
            float naive = (float) (Math.sqrt(x + 1.0f) - Math.sqrt(x));
            float stable = (float) (1.0f / (Math.sqrt(x + 1.0f) + Math.sqrt(x)));
            double exact = 1.0 / (Math.sqrt((double) x + 1.0) + Math.sqrt((double) x));
            double relError = Math.abs(naive - exact) / exact;

            System.out.printf("%-10.0e | %-16.8e | %-16.8e | %-16.8e%n",
                    x, naive, stable, relError);
        }

        System.out.println("\n-------------------------------------------------------------------------");

        // --- Численный эксперимент: Вариант 11 ---
        float[][] testPoints = {
                {3.0f, 4.0f},
                {1e19f, 1e19f},
                {1e20f, 1e20f},
                {1e30f, 1e30f}
        };

        System.out.printf("%-18s | %-16s | %-16s | %-16s%n",
                "Координаты (x, y)", "Наивный (float)", "Метод Math.hypot", "Эталон (double)");
        System.out.println("-------------------------------------------------------------------------");

        for (float[] point : testPoints) {
            float x = point[0];
            float y = point[1];

            float naive = (float) Math.sqrt(x * x + y * y);
            float stable = (float) Math.hypot(x, y);
            double exact = Math.hypot((double) x, (double) y);

            System.out.printf("(%-6.0e, %-6.0e)  | %-16.8e | %-16.8e | %-16.8e%n",
                    x, y, naive, stable, exact);
        }
    }
}