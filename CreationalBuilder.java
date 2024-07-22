//Use Case: Building complex objects step by step.
class House {
    private String walls;
    private String roof;
    private String interior;

    public void setWalls(String walls) {
        this.walls = walls;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    @Override
    public String toString() {
        return "Walls: " + walls + ", Roof: " + roof + ", Interior: " + interior;
    }
}
class HouseBuilder {
    private House house;

    public HouseBuilder() {
        this.house = new House();
    }

    public HouseBuilder buildWalls(String walls) {
        house.setWalls(walls);
        return this;
    }

    public HouseBuilder buildRoof(String roof) {
        house.setRoof(roof);
        return this;
    }

    public HouseBuilder buildInterior(String interior) {
        house.setInterior(interior);
        return this;
    }

    public House getHouse() {
        return house;
    }
}
class CreationalBuilder{
    public static void main(String[] args) {
        HouseBuilder builder = new HouseBuilder();
        House house = builder.buildWalls("Brick Walls").buildRoof("Metal Roof").buildInterior("Modern Interior").getHouse();
        System.out.println(house);
    }
}
