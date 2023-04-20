package chase.minecraft.architectury.fluidui.gui.component.layout;

import chase.minecraft.architectury.fluidui.FluidTheme;
import chase.minecraft.architectury.fluidui.enums.Alignment;
import chase.minecraft.architectury.fluidui.gui.component.FluidScrollbarWidget;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ScrollingListWidget extends VerticalListWidget
{
	@Nullable
	protected FluidScrollbarWidget scrollbar;
	
	public ScrollingListWidget(FluidTheme theme, Component label, int x, int y, int width, int height, int space, Alignment alignment, @Nullable FluidScrollbarWidget scrollbar, AbstractWidget... children)
	{
		super(theme, label, x, y, width, height, space, alignment, children);
		this.scrollbar = scrollbar;
		if (scrollbar != null)
		{
			scrollbar.setParent(this);
		}
	}
	
	public ScrollingListWidget(FluidTheme theme, Component label, int x, int y, int width, int height, int space, @Nullable FluidScrollbarWidget scrollbar, AbstractWidget... children)
	{
		this(theme, label, x, y, width, height, space, Alignment.DEFAULT, scrollbar, children);
	}
	
	public ScrollingListWidget(FluidTheme theme, Component label, int width, int height, int space, Alignment alignment, @Nullable FluidScrollbarWidget scrollbar, AbstractWidget... children)
	{
		this(theme, label, 0, 0, width, height, space, alignment, scrollbar, children);
	}
	
	public ScrollingListWidget(FluidTheme theme, Component label, int width, int height, int space, @Nullable FluidScrollbarWidget scrollbar, AbstractWidget... children)
	{
		this(theme, label, 0, 0, width, height, space, scrollbar, children);
	}
	
	
	public ScrollingListWidget(FluidTheme theme, Component label, int x, int y, int width, int height, int space, AbstractWidget... children)
	{
		this(theme, label, x, y, width, height, space, Alignment.DEFAULT, null, children);
	}
	
	public ScrollingListWidget(FluidTheme theme, Component label, int width, int height, int space, Alignment alignment, AbstractWidget... children)
	{
		this(theme, label, 0, 0, width, height, space, alignment, null, children);
	}
	
	public ScrollingListWidget(FluidTheme theme, Component label, int width, int height, int space, AbstractWidget... children)
	{
		this(theme, label, 0, 0, width, height, space, null, children);
	}
	
	@Override
	public void renderWidget(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		int currentY = getY() + getSpace();
		int contentHeight = calculateContentSize();
		
		switch (alignment)
		{
			case CENTER ->
			{
				currentY = (height / 2) - (contentHeight / 2);
			}
			case BOTTOM ->
			{
				currentY = height - contentHeight;
			}
		}
		if (scrollbar != null)
			scrollbar.setMaxScroll((contentHeight) - getHeight());
		for (int i = 0; i < children().length; i++)
		{
			AbstractWidget widget = children()[i];
			widget.setPosition(getX(), currentY - (this.scrollbar != null ? scrollbar.getScrollPosition() : 0));
			widget.visible = getCoordinate().isWithinBounds(widget.getX(), widget.getY());
			if (visible)
				widget.render(poseStack, mouseX, mouseY, partialTicks);
			
			currentY += widget.getHeight() + getSpace();
		}
		if (scrollbar != null)
			scrollbar.render(poseStack, mouseX, mouseY, partialTicks);
	}
	
	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton)
	{
		if (scrollbar != null)
			return scrollbar.mouseClicked(mouseX, mouseY, mouseButton);
		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int mouseButton)
	{
		if (scrollbar != null)
			return scrollbar.mouseReleased(mouseX, mouseY, mouseButton);
		return super.mouseReleased(mouseX, mouseY, mouseButton);
	}
	
	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double direction)
	{
		if (scrollbar != null)
			return scrollbar.mouseScrolled(mouseX, mouseY, direction);
		return super.mouseScrolled(mouseX, mouseY, direction);
	}
	
	public @Nullable FluidScrollbarWidget getScrollbar()
	{
		return scrollbar;
	}
	
	public void setScrollbar(@Nullable FluidScrollbarWidget scrollbar)
	{
		this.scrollbar = scrollbar;
	}
}
