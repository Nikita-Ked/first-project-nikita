package Claudehw;

import java.util.Arrays;

public class T7 {

    static double[] solve(double[][] a0, double[] b0, boolean pivot) {
        int n = b0.length;
        double[][] a = new double[n][];
        for (int i = 0; i < n; i++) a[i] = a0[i].clone();
        double[] b = b0.clone();

        for (int k = 0; k < n - 1; k++) {
            if (pivot) {
                int p = k;
                for (int i = k + 1; i < n; i++)
                    if (Math.abs(a[i][k]) > Math.abs(a[p][k])) p = i;
                double t = a[k][k]; a[k][k] = a[p][k]; a[p][k] = t;
                double tb = b[k]; b[k] = b[p]; b[p] = tb;
            }
            if (a[k][k] == 0) throw new ArithmeticException("вырождена");
            for (int i = k + 1; i < n; i++) {
                double c = a[i][k] / a[k][k];
                for (int j = k; j < n; j++) a[i][j] -= c * a[k][j];
                b[i] -= c * b[k];
            }
        }

        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double s = b[i];
            for (int j = i + 1; j < n; j++) s -= a[i][j] * x[j];
            x[i] = s / a[i][i];
        }
        return x;
    }

    public static void main(String[] args) {
        double[][] a = {
                {2, 1, -1},
                {-3, -1, 2},
                {-2, 1, 2}
        };
        double[] b = {8, -11, -3};

        double[] result = solve(a, b, true);
        System.out.println(Arrays.toString(result));
    }
}