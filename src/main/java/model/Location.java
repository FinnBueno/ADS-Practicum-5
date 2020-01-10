package model;

/**
 * @author Finn Bon
 */
public class Location {

	private static final double TIME_PER_SQUARE = 1.5;

	private int x;
	private int y;

	public Location(int[] coordinates) {
		this.x = coordinates[0];
		this.y = coordinates[1];
	}

	public double travelTime(Location location) {
		if (this == location) {
			return 0;
		}
		int distanceX = location.x - this.x;
		int distanceY = location.y - this.y;
		return Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2)) * TIME_PER_SQUARE;
	}

}
