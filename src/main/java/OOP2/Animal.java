package OOP2;

public abstract class Animal {
    protected String name;
    protected int runSpeed;
    protected int swimSpeed;
    protected int stamina;

    public Animal(String name, int runSpeed, int swimSpeed, int stamina) {
        this.name = name;
        this.runSpeed = runSpeed;
        this.swimSpeed = swimSpeed;
        this.stamina = stamina;
    }

    public double run(int distance) {
        int staminaCost = distance * 1; // 1 ед. выносливости на 1 метр бега

        if (stamina < staminaCost) {
            System.out.println(name + " устал(а) и не может пробежать " + distance + " м!");
            return -1;
        }

        stamina -= staminaCost;
        double time = (double) distance / runSpeed;
        System.out.println(name + " пробежал(а) " + distance + " м за " + time + " с. Осталось выносливости: " + stamina);
        return time;
    }

    public abstract double swim(int distance);

    public void info() {
        System.out.println("Животное: " + name + " | Выносливость: " + stamina + " ед.");
    }
}