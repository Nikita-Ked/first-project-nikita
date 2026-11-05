package Claudehw;

import java.util.Arrays;

public class T8 {

    static double[] seidel(double[][] a, double[] b, double eps, int kMax) {
        int n = b.length;
        double[] x = new double[n];
        for (int k = 1; k <= kMax; k++) {
            double d = 0;
            for (int i = 0; i < n; i++) {
                double s = b[i];
                for (int j = 0; j < n; j++)
                    if (j != i) s -= a[i][j] * x[j];
                double xi = s / a[i][i];
                d = Math.max(d, Math.abs(xi - x[i]));
                x[i] = xi;
            }
            if (d < eps) return x;
        }
        throw new ArithmeticException("не сошлось");
    }

    public static void main(String[] args) {
        double[][] a = {
                {10, -1, 2},
                {-1, 11, -1},
                {2, -1, 10}
        };
        double[] b = {6, 25, -11};

        double[] result = seidel(a, b, 1e-5, 1000);
        System.out.println(Arrays.toString(result));
    }
}