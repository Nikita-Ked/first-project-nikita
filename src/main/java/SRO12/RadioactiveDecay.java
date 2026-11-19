package SRO12;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Random;

public class RadioactiveDecay {

    public static void main(String[] args) throws IOException {
        Locale.setDefault(Locale.US);

        double trueT12 = 8.02; // Йод-131
        double trueLambda = Math.log(2.0) / trueT12;
        double trueN0 = 10000.0;

        int n = 15;
        double[] t = new double[n];
        double[] N_exp = new double[n];
        double[] Y = new double[n];

        Random rand = new Random(42);
        for (int i = 0; i < n; i++) {
            t[i] = i * 1.5;
            double exactN = trueN0 * Math.exp(-trueLambda * t[i]);
            N_exp[i] = exactN * (1.0 + 0.03 * rand.nextGaussian());
            Y[i] = Math.log(N_exp[i]);
        }

        double sumX = 0.0, sumY = 0.0, sumXX = 0.0, sumXY = 0.0;
        for (int i = 0; i < n; i++) {
            sumX += t[i];
            sumY += Y[i];
            sumXX += t[i] * t[i];
            sumXY += t[i] * Y[i];
        }

        double denom = n * sumXX - sumX * sumX;
        double a = (n * sumXY - sumX * sumY) / denom;
        double b = (sumXX * sumY - sumX * sumXY) / denom;

        double meanY = sumY / n;
        double ssRes = 0.0;
        double ssTot = 0.0;
        for (int i = 0; i < n; i++) {
            double yPred = a * t[i] + b;
            ssRes += Math.pow(Y[i] - yPred, 2);
            ssTot += Math.pow(Y[i] - meanY, 2);
        }

        double rSquared = 1.0 - (ssRes / ssTot);
        double sY = Math.sqrt(ssRes / (n - 2));
        double deltaA = sY * Math.sqrt(n / denom);
        double deltaB = sY * Math.sqrt(sumXX / denom);

        double lambdaEst = -a;
        double deltaLambda = deltaA;
        double N0Est = Math.exp(b);
        double deltaN0 = N0Est * deltaB;
        double T12Est = Math.log(2.0) / lambdaEst;
        double deltaT12 = (Math.log(2.0) / (lambdaEst * lambdaEst)) * deltaLambda;

        System.out.printf("Линейная регрессия ln(N) = a*t + b:%n");
        System.out.printf("  a = %.5f +- %.5f%n", a, deltaA);
        System.out.printf("  b = %.5f +- %.5f%n", b, deltaB);
        System.out.printf("  Коэффициент детерминации R^2 = %.6f%n", rSquared);
        System.out.println("Физические параметры модели:");
        System.out.printf("  Начальное число ядер N0   = %.1f +- %.1f (эталон: %.1f)%n", N0Est, deltaN0, trueN0);
        System.out.printf("  Постоянная распада lambda = %.5f +- %.5f сут^-1 (эталон: %.5f)%n", lambdaEst, deltaLambda, trueLambda);
        System.out.printf("  Период полураспада T_1/2  = %.3f +- %.3f сут (табличное I-131: %.2f сут)%n", T12Est, deltaT12, trueT12);

        try (PrintWriter pw = new PrintWriter(new FileWriter("task4_decay_mnk.csv"))) {
            pw.println("t,N_exp,N_approx,ln_N_exp,ln_N_approx");
            for (int i = 0; i < n; i++) {
                double nApprox = N0Est * Math.exp(-lambdaEst * t[i]);
                double lnApprox = a * t[i] + b;
                pw.printf("%.4f,%.4f,%.4f,%.6f,%.6f%n", t[i], N_exp[i], nApprox, Y[i], lnApprox);
            }
        }
        System.out.println("Результаты и CSV для Задания 4 успешно сохранены.");
    }
}