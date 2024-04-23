package mountain;
import java.util.HashMap;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {
	private Point p1;
	private Point p2;
	private Point p3;
	private double dev;
	private HashMap<Side, Point> sides;
	
	public Mountain(Point p1, Point p2, Point p3, double dev){
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.dev = dev;
		sides = new HashMap<Side, Point>();
	}

	@Override
	public String getTitle() {
		return "Mountain";
	}

	@Override
	public void draw(TurtleGraphics g) {
		g.penDown();
		fractalLine(dev, g, p1, p2, p3, order);
		
	}
	private void fractalLine(double dev, TurtleGraphics turtle, Point p1, Point p2, Point p3, int order) {
		if (order == 0) {
			turtle.moveTo(p1.getX(), p1.getY());
			turtle.forwardTo(p2.getX(), p2.getY());
			turtle.forwardTo(p3.getX(), p3.getY());
			turtle.forwardTo(p1.getX(), p1.getY());
		}else {
			Side s1 = new Side(p1, p2);
			Side s2 = new Side(p2, p3);
			Side s3 = new Side(p3, p1);
			
			Point p4;
			Point p5;
			Point p6;
			
			if(sides.containsKey(s1)) {
				p4 = sides.remove(s1);
			} else {
				p4 = s1.midPoint(dev);
				sides.put(s1, p4);
			}
			if(sides.containsKey(s2)) {
				p5 = sides.remove(s2);
			} else {
				p5 = s2.midPoint(dev);
				sides.put(s2, p5);
			}
			if(sides.containsKey(s3)) {
				p6 = sides.remove(s3);
			} else {
				p6 = s3.midPoint(dev);
				sides.put(s3, p6);
			}
			
			//Point p4 = new Point( p1.getX() + (p2.getX() - p1.getX() )/2 , p2.getY() + (p1.getY() - p2.getY())/2 + (int)RandomUtilities.randFunc(dev));
			//Point p5 = new Point( p2.getX() + (p3.getX() - p2.getX())/2, p2.getY() + (p3.getY() - p2.getY())/2 + (int)RandomUtilities.randFunc(dev));
			//Point p6 = new Point( p1.getX() + (p3.getX() - p1.getX())/2, p1.getY() + (p3.getY() - p1.getY())/2 + (int)RandomUtilities.randFunc(dev));
			
			fractalLine(dev/2, turtle, p1, p4, p6, order-1);
			fractalLine(dev/2, turtle, p4, p2, p5, order-1);
			fractalLine(dev/2, turtle, p5, p3, p6, order-1);
			fractalLine(dev/2, turtle, p4, p5, p6, order-1);
		}
	}
	
}
