package eu.nigsia.fractals;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import eu.nigsia.fractals.state.MenuState;
import eu.nigsia.fractals.state.State;
import eu.nigsia.fractals.state.StateManager;
import eu.nigsia.fractals.util.Res;

/**
 * 	This is the main class of the application. It loads resources and calls every render and update method.
 * 	@autor Ignasi Sánchez Rodríguez (https://www.github.com/Nigsia)
 *  @year  2015
 */
public class Main extends ApplicationAdapter
{
	/**
	 * 	Title displayed on the window.
	 */
	public static final String TITLE = "Fractals";
	/**
	 * 	Width of the window.
	 */
	public static final int WIDTH  = 500;
	/**
	 * 	Height of the window.
	 */
	public static final int HEIGHT = 500;
	
	/*
	 * TODO: Add Spritebatch to render fonts & GUI.
	 */
	/**
	 * 	Spritebatch used to render.
	 */
	private SpriteBatch batch;
	/**
	 * 	State manager. Used to switch between states. This is the only instance of {@link StateManager}. Passed through the constructor of every {@link State}.
	 */
	private StateManager sm;
	
	/**
	 * 	Method inherited from {@link ApplicationAdapter}. Called once when the application is started.
	 */
	@Override
	public void create () 
	{
		batch = new SpriteBatch();
		Res.init();
		Res.loadAtlas();
		sm = new StateManager();
		sm.push(new MenuState(sm));
		//sm.push(new SierpinskyState(sm));
		//sm.push(new MandelbrotState(sm));
	}
	
	/**
	 * 	Method inherited from {@link ApplicationAdapter}. Called every frame. Clears the screen with color (0.8f, 0.8f, 0.8f, 1.0f) (color displayed as RGBA). 
	 * 	Finally, it calls {@link SpriteBatch#begin()} and {@link SpriteBatch#end()} at the beggining and end of every {@link StateManager#update(float)} and {@link StateManager#render(SpriteBatch)} call.
	 */
	@Override
	public void render () 
	{
		batch.begin();
		
		Gdx.gl.glClearColor(.8f, .8f, .8f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sm.update(Gdx.graphics.getDeltaTime());
		sm.render(batch);
		
		batch.end();
	}
}
