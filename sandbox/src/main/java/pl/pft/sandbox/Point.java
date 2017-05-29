package pl.pft.sandbox;

public class Point {

    public double x, y;

    public Point (double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p2) {
        return Math.sqrt(Math.pow((x - p2.x),2) + Math.pow((y - p2.y),2));
    }

}
