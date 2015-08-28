import java.util.*;

public class Graph {
	ArrayList<Point> points = new ArrayList<Point> ();
	double INF = 10000000010.0;
	double minx = -10, miny = -10, maxx = 10, maxy = 10;
	static int increments = 10;

	public Graph(){

	}

	public Graph(ArrayList<Point> pts) {
		points = new ArrayList<Point> (pts);
	}


	public boolean plot(double x, double y) {
		Point cur = new Point(x, y);
		points.add(cur);
		return outOfBounds(cur);
	}

	public void setBounds() {
		minx = INF; miny = INF; maxx = -INF; maxy = -INF;
		for(Point pt: points) {
			minx = Math.min(minx, pt.x);
			miny = Math.min(miny, pt.y);
			maxx = Math.max(maxx, pt.x);
			maxy = Math.max(maxy, pt.y);
		}
	}

	public void setBounds(int cminx, int cminy, int cmaxx, int cmaxy) {
		minx = cminx; miny = cminy; maxx = cmaxx; maxy = cmaxy;
	}

	public Picture getGraph() {
		Picture beach = new Picture("beach.jpg");
		Pixel[][] pixels = beach.getPixels2D();

		int xl = pixels.length;
		int yl = pixels[0].length;

		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[0].length; j++) {
				pixels[i][j].setRed(255);
				pixels[i][j].setBlue(255);
				pixels[i][j].setGreen(255);
			}
		}

		for (Point pt: points) {
			if(outOfBounds(pt)) continue;
			int px = (int)(xl*(pt.x - minx)/(maxx - minx));
			int py = (int)(yl*(pt.y - miny)/(maxy - miny));
			pixels[px][py].setRed(0);
			pixels[px][py].setGreen(0);
			pixels[px][py].setBlue(0);
			System.out.println(pt.x + ", " + pt.y + ":::" + px + ", " + py);
		}

		if (minx < 0 && maxx > 0) {
			int px = (int)(xl*(0 - minx)/(maxx - minx));
			for(int j = 0; j < pixels[0].length; j++) {
				pixels[px][j].setRed(0);
				pixels[px][j].setGreen(0);
				pixels[px][j].setBlue(0);
			}
		}

		if (miny < 0 && maxy > 0) {
			int py = (int)(yl*(0 - miny)/(maxy - miny));
			for(int i = 0; i < pixels.length; i++) {
				pixels[i][py].setRed(0);
				pixels[i][py].setGreen(0);
				pixels[i][py].setBlue(0);
			}
		}
		return beach;
	}

	public void view() {
		getGraph().explore();
	}

	public boolean outOfBounds(Point p) {
		return p.x <= minx || p.x >= maxx || p.y <= miny || p.y >= maxy;
	}

	public double windowMinX(){
		return minx;
	}

	public double windowMinY(){
		return miny;
	}

	public double windowMaxX(){
		return maxx;
	}

	public double windowMaxY(){
		return maxy;
	}

	public static void main(String[] args) {
		Graph g = new Graph();
		for(double x = -1.0; x < 1.0; x+= 0.0001) {
			g.plot(x, Math.asin(x));
		}
		g.setBounds();
		g.view();
	}

}