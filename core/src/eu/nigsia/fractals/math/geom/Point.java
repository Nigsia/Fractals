package eu.nigsia.fractals.math.geom;

/**
 * 	This class represents a point in an infinite Cartesian coordinate system.
 * 	@autor Ignasi Sánchez Rodríguez (https://github.com/Nigsia/)
 *  @year  2015
 */
public class Point
{
	/**
	 * 	Represents the point's horizontal component.
	 */
	public int x;
	/**
	 * 	Represents the point's vertical component.
	 */
	public int y;
	
	/**
	 * 
	 * 	Sets a not trivial point in the coordinate system.
	 * 
	 * @param x: Horizontal component of the point.
	 * @param y: Vertical component of the point.
	 */
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 	Sets a trivial point in the coordinate system.
	 */
	public Point()
	{
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Calculates the mid point of the segment created by this point and p.
	 * @param p: The end point for the segment.
	 * @return A new point between this point and p.
	 */
	public Point midPoint(Point p)
	{
		return (new Point((this.x+p.getX())/2, (this.y+p.getY())/2));
	}

	/**
	 * Checks if this point and p are equal. 
	 * @param p: Point to check if it's equal.
	 */
    public boolean equals(Point p)
    {
	    return (x == p.x && y == p.y);
    }
	
    /**
     * Gets the x value.
     * @return the x value for this point
     */
	public int getX(){	return x; }
	/**
	 * Gets the y value.
	 * @return the y value for this point.
	 */
	public int getY(){	return y; }
	
	/**
	 * Sets the x value.
	 * @param x: the x value.
	 */
	public void setX(int x){	this.x = x;	}
	/**
	 * Sets the y value.
	 * @param y: the y value.
	 */
	public void setY(int y){	this.y = y; }
}
