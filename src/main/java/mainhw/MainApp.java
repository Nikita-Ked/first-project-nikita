package mainhw;

public class MainApp {

    public static void main(String[] args) {
        double[][] A = {
                {2.0, 1.0},
                {1.0, 2.0}
        };

        System.out.println("Исходная матрица A:");
        printMatrix(A);

        double trace = A[0][0] + A[1][1];
        double det = A[0][0] * A[1][1] - A[0][1] * A[1][0];

        System.out.println("\nХарактеристическое уравнение: lambda^2 - " + trace + "*lambda + " + det + " = 0");

        double discriminant = trace * trace - 4 * det;
        if (discriminant < 0) {
            System.out.println("Собственные значения являются комплексными.");
            return;
        }

        double lambda1 = (trace + Math.sqrt(discriminant)) / 2.0;
        double lambda2 = (trace - Math.sqrt(discriminant)) / 2.0;

        System.out.println("\nСобственные значения (λ):");
        System.out.println("λ₁ = " + Math.round(lambda1));
        System.out.println("λ₂ = " + Math.round(lambda2));

        System.out.println("\nСобственные векторы:");
        printEigenvector(A, lambda1, "v₁");
        printEigenvector(A, lambda2, "v₂");
    }

    public static void printEigenvector(double[][] A, double lambda, String vectorName) {
        double a = A[0][0] - lambda;
        double b = A[0][1];
        double c = A[1][0];
        double d = A[1][1] - lambda;

        double x1, x2;

        if (Math.abs(b) > 1e-9) {
            x1 = 1.0;
            x2 = -a / b;
        } else if (Math.abs(a) > 1e-9) {
            x1 = -b / a;
            x2 = 1.0;
        } else {
            x1 = 1.0;
            x2 = 0.0;
        }

        if (Math.abs(x2 - Math.round(x2)) < 1e-9 && Math.abs(x1 - Math.round(x1)) < 1e-9) {
            System.out.println(vectorName + " = [" + Math.round(x1) + ", " + Math.round(x2) + "]ᵀ");
        } else {
            System.out.println(vectorName + " = [" + x1 + ", " + x2 + "]ᵀ");
        }
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            System.out.print("[ ");
            for (double val : row) {
                System.out.print((int) val + " ");
            }
            System.out.println("]");
        }
    }
}

