 package eu.nigsia.fractals.state;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import eu.nigsia.fractals.Main;
import eu.nigsia.fractals.util.Res;
import static eu.nigsia.fractals.Main.HEIGHT;
import static eu.nigsia.fractals.Main.WIDTH;;

/**
 * 	This is the class that every state should inherit. 
 * 	@autor Ignasi Sánchez Rodríguez (https://www.github.com/Nigsia)
 *  @year  2015
 */
public abstract class State implements InputProcessor
{

	/**
	 * 	The {@link StateManager} instance created in the {@link Main} class.
	 */
	protected StateManager sm;
	/**
	 * 	The main {@link OrthographicCamera}. 
	 */
	protected OrthographicCamera cam;
	
	/**
	 * 	This is the texture of a pixel. It can be reescaled as the user wants to. It's used as a monochromatic texture sometimes.
	 */
	protected TextureRegion pixel;
	/**
	 * 	Main font.
	 */
	protected BitmapFont font;
	
	/**
	 * 	Default constructor for all states. This constructor initializes and sets the ortographic camera. It also initializes {@link #font} and {@link #pixel}
	 * @param sm: The {@link StateManager} instance created in the {@link Main} class.
	 */
	public State(StateManager sm)
	{
		this.sm = sm;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, WIDTH, HEIGHT);
		
		font = Res.getFont(Res.Fonts.FONT1);
		pixel = Res.getTexture(Res.Textures.PIXEL);
	}
	
	/**
	 * 	Default method for updating any state.
	 * @param dt: Delta time in case any update needs it.
	 */
	protected abstract void update(float dt);
	/**
	 * 	Default method for rendering any state.
	 *  @param sb: {@link SpriteBatch}. Passed from {@link Main}. It's the only {@link SpriteBatch} being rendered.
	 */
	protected abstract void render(SpriteBatch sb);
	
	/**
	 *  Since {@link Main#WIDTH} is imported staticly into this class, we have a method to get it in all states.
	 *	@return Window's width.
	 */
	protected int getWidth() {return WIDTH;}
	/**
	 * 	 Since {@link Main#HEIGHT} is imported staticly into this class, we have a method to get it in all states.
 	 * 	 @return Window's height.	
	 */
	protected int getHeight(){return HEIGHT;}
	
	/**
	 * 	This are all the methods {@link InputProcessor} adds. We can then overwrite them if some state needs it.
	 */
	
	/*
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#keyDown(int)
	 */
	@Override public boolean keyDown(int keycode) { return false; }
	/*
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#keyTyped(char)
	 */
	@Override public boolean keyTyped(char character) { return false; }
	/*
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#keyUp(int)
	 */
	@Override public boolean keyUp(int keycode) { return false; }
	/*
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#mouseMoved(int, int)
	 */
	@Override public boolean mouseMoved(int screenX, int screenY) { return false; }
	/*
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#scrolled(int)
	 */
	@Override public boolean scrolled(int amount) { return false; }
	/*
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#touchDown(int, int, int, int)
	 */
	@Override public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }
	/*
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#touchDragged(int, int, int)
	 */
	@Override public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
	/*
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#touchUp(int, int, int, int)
	 */
	@Override public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }
	
}
