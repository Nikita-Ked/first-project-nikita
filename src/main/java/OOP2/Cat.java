package OOP2;

public class Cat extends Animal {
    public Cat(String name, int runSpeed, int stamina) {
        super(name, runSpeed, 0, stamina); // У кота скорость плавания 0
    }

    @Override
    public double swim(int distance) {
        System.out.println(name + " не умеет плавать!");
        return -1;
    }
}