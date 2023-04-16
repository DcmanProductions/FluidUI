package chase.minecraft.architectury.fluidui.gui.component.layout;

import chase.minecraft.architectury.fluidui.Alignment;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class VerticalListWidget extends AbstractListWidget
{
	
	public VerticalListWidget(Component label, int x, int y, int width, int height, int space, Alignment alignment, AbstractWidget... children)
	{
		super(label, x, y, width, height, space, alignment, children);
	}
	
	public VerticalListWidget(Component label, int x, int y, int width, int height, int space, AbstractWidget... children)
	{
		this(label, x, y, width, height, space, Alignment.DEFAULT, children);
	}
	
	@Override
	public void renderWidget(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		int contentHeight = 0;
		int currentY = getSpace();
		switch (alignment)
		{
			case CENTER ->
			{
				for (int i = 0; i < children().length; i++)
				{
					AbstractWidget widget = children()[i];
					contentHeight += widget.getHeight() + getSpace();
				}
				currentY = (height / 2) - (contentHeight / 2);
			}
			case BOTTOM ->
			{
				for (int i = 0; i < children().length; i++)
				{
					AbstractWidget widget = children()[i];
					contentHeight += widget.getHeight() + getSpace();
				}
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
	
}
