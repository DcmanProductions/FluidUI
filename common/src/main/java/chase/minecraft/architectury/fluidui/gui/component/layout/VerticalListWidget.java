package chase.minecraft.architectury.fluidui.gui.component.layout;

import chase.minecraft.architectury.fluidui.FluidTheme;
import chase.minecraft.architectury.fluidui.enums.Alignment;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class VerticalListWidget extends AbstractListWidget
{
	
	public VerticalListWidget(FluidTheme theme, Component label, int x, int y, int width, int height, int space, Alignment alignment, AbstractWidget... children)
	{
		super(theme, label, x, y, width, height, space, alignment, children);
	}
	
	public VerticalListWidget(FluidTheme theme, Component label, int x, int y, int width, int height, int space, AbstractWidget... children)
	{
		super(theme, label, x, y, width, height, space, children);
	}
	
	public VerticalListWidget(FluidTheme theme, Component label, int width, int height, int space, Alignment alignment, AbstractWidget... children)
	{
		super(theme, label, width, height, space, alignment, children);
	}
	
	public VerticalListWidget(FluidTheme theme, Component label, int width, int height, int space, AbstractWidget... children)
	{
		super(theme, label, width, height, space, children);
	}
	
	@Override
	public void renderWidget(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		int contentHeight = calculateContentSize();
		int currentY = getSpace();
		
		for (int i = 0; i < children().length; i++)
		{
			AbstractWidget widget = children()[i];
			contentHeight += widget.getHeight() + getSpace();
		}
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
		for (int i = 0; i < children().length; i++)
		{
			AbstractWidget widget = children()[i];
			widget.setPosition(getX(), currentY);
			widget.render(poseStack, mouseX, mouseY, partialTicks);
			currentY += widget.getHeight() + getSpace();
		}
	}
	
	protected int calculateContentSize()
	{
		return super.calculateContentSize(true);
	}
}
