package OOP;

class User {
    private String lastName;
    private String firstName;
    private String middleName;
    private int birthYear;
    private String email;

    // Конструктор для заполнения всех полей
    public User(String lastName, String firstName, String middleName, int birthYear, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthYear = birthYear;
        this.email = email;
    }

    // Метод для вывода информации о пользователе
    public void printInfo() {
        System.out.println("ФИО: " + lastName + " " + firstName + " " + middleName);
        System.out.println("Год рождения: " + birthYear);
        System.out.println("e-mail: " + email);
        System.out.println("-------------------------");
    }

    // Геттер для получения года рождения (нужен для фильтрации)
    public int getBirthYear() {
        return birthYear;
    }
}

public class MainApplication {
    public static void main(String[] args) {
        // Создаем массив из 10 пользователей
        User[] users = new User[10];

        // Заполняем массив объектами (разные года рождения для демонстрации фильтра)
        users[0] = new User("Иванов", "Иван", "Иванович", 1980, "ivanov@mail.com");
        users[1] = new User("Петров", "Петр", "Петрович", 1995, "petrov@mail.com");
        users[2] = new User("Смирнов", "Алексей", "Сергеевич", 1975, "smirnov@mail.com");
        users[3] = new User("Кузнецова", "Анна", "Владимировна", 2000, "kuznetsova@mail.com");
        users[4] = new User("Попов", "Дмитрий", "Игоревич", 1983, "popov@mail.com");
        users[5] = new User("Васильев", "Сергей", "Павлович", 1990, "vasilyev@mail.com");
        users[6] = new User("Соколова", "Елена", "Николаевна", 1968, "sokolova@mail.com");
        users[7] = new User("Михайлов", "Андрей", "Олегович", 2002, "mikhailov@mail.com");
        users[8] = new User("Федоров", "Николай", "Федорович", 1985, "fedorov@mail.com");
        users[9] = new User("Морозова", "Ольга", "Викторовна", 1992, "morozova@mail.com");

        System.out.println("--- Пользователи старше 40 лет (родившиеся до 1986 года) ---");

        // Текущий год (для расчета возраста)
        int currentYear = 2026;

        // Проходим циклом и выводим информацию только о тех, кто старше 40 лет
        for (User user : users) {
            int age = currentYear - user.getBirthYear();
            if (age > 40) {
                user.printInfo();
            }
        }
    }
}