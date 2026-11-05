package Claudehw;

import java.util.function.DoubleUnaryOperator;

public class MainApp {

    static String bisect(DoubleUnaryOperator f, double a, double b, double eps, int nmax) {
        if (f.applyAsDouble(a) * f.applyAsDouble(b) > 0)
            return "NO_BRACKET"; // нет смены знака

        for (int n = 1; n <= nmax; n++) {
            double c = 0.5 * (a + b);
            if (f.applyAsDouble(a) * f.applyAsDouble(c) < 0) b = c;
            else a = c;

            if (b - a < 2 * eps) // формула (5.6)
                return "OK n=" + n + " x=" + 0.5 * (a + b);
        }
        return "NMAX";
    }

    public static void main(String[] args) {
        // Определение функции F = 5*(1 - e^(-x)) - x
        DoubleUnaryOperator f = x -> 5 * (1 - Math.exp(-x)) - x;

        double eps = 1e-5; // стандартное значение точности
        int nmax = 100;    // максимальное число итераций

        // Тест 1: на отрезке [4; 5]
        System.out.println("F = 5(1-e^-x) - x, [4; 5]");
        System.out.println(bisect(f, 4, 5, eps, nmax));

        // Тест 2: на отрезке [1; 2]
        System.out.println("\nто же на [1; 2]");
        System.out.println(bisect(f, 1, 2, eps, nmax));
    }
}