package OOP3;
class Horse implements Transport {
    private double stamina;

    public Horse(double stamina) {
        this.stamina = stamina;
    }

    @Override
    public String getName() {
        return "Лошадь";
    }

    @Override
    public boolean canMoveOn(TerrainType terrain) {
        return terrain != TerrainType.SWAMP;
    }

    @Override
    public boolean move(double distance, TerrainType terrain) {
        if (!canMoveOn(terrain)) return false;
        double requiredStamina = distance * 0.3;
        if (stamina < requiredStamina) return false;
        stamina -= requiredStamina;
        return true;
    }
}