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
	
	private int backgroundColor;
	private int primaryColor;
	private int widgetBackgroundColor;
	private int widgetForegroundColor;
	private int widgetHoverBackgroundColor;
	private int widgetHoverForegroundColor;
	private int dropdownHoverBackgroundColor;
	private int dropdownHoverForegroundColor;
	private int borderColor;
	private final ResourceLocation theme_location;
	public static final FluidTheme DEFUALT = new FluidTheme(FluidUI.MOD_ID, "default");
	public static final FluidTheme DARK = new FluidTheme(FluidUI.MOD_ID, "dark");
	private final String theme_name;
	
	public FluidTheme(String mod_id, String theme_name)
	{
		theme_location = new ResourceLocation(mod_id, "fluid/themes/%s.json".formatted(theme_name));
		this.theme_name = theme_name;
		AsyncThemeLoader.getInstance().put(this);
	}
	
	public void load()
	{
		try
		{
			FluidUI.log.info("Loading Theme: {}", theme_name);
			BufferedReader in = Minecraft.getInstance().getResourceManager().openAsReader(theme_location);
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
