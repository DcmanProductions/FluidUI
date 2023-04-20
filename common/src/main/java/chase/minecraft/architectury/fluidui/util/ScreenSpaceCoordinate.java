package chase.minecraft.architectury.fluidui.util;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;

public class ScreenSpaceCoordinate
{
	private final int top;
	private final int left;
	private final int right;
	private final int bottom;
	
	public ScreenSpaceCoordinate(int top, int left, int right, int bottom)
	{
		this.top = top;
		this.left = left;
		this.right = right;
		this.bottom = bottom;
	}
	
	public ScreenSpaceCoordinate(AbstractWidget widget)
	{
		this(widget.getY(), widget.getX(), widget.getX() + widget.getWidth(), widget.getY() + widget.getHeight());
	}
	
	
	public ScreenSpaceCoordinate(Screen screen)
	{
		this(0, 0, screen.width, screen.height);
	}
	
	public int getTop()
	{
		return top;
	}
	
	public int getLeft()
	{
		return left;
	}
	
	public int getRight()
	{
		return right;
	}
	
	public int getBottom()
	{
		return bottom;
	}
	
	public boolean isWithinBounds(int x, int y)
	{
		return x >= left && y >= top && x <= right && y <= bottom;
	}
	
	@Override
	public String toString()
	{
		return "ScreenSpaceCoordinate{" +
				"top=" + top +
				", left=" + left +
				", right=" + right +
				", bottom=" + bottom +
				'}';
	}
}
