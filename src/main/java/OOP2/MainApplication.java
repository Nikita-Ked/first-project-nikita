package OOP2;

public class MainApplication {
    public static void main(String[] args) {
        Animal cat = new Cat("Барсик", 10, 50);
        Animal dog = new Dog("Шарик", 12, 4, 100);
        Animal horse = new Horse("Буцефал", 20, 5, 200);

        System.out.println("=== Тестирование Кота ===");
        cat.info();
        cat.run(30);
        cat.swim(10);
        cat.run(30); // Здесь выносливости не хватит, появится состояние усталости
        cat.info();

        System.out.println("\n=== Тестирование Собаки ===");
        dog.info();
        dog.run(20);
        dog.swim(30);
        dog.info();

        System.out.println("\n=== Тестирование Лошади ===");
        horse.info();
        horse.swim(25);
        horse.run(50);
        horse.info();
    }
}