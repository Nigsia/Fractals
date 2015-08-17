package eu.nigsia.fractals.math.geom;

/**
 * 	This class represents a rectangle positioned on an infinite Cartesian axes.
 * 	@autor Ignasi Sánchez Rodríguez (https://www.github.com/Nigsia)
 *  @year  2015
 */
public class Rectangle 
{
	/**
	 * 	Represents the lower left point's horizontal component.
	 */
	public int x;
	/**
	 * 	Represents the lower left point's vertical component.
	 */
	public int y;
	/**
	 * 	Represents the rectangle's width.
	 */
	public int width;
	/**
	 * 	Represents the rectangle's height.
	 */
	public int height;
	
	/**
	 * Creates a new rectangle with the following parameters:
	 * 
	 * @param x: x component for the lower left point.
	 * @param y: y component for the lower left point.
	 * @param w: rectangle's width.
	 * @param h: rectangle's height.
	 */
	public Rectangle(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
	
	/**
	 * Creates a new rectangle from the following points:
	 * 
	 * @param bottomLeftCorner
	 * @param topRightCorner
	 */
	public Rectangle(Point bottomLeftCorner, Point topRightCorner)
	{
		this.x = bottomLeftCorner.getX();
		this.y = bottomLeftCorner.getY();
		this.width = topRightCorner.getX() - x;
		this.height = topRightCorner.getY() - y;
	}
	
	/**
	 * Returns whether the point p is inside the rectangle or not.
	 * 
	 * @param p: Point that we want to check.
	 */
	public boolean contains(Point p)
	{
		return contains(p.x, p.y);
	}
	
	/**
	 * Returns whether the point with components (x, y) is inside the rectangle or not.
	 * @param x: x component of the point.
	 * @param y: y component of the point.
	 */
	public boolean contains(int x, int y)
	{
		return 
				x >= this.x && 
				y >= this.y && 
				x <= this.x + width && 
				y <= this.y + height;
	}

	/**
	 * Gets the lower left corner's x component.
	 */
	public int getX(){	return x;	}
	/**
	 * Gets the lower left corner's y component.
	 */
	public int getY(){	return y;	}
	/**
	 * Gets the width of the rectangle.
	 */
	public int getWidth(){	return width;	}
	/**
	 * Gets the height of the rectangle.
	 */
	public int getH(){	return height;	}
	
	/**
	 * Sets  the lower left corner's x component.
	 */
	public void setX(int x){	this.x = x;	}
	/**
	 * Sets the lower left corner's y component.
	 */
	public void setY(int y){	this.y = y;	}
	/**
	 * Sets the width of the rectangle.
	 */
	public void setW(int w){	this.width = w;	}
	/**
	 * Sets the height of the rectangle.
	 */
	public void setH(int h){	this.height = h;	}
}
