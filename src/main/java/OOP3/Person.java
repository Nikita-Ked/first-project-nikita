package OOP3;

class Person {
    private String name;
    private Transport currentTransport;

    public Person(String name) {
        this.name = name;
        this.currentTransport = null;
    }

    public void getOnTransport(Transport transport) {
        this.currentTransport = transport;
    }

    public void getOffTransport() {
        this.currentTransport = null;
    }

    public boolean move(double distance, TerrainType terrain) {
        if (currentTransport == null) {
            return true; // Пешком
        }
        return currentTransport.move(distance, terrain);
    }

    public String getName() {
        return name;
    }

    public Transport getCurrentTransport() {
        return currentTransport;
    }
}