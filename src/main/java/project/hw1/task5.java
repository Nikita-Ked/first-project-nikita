package project.hw1;

public class task5 {

    public static void addOrSubtractAndPrint(int initValue, int delta, boolean increment) {
        if (increment) {
            System.out.println(initValue + delta);
        } else {
            System.out.println(initValue - delta);
        }
    }

    public static void main(String[] args) {
        addOrSubtractAndPrint(10, 5, true);
    }
}