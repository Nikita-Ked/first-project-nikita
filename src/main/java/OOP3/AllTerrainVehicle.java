package OOP3;

class AllTerrainVehicle implements Transport {
    private double fuel;

    public AllTerrainVehicle(double fuel) {
        this.fuel = fuel;
    }

    @Override
    public String getName() {
        return "Вездеход";
    }

    @Override
    public boolean canMoveOn(TerrainType terrain) {
        return true;
    }

    @Override
    public boolean move(double distance, TerrainType terrain) {
        double requiredFuel = distance * 0.8;
        if (fuel < requiredFuel) return false;
        fuel -= requiredFuel;
        return true;
    }
}