package chase.minecraft.architectury.fluidui.gui.component;

import chase.minecraft.architectury.fluidui.FluidTheme;
import chase.minecraft.architectury.fluidui.gui.component.layout.ScrollingListWidget;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FastColor;
import org.jetbrains.annotations.NotNull;

public class DropdownMenu extends ScrollingListWidget
{
	
	public DropdownMenu(FluidTheme theme, int x, int y, int width, int height, int space, AbstractWidget... children)
	{
		super(theme, Component.empty(), x, y, width, height, space, null, children);
	}
	
	@Override
	public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		super.render(poseStack, mouseX, mouseY, partialTicks);
		
		int backgroundColor = theme.getWidgetBackgroundColor();
		
		float r = FastColor.ARGB32.red(backgroundColor) / 255f;
		float g = FastColor.ARGB32.green(backgroundColor) / 255f;
		float b = FastColor.ARGB32.blue(backgroundColor) / 255f;
		float a = 1f;
		RenderSystem.enableBlend();
		RenderSystem.enableDepthTest();
		RenderSystem.setShaderColor(r, g, b, a);
		fill(poseStack, coordinate.getLeft(), coordinate.getTop(), coordinate.getRight(), coordinate.getBottom(), 0xFF_FF_FF_FF);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
	}
}
