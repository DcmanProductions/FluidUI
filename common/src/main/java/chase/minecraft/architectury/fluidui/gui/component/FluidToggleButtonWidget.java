package chase.minecraft.architectury.fluidui.gui.component;

import chase.minecraft.architectury.fluidui.FluidTheme;
import chase.minecraft.architectury.fluidui.util.ScreenSpaceCoordinate;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FastColor;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class FluidToggleButtonWidget extends FluidButtonWidget
{
	protected boolean value;
	protected final boolean initialValue;
	protected final Component activeLabel, inactiveLabel;
	
	public FluidToggleButtonWidget(FluidTheme theme, Component activeLabel, Component inactiveLabel, int x, int y, int width, int height, boolean initialValue, Consumer<Boolean> onValueChange)
	{
		this(theme, activeLabel, inactiveLabel, x, y, width, height, initialValue, false, onValueChange);
	}
	
	
	public FluidToggleButtonWidget(FluidTheme theme, Component activeLabel, Component inactiveLabel, int x, int y, int width, int height, boolean initialValue, boolean highlightIfActive, Consumer<Boolean> onValueChange)
	{
		super(theme, initialValue ? activeLabel : inactiveLabel, x, y, width, height, button ->
		{
			((FluidToggleButtonWidget) button).toggle();
		}, null);
		this.initialValue = this.value = initialValue;
		this.activeLabel = activeLabel;
		this.inactiveLabel = inactiveLabel;
	}
	
	public FluidToggleButtonWidget(FluidTheme theme, Component activeLabel, Component inactiveLabel, int width, int height, boolean initialValue, Consumer<Boolean> onValueChange)
	{
		this(theme, activeLabel, inactiveLabel, 0, 0, width, height, initialValue, false, onValueChange);
	}
	
	
	public FluidToggleButtonWidget(FluidTheme theme, Component activeLabel, Component inactiveLabel, int width, int height, boolean initialValue, boolean highlightIfActive, Consumer<Boolean> onValueChange)
	{
		this(theme, activeLabel, inactiveLabel, 0, 0, width, height, initialValue, highlightIfActive, onValueChange);
	}
	
	@Override
	public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		setMessage(value ? activeLabel : inactiveLabel);
		super.render(poseStack, mouseX, mouseY, partialTicks);
	}
	
	
	@Override
	public void renderWidget(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		if (value)
		{
			coordinate = new ScreenSpaceCoordinate(this);
			int backgroundColor = theme.getPrimaryColor();
			float r = FastColor.ARGB32.red(backgroundColor) / 255f;
			float g = FastColor.ARGB32.green(backgroundColor) / 255f;
			float b = FastColor.ARGB32.blue(backgroundColor) / 255f;
			float a = 1f;
			RenderSystem.enableBlend();
			RenderSystem.enableDepthTest();
			RenderSystem.setShaderColor(r, g, b, a);
			fill(poseStack, coordinate.getLeft(), coordinate.getTop(), coordinate.getRight(), coordinate.getBottom(), 0xFF_FF_FF_FF);
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
			int textColor = active ? 0xFF_FF_FF : 0xCC_CC_CC;
			renderString(poseStack, Minecraft.getInstance().font, textColor);
		} else
		{
			super.renderWidget(poseStack, mouseX, mouseY, partialTicks);
		}
	}
	
	public void toggle()
	{
		value = !value;
	}
	
	public void reset()
	{
		value = initialValue;
	}
	
	public boolean value()
	{
		return value;
	}
	
	public boolean initialValue()
	{
		return initialValue;
	}
	
	public boolean isInitialValue()
	{
		return value == initialValue;
	}
	
	
}
