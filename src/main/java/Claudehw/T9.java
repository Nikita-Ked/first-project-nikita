package Claudehw;

import java.util.Arrays;

public class T9 {

    static double[] divDiff(double[] xs, double[] ys) {
        int n = xs.length;
        double[] c = ys.clone();
        for (int k = 1; k < n; k++)
            for (int i = n - 1; i >= k; i--)
                c[i] = (c[i] - c[i - 1]) / (xs[i] - xs[i - k]);
        return c;
    }

    static double newton(double[] xs, double[] c, double x) {
        double s = c[c.length - 1];
        for (int i = c.length - 2; i >= 0; i--)
            s = s * (x - xs[i]) + c[i];
        return s;
    }

    public static void main(String[] args) {
        double[] xs = {0.0, 0.5, 1.0, 1.5, 2.0};
        double[] ys = new double[xs.length];
        for (int i = 0; i < xs.length; i++) {
            ys[i] = Math.sin(xs[i]);
        }

        double[] c = divDiff(xs, ys);
        double val = newton(xs, c, 1.2);
        System.out.println("Значение в точке 1.2: " + val);
    }
}