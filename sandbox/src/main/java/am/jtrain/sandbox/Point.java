package am.jtrain.sandbox;

public class Point {

    public double x;
    public double y;

    public Point(double a, double b) {
        x = a;
        y = b;
    }
    // Применяется к объекту класса. Вычисляет расстояние от объекта до указанной в качестве параметра точки
    public double distance(Point p) {

        return(Math.sqrt(Math.pow((this.x-p.x),2) + Math.pow((this.y-p.y),2)));
    }

}
