package fractal;

import koch.Koch;
import mountain.Mountain;
import mountain.Point;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[2];
		fractals[0] = new Koch(300);
		fractals[1] = new Mountain( new Point(50,400), new Point(300,50), new Point(550,550), 20);
	    new FractalView(fractals, "Fraktaler", 600, 600);
	}

}
