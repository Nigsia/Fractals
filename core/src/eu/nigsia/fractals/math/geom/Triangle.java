package eu.nigsia.fractals.math.geom;

import java.util.Random;

/**
 * 	This class represents a triangle positioned on an infinite Cartesian axes.
 * 	@autor Ignasi Sánchez Rodríguez (https://www.github.com/Nigsia)
 *  @year  2015
 */
public class Triangle 
{
	/**
	 * 	Point that represents one of the three vertex of the triangle.
	 */
	private Point p;
	/**
	 * 	Point that represents one of the three vertex of the triangle.
	 */
	private Point q;
	/**
	 * 	Point that represents one of the three vertex of the triangle.
	 */
	private Point r;
	
	/**
	 * 	Random object in order to use {@link #getRandomVertex()}.
	 */
	private Random rand;
	
	/**
	 * 	Creates a new Triangle with the next parameters:
	 * 
	 * @param p: One of the three vertex. 
	 * @param q: One of the three vertex. 
	 * @param r: One of the three vertex. 
	 */
	public Triangle(Point p, Point q, Point r)
	{
		this.p = p;
		this.q = q;
		this.r = r;
		rand = new Random();
	}
	
	/**
	 * 	Gets a random vertex of the triangle.
	 * 
	 * @return a random vertex.
	 */
	public Point getRandomVertex()
	{
		switch(rand.nextInt(3))
		{
			case 0:
				return p;
			case 1:
				return q;
			case 2:
				return r;
			default:
				return null;
		}
	}

	/**
	 * Gets the vertex represented by p.
	 */
	public Point getP(){		return p;		}
	/**
	 * Gets the vertex represented by q.
	 */
	public Point getQ(){		return q;		}
	/**
	 *  Gets the vertex represented by r.
	 * @return
	 */
	public Point getR(){		return r;		}

	
}
