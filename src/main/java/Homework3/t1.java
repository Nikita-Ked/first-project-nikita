package Homework3;
public class t1 {
    public static void main(String[] args) {
        int[][] array = {
                {1, -2, 3},
                {-4, 5, -6},
                {7, 8, -9}
        };
        int sum = sumOfPositiveElements(array);
        System.out.println(+ sum);
    }
    public static int sumOfPositiveElements(int[][] array) {
        if (array == null) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                continue;
            }
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] > 0) {
                    sum += array[i][j];
                }
            }
        }
        return sum;
    }
}