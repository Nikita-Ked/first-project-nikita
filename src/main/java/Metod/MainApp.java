package Metod;
public class MainApp {

    // Исходная функция
    static double f(double x) {
        return x * x * x - 6 * x + 2;
    }

    // Первая производная
    static double df(double x) {
        return 3 * x * x - 6;
    }

    public static void main(String[] args) {
        double x = -4.0;      // Начальное приближение
        double eps = 0.1;     // Требуемая точность
        int n = 0;            // Счётчик итераций

        System.out.println("Метод Ньютона");
        System.out.println("Начальное приближение: " + x);
        System.out.println("Точность: " + eps);

        while (true) {
            // Проверяем, что производная не близка к нулю
            if (Math.abs(df(x)) < 1e-12) {
                System.out.println("Производная близка к нулю!");
                return;
            }

            // Вычисляем следующее приближение
            double nextX = x - f(x) / df(x);
            n++;

            System.out.printf(
                    "Итерация %d: x = %.5f, ошибка = %.5f%n",
                    n, nextX, Math.abs(nextX - x)
            );

            // Проверяем условие остановки
            if (Math.abs(nextX - x) < eps) {
                x = nextX;
                break;
            }

            x = nextX;
        }

        System.out.println("Найденный корень: " + x);
        System.out.println("Количество итераций: " + n);
        System.out.println("Значение функции: " + f(x));
    }
}
