import java.util.ArrayList;
import java.util.List;

public class MainApplication {

    public static class IterationStep {
        public int k;
        public double x;
        public double diff;
        public double fx;

        public IterationStep(int k, double x, double diff, double fx) {
            this.k = k;
            this.x = x;
            this.diff = diff;
            this.fx = fx;
        }
    }

    private static final double SIGMA = 5.670374e-8;

    public static double f(double T, double I, double R0, double alpha, double T0, double h, double S, double epsRad, double Tenv) {
        double R = R0 * (1.0 + alpha * (T - T0));
        double heatIn = I * I * R;
        double heatOutConv = h * S * (T - Tenv);
        double heatOutRad = epsRad * SIGMA * S * (Math.pow(T, 4) - Math.pow(Tenv, 4));
        return heatIn - heatOutConv - heatOutRad;
    }

    public static double fPrime(double T, double I, double R0, double alpha, double h, double S, double epsRad) {
        double dHeatIn_dT = I * I * R0 * alpha;
        double dHeatOutConv_dT = h * S;
        double dHeatOutRad_dT = 4.0 * epsRad * SIGMA * S * Math.pow(T, 3);
        return dHeatIn_dT - dHeatOutConv_dT - dHeatOutRad_dT;
    }

    public static List<IterationStep> solveNewton(double I, double R0, double alpha, double T0, double h, double S, double epsRad, double Tenv, double T_initial, double eps) {
        List<IterationStep> history = new ArrayList<>();
        double Tk = T_initial;
        int k = 1;

        while (true) {
            double fVal = f(Tk, I, R0, alpha, T0, h, S, epsRad, Tenv);
            double fPrimeVal = fPrime(Tk, I, R0, alpha, h, S, epsRad);

            if (Math.abs(fPrimeVal) < 1e-15) {
                break;
            }

            double T_next = Tk - fVal / fPrimeVal;
            double diff = Math.abs(T_next - Tk);
            double fNextVal = f(T_next, I, R0, alpha, T0, h, S, epsRad, Tenv);

            history.add(new IterationStep(k, T_next, diff, fNextVal));

            Tk = T_next;
            k++;

            if (diff < eps || k > 100) {
                break;
            }
        }
        return history;
    }

    public static void printTable(String title, List<IterationStep> history) {
        System.out.println("\n=======================================================");
        System.out.println("  " + title);
        System.out.println("=======================================================");
        System.out.printf("%-5s | %-15s | %-15s | %-15s%n", "k", "T_k (K)", "|T_k - T_{k-1}|", "f(T_k)");
        System.out.println("-------------------------------------------------------");
        for (IterationStep step : history) {
            System.out.printf("%-5d | %-15.6f | %-15.2e | %-15.2e%n", step.k, step.x, step.diff, step.fx);
        }
        System.out.println("-------------------------------------------------------");
    }

    public static void main(String[] args) {
        double I = 5.0;
        double R0 = 2.0;
        double alpha = 0.004;
        double T0 = 293.15;
        double h = 15.0;
        double S = 0.005;
        double epsRad = 0.85;
        double Tenv = 293.15;
        double eps = 1e-8;

        double T_initial = Tenv + 50.0;

        List<IterationStep> newtonSteps = solveNewton(I, R0, alpha, T0, h, S, epsRad, Tenv, T_initial, eps);
        printTable("МЕТОД НЬЮТОНА", newtonSteps);

        if (!newtonSteps.isEmpty()) {
            double T_star = newtonSteps.get(newtonSteps.size() - 1).x;
            double residual = Math.abs(f(T_star, I, R0, alpha, T0, h, S, epsRad, Tenv));

            System.out.println("\n=== ВЕРИФИКАЦИЯ РЕШЕНИЯ ===");
            System.out.printf("T* = %.4f K (%.2f °C)%n", T_star, T_star - 273.15);
            System.out.printf("Невязка = %.2e%n", residual);
        }
    }
}