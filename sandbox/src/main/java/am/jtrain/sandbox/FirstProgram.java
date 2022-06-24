package am.jtrain.sandbox;

public class FirstProgram {
	
	public static void main(String[] args) {

		Point p1 = new Point(0,0);
		Point p2 = new Point(1, 1);

		Point p3 = new Point(-3.5,0);
		Point p4 = new Point(4, 18);

		System.out.println("Расстояние между точками (" + p1.x + ";" + p1.y + ")" + " и (" + p2.x + ";" + p2.y + ")"
				+ " равняется " + p1.distance(p1, p2));

		System.out.println("Расстояние между точками (" + p3.x + ";" + p3.y + ")" + " и (" + p4.x + ";" + p4.y + ")"
				+ " равняется " + p4.distance(p3, p4));
	}
}