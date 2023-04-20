package chase.minecraft.architectury.fluidui.gui.component;

import chase.minecraft.architectury.fluidui.FluidTheme;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class FluidLabelWidget extends AbstractWidget
{
	protected FluidTheme theme;
	protected Component text;
	
	public FluidLabelWidget(Component text, FluidTheme theme, int x, int y, int width, int height)
	{
		super(x, y, width, height, text);
		this.theme = theme;
		this.text = text;
	}
	
	public FluidLabelWidget(String text, FluidTheme theme, int x, int y, int width, int height)
	{
		this(Component.literal(text), theme, x, y, width, height);
	}
	
	public FluidLabelWidget(Component text, FluidTheme theme, int width, int height)
	{
		this(text, theme, 0, 0, width, height);
	}
	
	public FluidLabelWidget(String text, FluidTheme theme, int width, int height)
	{
		this(Component.literal(text), theme, width, height);
	}
	
	@Override
	public boolean mouseClicked(double d, double e, int i)
	{
		return false;
	}
	
	@Override
	public void renderWidget(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		drawString(poseStack, Minecraft.getInstance().font, text, getX(), getY(), theme.getWidgetForegroundColor());
	}
	
	@Override
	protected void updateWidgetNarration(@NotNull NarrationElementOutput narrationElementOutput)
	{
	
	}
}
