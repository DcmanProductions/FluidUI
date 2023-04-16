package chase.minecraft.architectury.fluidui.gui.component.layout;

import chase.minecraft.architectury.fluidui.Alignment;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class HorizontalListWidget extends AbstractListWidget
{
	public HorizontalListWidget(Component label, int x, int y, int width, int height, int space, Alignment alignment, AbstractWidget... children)
	{
		super(label, x, y, width, height, space, alignment, children);
	}
	
	public HorizontalListWidget(Component label, int x, int y, int width, int height, int space, AbstractWidget... children)
	{
		this(label, x, y, width, height, space, Alignment.LEFT, children);
	}
	
	@Override
	public void renderWidget(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		int contentWidth = 0;
		int currentX = getSpace();
		switch (alignment)
		{
			case CENTER ->
			{
				for (int i = 0; i < children().length; i++)
				{
					AbstractWidget widget = children()[i];
					contentWidth += widget.getWidth() + getSpace();
				}
				currentX = (width / 2) - (contentWidth / 2);
			}
			case RIGHT ->
			{
				for (int i = 0; i < children().length; i++)
				{
					AbstractWidget widget = children()[i];
					contentWidth += widget.getWidth() + getSpace();
				}
				currentX = width - contentWidth;
			}
		}
		for (int i = 0; i < children().length; i++)
		{
			AbstractWidget widget = children()[i];
			widget.setPosition(currentX, getY());
			widget.render(poseStack, mouseX, mouseY, partialTicks);
			currentX += widget.getWidth() + getSpace();
		}
	}
	
}
