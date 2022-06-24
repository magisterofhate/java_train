package am.jtrain;

public class Point {

    public double x;
    public double y;

    public Point(double a, double b) {
        x = a;
        y = b;
    }
    public double distance(Point p1, Point p2) {

        return(Math.sqrt(Math.pow((p2.x-p1.x),2) + Math.pow((p2.y-p1.y),2)));
    }

}
