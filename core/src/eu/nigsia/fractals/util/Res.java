package eu.nigsia.fractals.util;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * 	This class is in charge of loading all resources (such as textures, fonts, ... ).
 * 	@autor Ignasi Sánchez Rodríguez (https://www.github.com/Nigsia)
 *  @year  2015
 */
public class Res 
{
	/**
	 *  Enum containing all the textures and it's names inside the pack. 
	 */
	public static enum Textures
	{
		PIXEL("pixel");
		
		String region;
		private Textures(String s){			this.region = s;		}

		public String getName(){			return region;		}
	}
	
	/**
	 * 	Enum containing all the fonts and it's names and file extensions.
	 */
	public static enum Fonts
	{
		FONT1("Znikomit.otf");
		
		String fileName;
		private Fonts(String s){			this.fileName = s;		}
		
		public String getName(){			return fileName;		}
	}
	
	/**
	 * 	{@link TextureAtlas} with all the textures.
	 */
	private static TextureAtlas atlas;
	/**
	 * 	Hasmap with the fonts.
	 */
	private static HashMap<Fonts, BitmapFont> fonts;
	
	/**
	 * 	Calls {@link #loadAtlas()} and initializes {@link #fonts} and creates every font. Default font size is 20. 
	 */
	// TODO: Add diferent font sizes.
	public static void init()
	{
		loadAtlas();
		fonts = new HashMap<Fonts, BitmapFont>();
		
		for(Fonts f : Fonts.values())
		{
			createFont(f,  "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", 20);
		}
		
	}
	
	
	/**
	 * 	Method that creates a {@link TextureAtlas} from a pack and set's it's filters to Linear.
	 */
	public static void loadAtlas()
	{
		atlas = new TextureAtlas("res/pack.pack");
		atlas.getTextures().first().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}
	
	/**
	 * 	Returns the {@link TextureRegion} attached to {@link Textures#getName()}.
	 * 	@param t: The {@link Textures} texture representation.
	 */
	public static TextureRegion getTexture(Textures t)
	{
		return atlas.findRegion(t.getName());
	}
	
	/**
	 * 	Creates a font (FreeTypeFont way).
	 * @param key: The {@link Fonts} font representation.
	 * @param chars: The characters that we want the font to have. Note that if the original font does not have a character and we add it here it will not find it.
	 * @param size: The font size.
	 */
	private static void createFont(Fonts key, String chars, int size)
	{
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("res/fonts/" + key.getName()));
		FreeTypeFontParameter params = new FreeTypeFontParameter();
		params.characters = chars;
		params.minFilter = TextureFilter.Linear;
		params.magFilter = TextureFilter.Linear;
		params.size = size;
		params.color = Color.BLACK;
		BitmapFont font = gen.generateFont(params);
		fonts.put(key, font);
		gen.dispose();
	}
	
	/**
	 * 	Returns the {@link BitmapFont} attached to {@link Fonts#getName()}
	 * @param key: The {@link Fonts} font representation.
	 * @return
	 */
	public static BitmapFont getFont(Fonts key)
	{
		return fonts.get(key);
	}
	
}
