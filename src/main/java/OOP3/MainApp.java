package OOP3;

public class MainApp {
    public static void main(String[] args) {
        Person alex = new Person("Алексей");

        alex.move(5.0, TerrainType.PLAIN);

        Car car = new Car(10.0);
        alex.getOnTransport(car);

        boolean result1 = alex.move(10.0, TerrainType.SWAMP);
        boolean result2 = alex.move(10.0, TerrainType.PLAIN);

        alex.getOffTransport();
    }
}