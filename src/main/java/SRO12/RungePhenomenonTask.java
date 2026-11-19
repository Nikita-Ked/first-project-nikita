package SRO12;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class RungePhenomenonTask {

    public static void main(String[] args) throws IOException {
        Locale.setDefault(Locale.US);

        System.out.printf("%-6s %-22s %-22s%n", "n", "MaxErr (Равномерная)", "MaxErr (Чебышёвская)");

        try (PrintWriter pw = new PrintWriter(new FileWriter("task3_runge.csv"))) {
            pw.println("n,uniform_max_err,chebyshev_max_err");

            for (int n = 4; n <= 20; n += 2) {
                double[] xUniform = new double[n + 1];
                double[] yUniform = new double[n + 1];

                double[] xCheb = new double[n + 1];
                double[] yCheb = new double[n + 1];

                for (int i = 0; i <= n; i++) {
                    xUniform[i] = -1.0 + 2.0 * i / n;
                    yUniform[i] = rungeFunction(xUniform[i]);

                    xCheb[i] = Math.cos(Math.PI * (2.0 * i + 1.0) / (2.0 * (n + 1)));
                    yCheb[i] = rungeFunction(xCheb[i]);
                }

                double maxErrUniform = 0.0;
                double maxErrCheb = 0.0;
                int samples = 1000;

                for (int k = 0; k <= samples; k++) {
                    double x = -1.0 + 2.0 * k / samples;
                    double exact = rungeFunction(x);

                    double errU = Math.abs(exact - InterpolationTask.lagrangeInterpolation(xUniform, yUniform, x));
                    double errC = Math.abs(exact - InterpolationTask.lagrangeInterpolation(xCheb, yCheb, x));

                    if (errU > maxErrUniform) maxErrUniform = errU;
                    if (errC > maxErrCheb) maxErrCheb = errC;
                }

                System.out.printf("%-6d %-22.6e %-22.6e%n", n, maxErrUniform, maxErrCheb);
                pw.printf("%d,%.8e,%.8e%n", n, maxErrUniform, maxErrCheb);
            }
        }
        System.out.println("Результаты и CSV для Задания 3 успешно сохранены.");
    }

    private static double rungeFunction(double x) {
        return 1.0 / (1.0 + 25.0 * x * x);
    }
}