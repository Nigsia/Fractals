package eu.nigsia.fractals.state;

import eu.nigsia.fractals.Main;
import eu.nigsia.fractals.math.geom.Point;
import eu.nigsia.fractals.math.geom.Triangle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * 	This class calculates and renders Serpinski's triangle.
 * 	@autor Ignasi Sánchez Rodríguez (https://www.github.com/Nigsia)
 *  @year  2015
 */
public class SierpinskiState extends FractalState 
{
	/**
	 * 	Maximum number of iterations allowed.
	 */
	protected static final long MAX_ITER = 200000;
	
	/**
	 * 	The triangle's default color.
	 */
	private static final Color DEF_COL = new Color(1.0f, 0.0f, 1.0f, 1.0f);

	/** 
	 * 	Default constructor inherited from {@link State}.
	 * 
	 * @param sm: The {@link StateManager} instance created in the {@link Main} class.
	 */
	public SierpinskiState(StateManager sm) 
	{
		super(sm);
	}

	/**
	 * 	Method inherited from {@link State}. Used to update this state.
	 * @param dt: Delta time in case any update needs it.
	 */
	@Override
	protected void update(float dt) 
	{}
	
	/**
	 * 	Method inherited from {@link State}. Used to draw everything on this state.
	 *  @param sb: {@link SpriteBatch}. Passed from {@link Main}. It's the only {@link SpriteBatch} being rendered.
	 */
	@Override
	protected void render(SpriteBatch sb) 
	{
		sb.draw(fractal, 0, 0);
	}
	
	/**
	 * 	This method calculates Sierpinski's triangle. It follows the next algorithm:
	 *  1. Generate 3 points creating an imaginary triangle. Let's call those points <i>p</i>, <i>q</i> and <i>r</i>.
	 *  2. Let <i>mid</i> be the center of the triangle.
	 *  3. <b>for</b> iteration <i>MAX_ITER</i> <b>do</b>
	 *  4. 			Get a random vertex (<i>p</i>, <i>q</i> or <i>r</i>).
	 *  5.			<i>mid</i> = Mid point of the segment created by the random vertex and the last mid point.
	 *  6.			Draw the pixel represented by the mid point.
	 */
	protected void calculate()
	{
		pixmap.setColor(DEF_COL);
		int w = getWidth();
		int h = getHeight();
		
		Point p = new Point(w/2, 0);
		Point q = new Point(w, h);
		Point r = new Point(0, h);
		Triangle t = new Triangle(p, q, r);
		Point mid = new Point(w/2, h/2);
		
		for(int i = 0; i < MAX_ITER; i++)
		{
			mid = mid.midPoint(t.getRandomVertex());
			pixmap.drawPixel(mid.x, mid.y);
		}
		
		fractal = new TextureRegion(new Texture(pixmap), pixmap.getWidth(), pixmap.getHeight());
		
	}
}
