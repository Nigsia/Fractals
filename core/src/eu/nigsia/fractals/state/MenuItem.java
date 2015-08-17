package eu.nigsia.fractals.state;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import eu.nigsia.fractals.Main;
import eu.nigsia.fractals.math.geom.Point;
import eu.nigsia.fractals.math.geom.Rectangle;
import eu.nigsia.fractals.util.Res;

/**
 * 	This class represents every button from the {@link MenuState}.
 * 	@autor Ignasi Sánchez Rodríguez (https://www.github.com/Nigsia)
 *  @year  2015
 */
public class MenuItem 
{
	/**
	 * 	Button's default width value.
	 */
	private static final int WIDTH = 200;
	/**
	 * 	Button's default height value.
	 */
	private static final int HEIGHT = 50;
	
	/**
	 * 	{@link FractalState} that the button represents.
	 */
	private FractalState state;
	/**
	 * 	{@link TextureRegion} for the button when hovered.
	 */
	private TextureRegion buttonHovered; 
	/**
	 * 	{@link TextureRegion} for the button when not hovered.
	 */
	private TextureRegion buttonUnhovered;
	
	/**
	 * 	The button's label.
	 */
	private String label;
	/**
	 * 	The button's font.
	 */
	private BitmapFont labelFont;
	
	/**
	 * 	Rectangle representing the button.
	 */
	private Rectangle rect;
	
	/**
	 * 	Boolean value indicating whether the mouse is hovering the button or not.
	 */
	private boolean isHovered = false;
	
	/**
	 * 	{@link GlyphLayout} used to calculate the {@link MenuItem#label} width.
	 */
	private GlyphLayout gl;
	/**
	 * 	The {@link #label}'s width.
	 */
	private float strWidth;
	
	/**
	 * 	Default constructor. Asks for:
	 * 
	 * @param state: 		The {@link FractalState} that the button refers to. 
	 * @param label: 		The button's {@link #label}
	 * @param x:	 		The lower left corner's x component.
	 * @param y:	 		The lower left corner's y component.
	 * @param unhovered:	The {@link Color} for the button when unhovered.
	 * @param hovered: :	The {@link Color} for the button when hovered.
	 */
	public MenuItem(FractalState state, String label, int x , int y, Color unhovered, Color hovered) 
	{
		this.state = state;
		this.label 	 = label;
		this.rect = new Rectangle(x, y, WIDTH, HEIGHT);

		labelFont	  = Res.getFont(Res.Fonts.FONT1);
		buttonHovered = createButton(hovered);
		buttonUnhovered = createButton(unhovered);
		
		gl = new GlyphLayout();
		gl.setText(labelFont, label);
		strWidth = gl.width;
	}
	
	/**
	 * 	Draws the rectangle (with the apropiate color wheter it's hovered or unhovered) and the {@link #label}.
	 * @param sb: {@link SpriteBatch}. Passed from {@link Main}. It's the only {@link SpriteBatch} being rendered.
	 */
	public void draw(SpriteBatch sb)
	{
		// Draw Rectangle
		if(isHovered)
			sb.draw(buttonHovered, rect.x, rect.y, WIDTH, HEIGHT);
		else
			sb.draw(buttonUnhovered, rect.x, rect.y, WIDTH, HEIGHT);
		
		//	Draw Label
		labelFont.draw(sb, label, rect.x+(strWidth/2), rect.y+32);
	}
	
	/**
	 * 	Creates the {@link TextureRegion} for the button based in the rectangle {@link #rect} and the color c.
	 * @param c: The color for the button.
	 */
	private TextureRegion createButton(Color c)
	{
		Pixmap p = new Pixmap(WIDTH, HEIGHT, Pixmap.Format.RGBA4444);
		
		p.setColor(c);
		for(int x = 0; x < WIDTH; x++)
			for(int y = 0; y < WIDTH; y++)
				p.drawPixel(x, y);
		
		TextureRegion t = new TextureRegion(new Texture(p));
		p.dispose();
		return t;
	}

	/**
	 * 	This method changes the state to {@link MenuItem#state}. It also sets it to be the input processor and finally it calls {@link FractalState#calculate()}.
	 */
	public void launchState(){ 	state.sm.push(state);
								state.setInputProcessor();
								state.calculate(); 		}
	
	/**
	 * Checks if a point is inside the button.
	 * 
	 * @param x: the point's x component.
	 * @param y: the point's y component.
	 */
	public boolean contains(int x, int y){		return rect.contains(x, state.getHeight()-y); /* We need to flip the y axis, since libGDX uses cartesian axes.*/	}
	/**
	 * Checks if a point is inside the button.
	 *	@param p: the point that we want to check. 
	 */
	public boolean contains(Point p){			return contains(p.x, p.y);	}

	/**
	 * 	Sets the current button to hovered.
	 * @param b: boolean value to set {@link #isHovered}.
	 */
	public void setHovered(boolean b){	this.isHovered = b;	}
	/**
	 *  Gets if the current button is hovered.
	 */
	public boolean isHovered()		 {	return isHovered;	}
	
}
