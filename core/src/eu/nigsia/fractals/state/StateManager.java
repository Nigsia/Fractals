package eu.nigsia.fractals.state;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import eu.nigsia.fractals.Main;

/**
 * 	This class is in charge of switching between states. It also renders and updates the current state.
 * 	@autor Ignasi Sánchez Rodríguez (https://www.github.com/Nigsia)
 *  @year  2015
 */
public class StateManager
{
	/**
	 * 	{@link LinkedList} that stores all states.
	 */
	private LinkedList<State> states;
	
	/**
	 * 	Initializes {@link #states}
	 */
	public StateManager()
	{
		states = new LinkedList<State>();
	}
	
	/**
	 * 	Updates the current state.
	 * @param dt: Delta time in case any update needs it.
	 */
	public void update(float dt)
	{
		states.peek().update(dt);
	}
	
	/**
	 * 	Renders the current state.
	 *  @param sb: {@link SpriteBatch}. Passed from {@link Main}. It's the only {@link SpriteBatch} being rendered.
	 */
	public void render(SpriteBatch sb)
	{
		states.peek().render(sb);
	}
	
	/**
	 * {@link LinkedList#pop()}
	 */
	public void pop()
	{
		states.pop();
	}
	
	/**
	 * {@link LinkedList#push(Object)}
	 * 
	 * @param s: {@link State} that we want to push.
	 */
	public void push(State s)
	{
		states.push(s);
	}
	
	/**
	 * Pops and pushes s to be the current state.
	 * @param s: The state that we want to set.
	 */
	public void set(State s)
	{
		pop();
		push(s);
	}
	
}
