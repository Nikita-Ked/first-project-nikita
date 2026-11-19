package SRO12;

public class CubicSpline {
    private final double[] x, a, b, c, d;

    public CubicSpline(double[] x, double[] y) {
        int n = x.length - 1;
        this.x = x.clone();
        this.a = y.clone();
        this.b = new double[n];
        this.c = new double[n + 1];
        this.d = new double[n];

        double[] h = new double[n];
        for (int i = 0; i < n; i++) {
            h[i] = x[i + 1] - x[i];
        }

        double[] alpha = new double[n];
        for (int i = 1; i < n; i++) {
            alpha[i] = (3.0 / h[i]) * (a[i + 1] - a[i]) - (3.0 / h[i - 1]) * (a[i] - a[i - 1]);
        }

        double[] l = new double[n + 1];
        double[] mu = new double[n + 1];
        double[] z = new double[n + 1];
        l[0] = 1.0;

        for (int i = 1; i < n; i++) {
            l[i] = 2.0 * (x[i + 1] - x[i - 1]) - h[i - 1] * mu[i - 1];
            mu[i] = h[i] / l[i];
            z[i] = (alpha[i] - h[i - 1] * z[i - 1]) / l[i];
        }

        l[n] = 1.0;
        for (int j = n - 1; j >= 0; j--) {
            c[j] = z[j] - mu[j] * c[j + 1];
            b[j] = (a[j + 1] - a[j]) / h[j] - h[j] * (c[j + 1] + 2.0 * c[j]) / 3.0;
            d[j] = (c[j + 1] - c[j]) / (3.0 * h[j]);
        }
    }

    public double evaluate(double val) {
        int n = x.length - 1;
        int i = n - 1;
        for (int j = 0; j < n; j++) {
            if (val <= x[j + 1]) {
                i = j;
                break;
            }
        }
        double dx = val - x[i];
        return a[i] + b[i] * dx + c[i] * dx * dx + d[i] * dx * dx * dx;
    }
}