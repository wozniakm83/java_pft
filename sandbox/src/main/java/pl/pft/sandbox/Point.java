package pl.pft.sandbox;

public class Point {

    public double x, y;
    public double x1, y1, x2, y2;

    public Point (double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double distance(){
        return Math.sqrt(Math.pow((x2 - x1),2) + Math.pow((y2 - y1),2));
    }

}
