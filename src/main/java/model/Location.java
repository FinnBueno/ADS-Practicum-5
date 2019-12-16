package model;

public class Location {
    private double x;
    private double y;
    private final double squareTravelTime = 1.5;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double travelTime(Location toLocation){
        double distance = Math.sqrt((toLocation.getY() - this.getY()) * (toLocation.getY() - this.getY()) + (toLocation.getX() - this.getX()) * (toLocation.getX() - this.getX()));
        return distance * squareTravelTime;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
