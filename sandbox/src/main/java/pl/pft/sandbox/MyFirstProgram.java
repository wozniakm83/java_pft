package pl.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("World");
        hello("Michal");

        Point p1 = new Point(2, 3);
        Point p2 = new Point(1, 4);

        System.out.println("Distance between point p1 and point p2 is:");
        System.out.println("sqrt[(" + p2.x + " - " + p1.x + ")^2 + (" + p2.y + " - " + p1.y + ")^2] =");
        System.out.println(" = " + p1.distance(p2));
    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }

}