package eu.nigsia.fractals.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import eu.nigsia.fractals.Main;
import eu.nigsia.fractals.state.MenuItem;

/**
 *  This class is the main menu of the application. 
 * 	@autor Ignasi Sánchez Rodríguez (https://www.github.com/Nigsia)
 *  @year  2015
 */
public class MenuState extends State
{
	/**
	 * Used to debug.
	 */
	private static final boolean DBG = false;
	
	/**
	 * Array containing all the menu buttons.
	 */
	private MenuItem[] menuItems;
	
	/**
	 *  Default constructor inherited from {@link State}. Sets up all of the {@link MenuItem}s and adds them to {@link #menuItems}.
	 * @param sm: The {@link StateManager} instance created in the {@link Main} class.
	 */
	public MenuState(StateManager sm) 
	{
		super(sm);
		menuItems = new MenuItem[2];
		
		Gdx.input.setInputProcessor(this);
		
		int padding = 10;
		
		// Add menu items
		menuItems[0] = new MenuItem(
				new SierpinskiState(sm), 
				"Sierpinsky", 
				getWidth()/2-100, 
				getHeight()/2-25, 
				new Color(1.0f, 0.0f, 1.0f, 1.0f), 
				new Color(0.0f, 1.0f, 1.0f, 1.0f));
		menuItems[1] = new MenuItem(
				new MandelbrotState(sm), 
				"Mandelbrot", 
				getWidth()/2-100, 
				getHeight()/2-75-padding, 
				new Color(1.0f, 0.0f, 1.0f, 1.0f), 
				new Color(0.0f, 1.0f, 1.0f, 1.0f));
	}

	/**
	 * 	Method inherited from {@link State}. Used to update this state. It checks for all the {@link MenuItem} in {@link MenuState#menuItems} if anyone is being hovered.
	 * @param dt: Delta time in case any update needs it.
	 */
	@Override
	protected void update(float dt) 
	{
		// Hover
		int x = Gdx.input.getX();
		int y = Gdx.input.getY();
		
		for(MenuItem mi : menuItems)
		{
			if(mi.contains(x, y))
				mi.setHovered(true);
			else
				mi.setHovered(false);
		}
	}
	
	/**
	 * 	Method inherited from {@link State}. Used to draw everything on this state.
	 *  @param sb: {@link SpriteBatch}. Passed from {@link Main}. It's the only {@link SpriteBatch} being rendered.
	 */
	@Override
	protected void render(SpriteBatch sb)
	{
		//	Grid
		if(DBG)
		{
		 	sb.setColor(Color.BLACK);
		 	int step = 10;
		 	for(int i = 0; i < getWidth(); i+= step)
		 	{
		  		sb.draw(pixel, i, 0, 1, getHeight());
		 		sb.draw(pixel, 0, i, getWidth(), 1);
		 	}
		 	sb.setColor(Color.WHITE);
		}
		
		// Title
		font.draw(sb, "Fractals", getWidth()/2 - 40, getHeight()/8*7);
		
		// Draw MenuItems
		for(MenuItem mi : menuItems)
			mi.draw(sb);
		
	}

	/**
	 * 	Checks if a button is clicked and switches this state to the proper one.
	 */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(button == Input.Buttons.LEFT)
		{
			for(MenuItem mi : menuItems)
			{
				if(mi.isHovered())
					mi.launchState();
			}
		}
		return false;
	}
	
}
