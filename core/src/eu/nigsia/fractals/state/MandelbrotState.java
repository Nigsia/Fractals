package eu.nigsia.fractals.state;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import eu.nigsia.fractals.Main;
import static com.badlogic.gdx.Input.Keys.*;
/**
 * 	This class calculates and renders a fractal based on Mandelbrot's Set.
 * 
 *  This are the keys that this class can use:
 * 
	 * 	Z: Zoom to bottom-Left corner.
	 *	X: See how iterations change the fractal's form. (Let's call it Iteration state when it's on).
	 *	S: Stop Iteration state.
	 *	C: Recalculate the fractal.
	 *	R: Reset position and zoom to the default values.
	 *  I: Increase by one the bitshift of the color.
	 *  U: Decrease by one the bitshift of the color.
	 *  P: Increase MAX_ITER by 500.
 *  Arrow Keys to move the fractal.
 *  Mouse wheel up to increase/decrease zoom.
 *  
 * 	@autor Ignasi Sánchez Rodríguez (https://www.github.com/Nigsia)
 *  @year  2015
 */
public class MandelbrotState extends FractalState 
{
	/**
	 */
	
	/**
	 * 	Used for debugging.
	 */
	private static final boolean DBG = false;
	
	/**
	 * 	Default number of iterations per pixel.
	 */
	private static final int DEFAULT_ITER = 1000;
	/**
	 * 	Default zoom value.
	 */
	private static final double defZOOM = 150;
	/**
	 * 	Minimum number to bitshift the color. {@link MandelbrotState#calculate()}
	 */
	private static final byte BITSHIFTMIN = 4;
	/**
	 * 	Maximum number to bitshift the color. {@see MandelbrotState##calculate()}
	 */
	private static final byte BITSHIFTMAX = 32;
	/**
	 * 	Number of pixels that are moved when moving with Arrow Keys or Zooming.
	 */
	private static final int STEP = 30;
	
	/**
	 * 	Number of iterarions per pixel. Default value is {@link MandelbrotState#DEFAULT_ITER}
	 */
	private static int MAX_ITER = DEFAULT_ITER; 
	
	/**
	 * 	Indicates the center X value of the fractal. Note: this is not a "world" coordinate but a custom coordinate on the fractal.
	 */
	private int centerX = getWidth()/2;
	/**
	 * 	Indicates the center Y value of the fractal. Note: this is not a "world" coordinate but a custom coordinate on the fractal.
	 */
	private int centerY = getHeight()/2;
	
	/**
	 * 	Indicates the zoom added when scrolling the mouse wheel.
	 */
	private int zoomAdded = 0;
	
	/**
	 * 	Indicates the <i>z_n</i> (in the Real axis) as shown in Wikipedia's formula {@link https://en.wikipedia.org/wiki/Mandelbrot_set}
	 */
	private double zx = 0;
	/**
	 * 	Indicates the <i>z_n</i> (in the Complex axis) as shown in Wikipedia's formula {@link https://en.wikipedia.org/wiki/Mandelbrot_set}
	 */
	private double zy = 0;
	/**
	 * 	Indicates the constant <i>c</i> (in the Real axis) as shown in Wikipedia's formula {@link https://en.wikipedia.org/wiki/Mandelbrot_set}
	 */
	private double cX = 0;
	/**
	 * 	Indicates the constant <i>c</i> (in the Complex axis) as shown in Wikipedia's formula {@link https://en.wikipedia.org/wiki/Mandelbrot_set}
	 */
	private double cY = 0;
	
	/**
	 * 	Used to make the Iteration State. Iteration State will increase the iteration number each second.
	 */
	private long lastTime = System.nanoTime();
	/**
	 * 	Used to toggle the Iteration State.
	 */
	private boolean doIterationLoop = false;
	
	/**
	 * 	Current color bitshift {@link MandelbrotState#calculate()}
	 */
	private byte bitshift = 4;

	
	/** 
	 * 	Default constructor inherited from {@link State}.
	 * 
	 * @param sm: The {@link StateManager} instance created in the {@link Main} class.
	 */
	public MandelbrotState(StateManager sm) 
	{
		super(sm);
	}

	/**
	 * 	Method that calculates the fractal using Wikipedia's formula {@link https://en.wikipedia.org/wiki/Mandelbrot_set}.
	 * 	Every pixel of the window is drawn to a {@link Pixmap}. When the loop finishes, it stores creates a new {@link FractalState#fractal} from the {@link Pixmap}. 
	 */
	@Override
	protected final void calculate()
	{
		// Zoom
		double zoom = defZOOM + zoomAdded;
		if(zoom <= 0)
			zoom = defZOOM;
		
		
		double tmp;
		if(DBG)
			System.out.println("Calculating...");
		
		// Calculate
		for (int x = 0; x < getWidth(); x++)
		{
			for (int y = 0; y < getHeight(); y++)
			{
                zx = zy = 0;
                cX = (x - centerX) / zoom;
                cY = (y - centerY) / zoom;
                int iter = MAX_ITER;
                while (zx * zx + zy * zy < 4.0 && iter > 0)
                {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
              
                pixmap.setColor(iter | iter << bitshift);
                pixmap.drawPixel(x, y);
            
			}
        }
		
		if(DBG)
			System.out.println("Finished!");
		
		fractal = new TextureRegion(new Texture(pixmap));
		// We reset the Pixmap.
		pixmap = new Pixmap(getWidth(), getHeight(), Pixmap.Format.RGBA4444);
		
		if(DBG)
			System.out.println("\n--------------------" + "Current:" + "\ndx: " + centerX + "\ndy: " + centerY + "\nzoom: " + zoom + "\n--------------------\n" + bitshift);
	}

	/**
	 * 	Method inherited from {@link State}. Used to update this state.
	 * @param dt: Delta time in case any update needs it.
	 */
	@Override
	protected final void update(float dt) 
	{	
		if(doIterationLoop)
		{
			if((System.nanoTime() - lastTime)/1000000000 >= 1)
			{
				MAX_ITER += 5; 
				lastTime = System.nanoTime();
				calculate();
			}
		}
	}

	/**
	 * 	Method inherited from {@link State}. Used to draw everything on this state.
	 *  @param sb: {@link SpriteBatch}. Passed from {@link Main}. It's the only {@link SpriteBatch} being rendered.
	 */
	@Override
	protected final void render(SpriteBatch sb) 
	{
		sb.draw(fractal, 0, 0);
	}
	
	/**
	 * 	Method inherited from {@link State}. This method is called every time a key is pressed. It also has all the code executed when the keys used by this class are pressed.
	 * 	@param keycode: Every Keycode is imported staticly to this class from {@link Keys}
	 */
	@Override 
	public final boolean keyDown(int keycode)
	{
		switch(keycode)
		{
		case P:
			MAX_ITER += 500;
			calculate();
			break;
		case I:
			bitshift++;
			if(bitshift >= BITSHIFTMAX)
				bitshift = BITSHIFTMAX;
			calculate();
			break;
		case U:
			bitshift--;
			if(bitshift <= BITSHIFTMIN)
				bitshift = BITSHIFTMIN;
			calculate();
			break;
		case R:
			centerX = 250;
			centerY = 250;
			zoomAdded = 0;
			calculate();
			break;
		case Z:
			centerX = 1552;
			centerY = 4840;
			zoomAdded = 6230;
			calculate();
			break;
		case X:
			MAX_ITER = 15;
			doIterationLoop = true;
			lastTime = System.nanoTime();
			break;
		case S:
			MAX_ITER = DEFAULT_ITER;
			doIterationLoop = false;
			break;
		case C:
			calculate();
			break;
		case LEFT:
			centerX += STEP;
			calculate();
			break;
		case RIGHT:
			centerX -= STEP;
			calculate();
			break;
		case UP:
			centerY += STEP;
			calculate();
			break;
		case DOWN:
			centerY -= STEP;
			calculate();
			break;
		default:
				break;
		}

		return super.keyDown(keycode);
	}
	
	/**
	 * 	Method inherited from {@link State}. This method is called every time the mouse wheel is scrolled. It adds {@link #STEP}*-1 to the amount scrolled.
	 *  @param amount: This is the amount scrolled.
	 */
	@Override
	public final boolean scrolled(int amount)
	{
		zoomAdded += amount*STEP*-1;
		calculate();
		return super.scrolled(amount);
	}

}
