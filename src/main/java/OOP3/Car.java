package OOP3;
class Car implements Transport {
    private double fuel;

    public Car(double fuel) {
        this.fuel = fuel;
    }

    @Override
    public String getName() {
        return "Машина";
    }

    @Override
    public boolean canMoveOn(TerrainType terrain) {
        return terrain == TerrainType.PLAIN;
    }

    @Override
    public boolean move(double distance, TerrainType terrain) {
        if (!canMoveOn(terrain)) return false;
        double requiredFuel = distance * 0.5;
        if (fuel < requiredFuel) return false;
        fuel -= requiredFuel;
        return true;
    }
}