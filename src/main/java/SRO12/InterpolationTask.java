package SRO12;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class InterpolationTask {

    public static void main(String[] args) throws IOException {
        Locale.setDefault(Locale.US);

        double[] xNodes = {0.0, 2.0, 4.0, 6.0, 8.0, 10.0};
        double[] yNodes = {1000.0, 840.9, 707.1, 594.6, 500.0, 420.4};

        CubicSpline spline = new CubicSpline(xNodes, yNodes);

        System.out.printf("%-10s %-18s %-18s %-15s%n", "x", "Лагранж L(x)", "Куб. сплайн S(x)", "|L(x) - S(x)|");
        double[] testPoints = {1.0, 3.0, 5.0, 7.0, 9.0};
        for (double x : testPoints) {
            double lagrangeVal = lagrangeInterpolation(xNodes, yNodes, x);
            double splineVal = spline.evaluate(x);
            System.out.printf("%-10.2f %-18.4f %-18.4f %-15.4f%n",
                    x, lagrangeVal, splineVal, Math.abs(lagrangeVal - splineVal));
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter("task2_interpolation.csv"))) {
            pw.println("x,lagrange,spline");
            for (double x = 0.0; x <= 10.001; x += 0.1) {
                pw.printf("%.4f,%.6f,%.6f%n", x, lagrangeInterpolation(xNodes, yNodes, x), spline.evaluate(x));
            }
        }
        System.out.println("Результаты и CSV для Задания 2 успешно сохранены.");
    }

    public static double lagrangeInterpolation(double[] xNodes, double[] yNodes, double x) {
        double result = 0.0;
        int n = xNodes.length;
        for (int i = 0; i < n; i++) {
            double term = yNodes[i];
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    term *= (x - xNodes[j]) / (xNodes[i] - xNodes[j]);
                }
            }
            result += term;
        }
        return result;
    }
}