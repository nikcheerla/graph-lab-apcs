public class Point {
	double x, y;
	public Point(double xx, double yy) {
		x = xx; y = yy;
	}
	public static double dist(Point a, Point b) {
		return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y)*(a.y - b.y));
	}
}