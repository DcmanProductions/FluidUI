package chase.minecraft.architectury.fluidui;

import chase.minecraft.architectury.fluidui.impl.AsyncThemeLoader;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;

import java.io.BufferedReader;
import java.io.IOException;

public class FluidTheme
{
	
	protected int backgroundColor;
	protected int primaryColor;
	protected int widgetBackgroundColor;
	protected int widgetForegroundColor;
	protected int widgetHoverBackgroundColor;
	protected int widgetHoverForegroundColor;
	protected int dropdownHoverBackgroundColor;
	protected int dropdownHoverForegroundColor;
	protected int borderColor;
	protected final ResourceLocation themeLocation;
	public static final FluidTheme DEFAULT = new FluidTheme(FluidUI.MOD_ID, "default");
	public static final FluidTheme DARK = new FluidTheme(FluidUI.MOD_ID, "dark");
	protected final String themeName;
	
	/**
	 * Create a theme with the mod id and theme name<br \>
	 * Theme file is in <i>resources/assets/{modId}/fluid/themes/{themeName}.json</i>
	 *
	 * @param modId     The id of your mod
	 * @param themeName The name of the theme, not including the .json
	 */
	public FluidTheme(String modId, String themeName)
	{
		themeLocation = new ResourceLocation(modId, "fluid/themes/%s.json".formatted(themeName));
		this.themeName = themeName;
		AsyncThemeLoader.getInstance().put(this);
	}
	
	public static FluidTheme register(String modId, String themeName)
	{
		return new FluidTheme(modId, themeName);
	}
	
	/**
	 * Loads theme from file. This is automatically run when the title screen is loaded for the first time.
	 */
	public void load()
	{
		try
		{
			FluidUI.log.info("Loading Theme: {}", themeName);
			BufferedReader in = Minecraft.getInstance().getResourceManager().openAsReader(themeLocation);
			JsonObject json = GsonHelper.parse(in);
			backgroundColor = Integer.decode(json.get("backgroundColor").getAsString());
			primaryColor = Integer.decode(json.get("primaryColor").getAsString());
			widgetBackgroundColor = Integer.decode(json.get("widgetBackgroundColor").getAsString());
			widgetForegroundColor = Integer.decode(json.get("widgetForegroundColor").getAsString());
			widgetHoverBackgroundColor = Integer.decode(json.get("widgetHoverBackgroundColor").getAsString());
			widgetHoverForegroundColor = Integer.decode(json.get("widgetHoverForegroundColor").getAsString());
			dropdownHoverBackgroundColor = Integer.decode(json.get("dropdownHoverBackgroundColor").getAsString());
			dropdownHoverForegroundColor = Integer.decode(json.get("dropdownHoverForegroundColor").getAsString());
			borderColor = Integer.decode(json.get("borderColor").getAsString());
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	void parse(BufferedReader reader)
	{
		// parse json
	}
	
	public ResourceLocation getThemeLocation()
	{
		return themeLocation;
	}
	
	public String getThemeName()
	{
		return themeName;
	}
	
	public int getBackgroundColor()
	{
		return backgroundColor;
	}
	
	public int getPrimaryColor()
	{
		return primaryColor;
	}
	
	public int getWidgetBackgroundColor()
	{
		return widgetBackgroundColor;
	}
	
	public int getWidgetForegroundColor()
	{
		return widgetForegroundColor;
	}
	
	public int getWidgetHoverBackgroundColor()
	{
		return widgetHoverBackgroundColor;
	}
	
	public int getWidgetHoverForegroundColor()
	{
		return widgetHoverForegroundColor;
	}
	
	public int getDropdownHoverBackgroundColor()
	{
		return dropdownHoverBackgroundColor;
	}
	
	public int getDropdownHoverForegroundColor()
	{
		return dropdownHoverForegroundColor;
	}
	
	public int getBorderColor()
	{
		return borderColor;
	}
}
