public class Point {

    double x = 0;
    double y = 0;

    public static double distance(Point p1, Point p2) {
        double l = 0;
        l = Math.sqrt(Math.pow((p2.x-p1.x),2) + Math.pow((p2.y-p1.y),2));
        return l;
    }

    public static void main(String[] args) {
        Point p1 = new Point();
        Point p2 = new Point();
        Point p3 = new Point();
        Point p4 = new Point();

        p1.x = 0;
        p1.y = 0;

        p2.x = 1;
        p2.y = 1;

        p3.x = -1;
        p3.y = -1;

        p4.x = 0;
        p4.y = 3;

        double res1 = 0;
        res1 = distance(p1, p2);
        System.out.println(res1);

        double res2 = 0;
        res2 = distance(p1, p3);
        System.out.println(res2);

        double res3 = 0;
        res3 = distance(p1, p4);
        System.out.println(res3);

        double res4 = 0;
        res4 = distance(p2, p3);
        System.out.println(res4);
    }
}
