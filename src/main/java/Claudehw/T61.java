package Claudehw;

import java.util.function.DoubleUnaryOperator;

public class T61 {

    static String ok(int n, double x) {
        return "OK n=" + n + " x=" + x;
    }

    // Метод Ньютона
    static String newton(DoubleUnaryOperator f, DoubleUnaryOperator df, double x,
                         double eps, int nmax) {
        for (int n = 1; n <= nmax; n++) {
            double d = df.applyAsDouble(x);
            if (Math.abs(d) < 1e-14) return "DF_ZERO";
            if (Math.abs(x) > 1e6) return "DIVERGED";
            double xn = x - f.applyAsDouble(x) / d;
            if (Math.abs(xn - x) < eps) return ok(n, xn);
            x = xn;
        }
        return "NMAX";
    }

    // Смешанный алгоритм (safe)
    static String safe(DoubleUnaryOperator f, DoubleUnaryOperator df, double a, double b,
                       double x, double eps, int nmax) {
        if (f.applyAsDouble(a) * f.applyAsDouble(b) > 0)
            return "NO_BRACKET";
        int bis = 0;
        for (int n = 1; n <= nmax; n++) {
            double d = df.applyAsDouble(x);
            double xn = (d == 0) ? Double.NaN : x - f.applyAsDouble(x) / d;
            if (!(xn > a && xn < b)) {
                xn = 0.5 * (a + b);
                bis++;
            }
            if (f.applyAsDouble(a) * f.applyAsDouble(xn) < 0)
                b = xn;
            else
                a = xn;
            if (Math.abs(xn - x) < eps)
                return ok(n, xn) + " bis=" + bis;
            x = xn;
        }
        return "NMAX";
    }

    public static void main(String[] args) {
        // Функция F = 5*(1 - e^(-x)) - x
        DoubleUnaryOperator f = x -> 5 * (1 - Math.exp(-x)) - x;
        // Производная F' = 5*e^(-x) - 1
        DoubleUnaryOperator df = x -> 5 * Math.exp(-x) - 1;

        double eps = 1e-5;
        int nmax = 100;


        System.out.println("Ньютон x0=4: " + newton(f, df, 4, eps, nmax));
        System.out.println("Ньютон x0=1.5: " + newton(f, df, 1.5, eps, nmax));

        // Для теста x0 = ln(5) производная обращается в нуль -> DF_ZERO
        double x0_ln5 = Math.log(5);
        System.out.println("Ньютон x0=ln(5): " + newton(f, df, x0_ln5, eps, nmax));

        System.out.println("safe [1;6], x0=1.5: " + safe(f, df, 1, 6, 1.5, eps, nmax));
    }
}