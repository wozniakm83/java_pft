package pl.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("World");
        hello("Michal");

        Point p1 = new Point();
        p1.x = 2;
        p1.y = 3;

        Point p2 = new Point();
        p2.x = 1;
        p2.y = 4;

        System.out.println("Distance between point p1 and point p2 is:");
        System.out.println("|p1 p2| = ");
        System.out.println(" = sqrt[(" + p2.x + " - " + p1.x + ")^2 + (" + p2.y + " - " + p1.y + ")^2] =");
        System.out.println(" = " + distance(p1, p2));
    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }

    public static double distance(Point p1, Point p2){
        return Math.sqrt(Math.pow((p2.x - p1.x),2) + Math.pow((p2.y - p1.y),2));
    }

}