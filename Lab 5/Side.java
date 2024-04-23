package mountain;

public class Side {
	private Point p1;
	private Point p2;
	
	public Side(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	@Override
	public boolean equals(Object o1) {
		if(o1 instanceof Side) {
			Point point1 = ((Side) o1).getp1();
			Point point2 = ((Side) o1).getp2();
			if(point1.getX() == p1.getX() && point1.getY() == p1.getY()) {
				if (point2.getX() == p2.getX() && point2.getY() == p2.getY()) {
					return true;	
				}
			} else if(point2.getX() == p1.getX() && point2.getY() == p1.getY()) {
					if (point1.getX() == p2.getX() && point1.getY() == p2.getY()) {
						return true;	
				}
			}
		}
			return false;	
	}
	public Point midPoint(double dev) {
		int deviation = (int) RandomUtilities.randFunc(dev);
		Point midpoint = new Point((p2.getX() - p1.getX()) / 2 + p1.getX(), (p1.getY() - p2.getY() + deviation) / 2 + p2.getY());
		return midpoint;
	}
	
	public Point getp1() {
		return p1;
	}
	public Point getp2() {
		return p2;
	}
	
    @Override
    public int hashCode() {
        return p1.hashCode() + p2.hashCode();
    }
}
