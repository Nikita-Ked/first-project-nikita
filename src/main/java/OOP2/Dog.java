package OOP2;

public class Dog extends Animal {
    public Dog(String name, int runSpeed, int swimSpeed, int stamina) {
        super(name, runSpeed, swimSpeed, stamina);
    }

    @Override
    public double swim(int distance) {
        int staminaCost = distance * 2; // 2 ед. выносливости на 1 метр плавания

        if (stamina < staminaCost) {
            System.out.println(name + " устал(а) и не может проплыть " + distance + " м!");
            return -1;
        }

        stamina -= staminaCost;
        double time = (double) distance / swimSpeed;
        System.out.println(name + " проплыл(а) " + distance + " м за " + time + " с. Осталось выносливости: " + stamina);
        return time;
    }
}