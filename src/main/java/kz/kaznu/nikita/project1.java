package kz.kaznu.nikita;

public class project1 {
    public static void main(String[] args) {
        double x = 3.0;
        double literalSum = 0;

        for (int i = 0; i <= 5; i++) {
            literalSum += (x * x);
        }
        System.out.println(literalSum);

        System.out.println("------------------------------------");

        int logicalSum = 0;

        for (int i = 0; i <= 5; i++) {
            logicalSum += (i * i);
        }
        System.out.println(logicalSum);
    }
}