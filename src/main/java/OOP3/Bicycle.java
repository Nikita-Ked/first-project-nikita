package OOP3;

class Bicycle implements Transport {
    @Override
    public String getName() {
        return "Велосипед";
    }

    @Override
    public boolean canMoveOn(TerrainType terrain) {
        return terrain != TerrainType.SWAMP;
    }

    @Override
    public boolean move(double distance, TerrainType terrain) {
        return canMoveOn(terrain);
    }
}