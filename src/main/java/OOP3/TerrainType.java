package OOP3;

enum TerrainType {
    DENSE_FOREST("Густой лес"),
    PLAIN("Равнина"),
    SWAMP("Болото");

    private final String title;

    TerrainType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}