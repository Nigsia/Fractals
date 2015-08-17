package eu.nigsia.fractals.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import eu.nigsia.fractals.Main;

/**
 * 	This abstract class should be implemented by every fractal.
 * 	@autor Ignasi Sánchez Rodríguez (https://www.github.com/Nigsia)
 *  @year  2015
 */
public abstract class FractalState extends State
{
	/**
	 * 	This {@link Pixmap} is used to draw pixels while the fractal is being calculated. When the fractal is complete, we should create a new {@code #fractal} from this pixmap.
	 */
	protected Pixmap pixmap;
	/**
	 * 	This {@link TextureRegion} is used to render the fractal once it's calculated.
	 */
	protected TextureRegion fractal;
	
	/**
	 * 	Default constructor for every fractal. It initializes the {@link #pixmap} with format {@value Pixmap.Format.RGBA4444}.
	 * 
	 * @param sm: The {@link StateManager} instance created in the {@link Main} class.
 	 */
	public FractalState(StateManager sm)
	{
		super(sm);
		pixmap = new Pixmap(getWidth(), getHeight(), Pixmap.Format.RGBA4444);
	}
	
	/**
	 * 	Every fractal needs a calculation in order to draw it. This method should be called once or every time we want to recalculate. Using it on the render or update method is strongly discouraged.
	 */
	protected abstract void calculate();
	
	/**
	 * 	Sets the {@link FractalState} child that called it to be the input processor (Since {@link State} implements {@link InputProcessor}).
	 */
	protected void setInputProcessor()
	{
		Gdx.input.setInputProcessor(this);
	}
	
	/**
	 * 	Sets backspace as a "return to menu" key.
	 */
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.BACKSPACE)
		{
			sm.push(new MenuState(sm));
		}
		return super.keyDown(keycode);
	}
	
}
