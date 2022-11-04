package exercise;

// BEGIN
public class Circle {
    private final int radius;

    public Circle(Point point, int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (getRadius() < 0) {
            throw new NegativeRadiusException("");
        }
        return getRadius() * getRadius() * Math.PI;
    }
}
// END
