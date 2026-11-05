package Claudehw;

import java.util.function.DoubleUnaryOperator;

public class T6 {

    static String ok(int n, double x) {
        return "OK n=" + n + " x=" + x;
    }

    // Метод хорд
    static String chord(DoubleUnaryOperator f, double a, double b, double eps, int nmax) {
        if (f.applyAsDouble(a) * f.applyAsDouble(b) > 0)
            return "NO_BRACKET";
        double c = a;
        for (int n = 1; n <= nmax; n++) {
            double fa = f.applyAsDouble(a);
            double fb = f.applyAsDouble(b);
            double cn = a - (b - a) * fa / (fb - fa);
            if (fa * f.applyAsDouble(cn) < 0) b = cn;
            else a = cn;
            if (Math.abs(cn - c) < eps) return ok(n, cn);
            c = cn;
        }
        return "NMAX";
    }

    // Метод секущих
    static String secant(DoubleUnaryOperator f, double x0, double x1, double eps, int nmax) {
        for (int n = 1; n <= nmax; n++) {
            double f0 = f.applyAsDouble(x0);
            double f1 = f.applyAsDouble(x1);
            if (f1 == f0) return "FLAT";
            double x2 = x1 - f1 * (x1 - x0) / (f1 - f0);
            if (Math.abs(x2 - x1) < eps) return ok(n, x2);
            x0 = x1;
            x1 = x2;
        }
        return "NMAX";
    }

    public static void main(String[] args) {
        // Функция F = 5*(1 - e^(-x)) - x
        DoubleUnaryOperator f = x -> 5 * (1 - Math.exp(-x)) - x;
        double eps = 1e-5;
        int nmax = 100;


        System.out.print("chord [4; 5]: " + chord(f, 4, 5, eps, nmax) + "   ");
        System.out.print("secant 4; 5: " + secant(f, 4, 5, eps, nmax) + "   ");
        System.out.println("chord [1; 2]: " + chord(f, 1, 2, eps, nmax));
    }
}