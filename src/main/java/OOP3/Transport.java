package OOP3;

interface Transport {
    String getName();
    boolean canMoveOn(TerrainType terrain);
    boolean move(double distance, TerrainType terrain);
}