package chase.minecraft.architectury.fluidui;

public class FluidTheme
{
	
	private static int backgroundColor = 0x0D1010;
	private static int primaryColor = 0x5ce24c;
	private static int widgetBackgroundColor = 0x1D1D1D;
	private static int widgetForegroundColor = 0xFFFFFF;
	private static int widgetHoverBackgroundColor = 0x262626;
	private static int widgetHoverForegroundColor = 0xFFFFFF;
	private static int dropdownHoverBackgroundColor = 0x333333;
	private static int dropdownHoverForegroundColor = 0xFFFFFF;
	private static int borderColor = 0x24F65F;
	
	private FluidTheme()
	{
	}
	
	public static int getDropdownHoverBackgroundColor()
	{
		return dropdownHoverBackgroundColor;
	}
	
	public static void setDropdownHoverBackgroundColor(int dropdownHoverBackgroundColor)
	{
		FluidTheme.dropdownHoverBackgroundColor = dropdownHoverBackgroundColor;
	}
	
	public static int getDropdownHoverForegroundColor()
	{
		return dropdownHoverForegroundColor;
	}
	
	public static void setDropdownHoverForegroundColor(int dropdownHoverForegroundColor)
	{
		FluidTheme.dropdownHoverForegroundColor = dropdownHoverForegroundColor;
	}
	
	public static int getBackgroundColor()
	{
		return backgroundColor;
	}
	
	public static void setBackgroundColor(int backgroundColor)
	{
		FluidTheme.backgroundColor = backgroundColor;
	}
	
	public static int getPrimaryColor()
	{
		return primaryColor;
	}
	
	public static void setPrimaryColor(int primaryColor)
	{
		FluidTheme.primaryColor = primaryColor;
	}
	
	public static int getWidgetBackgroundColor()
	{
		return widgetBackgroundColor;
	}
	
	public static void setWidgetBackgroundColor(int widgetBackgroundColor)
	{
		FluidTheme.widgetBackgroundColor = widgetBackgroundColor;
	}
	
	public static int getWidgetForegroundColor()
	{
		return widgetForegroundColor;
	}
	
	public static void setWidgetForegroundColor(int widgetForegroundColor)
	{
		FluidTheme.widgetForegroundColor = widgetForegroundColor;
	}
	
	public static int getWidgetHoverBackgroundColor()
	{
		return widgetHoverBackgroundColor;
	}
	
	public static void setWidgetHoverBackgroundColor(int widgetHoverBackgroundColor)
	{
		FluidTheme.widgetHoverBackgroundColor = widgetHoverBackgroundColor;
	}
	
	public static int getWidgetHoverForegroundColor()
	{
		return widgetHoverForegroundColor;
	}
	
	public static void setWidgetHoverForegroundColor(int widgetHoverForegroundColor)
	{
		FluidTheme.widgetHoverForegroundColor = widgetHoverForegroundColor;
	}
	
	public static int getBorderColor()
	{
		return borderColor;
	}
	
	public static void setBorderColor(int borderColor)
	{
		FluidTheme.borderColor = borderColor;
	}
}
