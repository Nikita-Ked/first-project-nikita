package Claudehw;

import java.util.Arrays;

public class T71 {

    static double[] thomas(double[] a, double[] b, double[] c, double[] d) {
        int n = d.length;
        double[] A = new double[n], B = new double[n];
        double[] x = new double[n];

        A[0] = -c[0] / b[0];
        B[0] = d[0] / b[0];

        for (int i = 1; i < n; i++) {
            double e = a[i] * A[i - 1] + b[i];
            A[i] = -c[i] / e;
            B[i] = (d[i] - a[i] * B[i - 1]) / e;
        }

        x[n - 1] = B[n - 1];
        for (int i = n - 2; i >= 0; i--)
            x[i] = A[i] * x[i + 1] + B[i];

        return x;
    }

    public static void main(String[] args) {
        double[] a = {0, 1, 1, 1};
        double[] b = {2, 2, 2, 2};
        double[] c = {1, 1, 1, 0};
        double[] d = {3, 4, 4, 3};

        double[] result = thomas(a, b, c, d);
        System.out.println(Arrays.toString(result));
    }
}