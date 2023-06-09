package chase.minecraft.architectury.fluidui.gui.component.layout;

import chase.minecraft.architectury.fluidui.FluidTheme;
import chase.minecraft.architectury.fluidui.enums.Alignment;
import chase.minecraft.architectury.fluidui.util.ScreenSpaceCoordinate;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractListWidget extends AbstractWidget
{
	
	protected ScreenSpaceCoordinate coordinate;
	private List<AbstractWidget> children;
	private int space = 5;
	protected Alignment alignment;
	protected FluidTheme theme;
	
	public AbstractListWidget(FluidTheme theme, Component label, int x, int y, int width, int height, int space, Alignment alignment, AbstractWidget... children)
	{
		super(x, y + Minecraft.getInstance().font.lineHeight + 10, width, height, label);
		this.children = List.of(children);
		this.space = space;
		this.alignment = alignment;
		coordinate = new ScreenSpaceCoordinate(this);
		this.theme = theme;
	}
	
	public AbstractListWidget(FluidTheme theme, Component label, int x, int y, int width, int height, int space, AbstractWidget... children)
	{
		this(theme, label, x, y, width, height, space, Alignment.DEFAULT, children);
	}
	
	public AbstractListWidget(FluidTheme theme, Component label, int width, int height, int space, Alignment alignment, AbstractWidget... children)
	{
		this(theme, label, 0, 0, width, height, space, alignment, children);
	}
	
	public AbstractListWidget(FluidTheme theme, Component label, int width, int height, int space, AbstractWidget... children)
	{
		this(theme, label, 0, 0, width, height, space, Alignment.DEFAULT, children);
	}
	
	
	@Override
	public abstract void renderWidget(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks);
	
	@Override
	public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		coordinate = new ScreenSpaceCoordinate(this);
		if (visible)
		{
			isHovered = coordinate.isWithinBounds(mouseX, mouseY);
			renderWidget(poseStack, mouseX, mouseY, partialTicks);
			Font font = Minecraft.getInstance().font;
			drawCenteredString(poseStack, font, getMessage(), getWidth() / 2, getY() - font.lineHeight - 5, theme.getWidgetForegroundColor());
		}
		super.render(poseStack, mouseX, mouseY, partialTicks);
	}
	
	
	public Alignment getAlignment()
	{
		return alignment;
	}
	
	public void setAlignment(Alignment alignment)
	{
		this.alignment = alignment;
	}
	
	public int getSpace()
	{
		return space;
	}
	
	public void setSpace(int space)
	{
		this.space = space;
	}
	
	
	public void addWidget(AbstractWidget widget)
	{
		children.add(widget);
	}
	
	public void removeWidget(AbstractWidget widget)
	{
		children.remove(widget);
	}
	
	public void clear()
	{
		children.clear();
	}
	
	
	public AbstractWidget[] children()
	{
		return children.toArray(AbstractWidget[]::new);
	}
	
	public ScreenSpaceCoordinate getCoordinate()
	{
		return coordinate;
	}
	
	@Override
	protected void updateWidgetNarration(@NotNull NarrationElementOutput narrationElementOutput)
	{
	}
	
	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton)
	{
		for (AbstractWidget child : this.children)
		{
			child.mouseClicked(mouseX, mouseY, mouseButton);
		}
		return false;
	}
	
	protected int calculateContentSize(boolean height)
	{
		int size = 0;
		for (int i = 0; i < children().length; i++)
		{
			AbstractWidget widget = children()[i];
			size += (height ? widget.getHeight() : widget.getWidth()) + getSpace();
		}
		return size;
	}
	
}
