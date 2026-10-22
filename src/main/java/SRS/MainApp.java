import java.util.ArrayList;
import java.util.List;

public class MainApp {

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

    private static final double SIGMA = 5.670374e-8; // Постоянная Стефана-Больцмана

    // Целевая функция теплового баланса: f(T) = P_in - P_out = 0
    public static double f(double T, double I, double R0, double alpha, double T0,
                           double h, double S, double epsRad, double Tenv) {
        double R = R0 * (1.0 + alpha * (T - T0));
        double heatIn = I * I * R;
        double heatOutConv = h * S * (T - Tenv);
        double heatOutRad = epsRad * SIGMA * S * (Math.pow(T, 4) - Math.pow(Tenv, 4));
        return heatIn - heatOutConv - heatOutRad;
    }

    // Производная функции для метода Ньютона
    public static double fPrime(double T, double I, double R0, double alpha,
                                double h, double S, double epsRad) {
        double dHeatIn_dT = I * I * R0 * alpha;
        double dHeatOutConv_dT = h * S;
        double dHeatOutRad_dT = 4.0 * epsRad * SIGMA * S * Math.pow(T, 3);
        return dHeatIn_dT - dHeatOutConv_dT - dHeatOutRad_dT;
    }

    // 1. Метод Ньютона (касательных)
    public static List<IterationStep> solveNewton(double I, double R0, double alpha, double T0,
                                                  double h, double S, double epsRad, double Tenv,
                                                  double T_initial, double eps) {
        List<IterationStep> history = new ArrayList<>();
        double Tk = T_initial;
        int k = 1;

        while (true) {
            double fVal = f(Tk, I, R0, alpha, T0, h, S, epsRad, Tenv);
            double fPrimeVal = fPrime(Tk, I, R0, alpha, h, S, epsRad);

            if (Math.abs(fPrimeVal) < 1e-15) break;

            double T_next = Tk - fVal / fPrimeVal;
            double diff = Math.abs(T_next - Tk);
            double fNextVal = f(T_next, I, R0, alpha, T0, h, S, epsRad, Tenv);

            history.add(new IterationStep(k, T_next, diff, fNextVal));
            Tk = T_next;
            k++;

            if (diff < eps || k > 100) break;
        }
        return history;
    }

    // 2. Метод секущих
    public static List<IterationStep> solveSecant(double I, double R0, double alpha, double T0,
                                                  double h, double S, double epsRad, double Tenv,
                                                  double T0_sec, double T1_sec, double eps) {
        List<IterationStep> history = new ArrayList<>();
        double t_prev2 = T0_sec;
        double t_prev1 = T1_sec;
        int k = 1;

        while (true) {
            double f2 = f(t_prev2, I, R0, alpha, T0, h, S, epsRad, Tenv);
            double f1 = f(t_prev1, I, R0, alpha, T0, h, S, epsRad, Tenv);

            double t_curr = t_prev1 - f1 * (t_prev1 - t_prev2) / (f1 - f2);
            double diff = Math.abs(t_curr - t_prev1);
            double fc = f(t_curr, I, R0, alpha, T0, h, S, epsRad, Tenv);

            history.add(new IterationStep(k, t_curr, diff, fc));

            t_prev2 = t_prev1;
            t_prev1 = t_curr;
            k++;

            if (diff < eps || k > 100) break;
        }
        return history;
    }

    // 3. Метод дихотомии (деления пополам)
    public static List<IterationStep> solveBisection(double I, double R0, double alpha, double T0,
                                                     double h, double S, double epsRad, double Tenv,
                                                     double a, double b, double eps) {
        List<IterationStep> history = new ArrayList<>();
        double left = a;
        double right = b;
        int k = 1;

        while (true) {
            double c = (left + right) / 2.0;
            double fc = f(c, I, R0, alpha, T0, h, S, epsRad, Tenv);
            double diff = (right - left) / 2.0;

            history.add(new IterationStep(k, c, diff, fc));

            if (f(left, I, R0, alpha, T0, h, S, epsRad, Tenv) * fc < 0) {
                right = c;
            } else {
                left = c;
            }

            k++;
            if (diff < eps || k > 100) break;
        }
        return history;
    }

    // Вывод таблицы итераций
    public static void printTable(String title, List<IterationStep> history) {
        System.out.println("\n=======================================================");
        System.out.println("  " + title);
        System.out.println("=======================================================");
        System.out.printf("%-5s | %-15s | %-15s | %-15s%n", "k", "T_k (K)", "|T_k - T_{k-1}|", "f(T_k)");
        System.out.println("-------------------------------------------------------");
        for (IterationStep step : history) {
            System.out.printf("%-5d | %-15.6f | %-15.2e | %-15.2e%n",
                    step.k, step.x, step.diff, step.fx);
        }
        System.out.println("-------------------------------------------------------");
    }

    public static void main(String[] args) {
        // Физические параметры проводника (Вариант 10)
        double I = 5.0;
        double R0 = 2.0;
        double alpha = 0.004;
        double T0 = 293.15;
        double h = 15.0;
        double S = 0.005;
        double epsRad = 0.85;
        double Tenv = 293.15;
        double eps = 1e-8;

        System.out.println("=== ИССЛЕДОВАНИЕ МЕТОДОВ ДЛЯ ВАРИАНТА 10 ===");

        // 1. Метод Ньютона
        List<IterationStep> newtonSteps = solveNewton(I, R0, alpha, T0, h, S, epsRad, Tenv, Tenv + 50.0, eps);
        printTable("МЕТОД НЬЮТОНА", newtonSteps);
        double T_star = newtonSteps.get(newtonSteps.size() - 1).x;

        // 2. Метод секущих
        List<IterationStep> secantSteps = solveSecant(I, R0, alpha, T0, h, S, epsRad, Tenv, Tenv, Tenv + 100.0, eps);
        printTable("МЕТОД СЕКУЩИХ", secantSteps);

        // 3. Метод дихотомии
        List<IterationStep> bisectionSteps = solveBisection(I, R0, alpha, T0, h, S, epsRad, Tenv, Tenv, Tenv + 200.0, eps);
        printTable("МЕТОД ДИХОТОМИИ", bisectionSteps);

        // Верификация
        double residual = Math.abs(f(T_star, I, R0, alpha, T0, h, S, epsRad, Tenv));
        System.out.println("\n=== ВЕРИФИКАЦИЯ РЕШЕНИЯ ===");
        System.out.printf("Установившаяся температура T* = %.4f K (%.2f °C)%n", T_star, T_star - 273.15);
        System.out.printf("Невязка R = |f(T*)|         = %.2e%n", residual);
    }
}