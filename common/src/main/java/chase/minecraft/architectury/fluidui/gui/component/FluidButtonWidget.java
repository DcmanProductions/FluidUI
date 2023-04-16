package chase.minecraft.architectury.fluidui.gui.component;

import chase.minecraft.architectury.fluidui.FluidTheme;
import chase.minecraft.architectury.fluidui.util.ScreenSpaceCoordinate;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FastColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class FluidButtonWidget extends AbstractButton
{
	@Nullable
	private final Consumer<FluidButtonWidget> onClick, onContextMenu;
	protected ScreenSpaceCoordinate coordinate;
	
	/**
	 * Creates a stylish button widget.
	 *
	 * @param label         The text on the button
	 * @param x             the x position
	 * @param y             the y position
	 * @param width         the width of the button
	 * @param height        the height of the button
	 * @param onClick       this runs when the button is left-clicked
	 * @param onContextMenu this runs when the button is right-clicked
	 */
	public FluidButtonWidget(Component label, int x, int y, int width, int height, @Nullable Consumer<FluidButtonWidget> onClick, @Nullable Consumer<FluidButtonWidget> onContextMenu)
	{
		super(x, y, width, height, label);
		
		this.onClick = onClick;
		this.onContextMenu = onContextMenu;
		coordinate = new ScreenSpaceCoordinate(this);
	}
	
	/**
	 * Creates a stylish button widget without right-click functionality
	 *
	 * @param label   The text on the button
	 * @param x       the x position
	 * @param y       the y position
	 * @param width   the width of the button
	 * @param height  the height of the button
	 * @param onClick this runs when the button is left-clicked
	 */
	public FluidButtonWidget(Component label, int x, int y, int width, int height, Consumer<FluidButtonWidget> onClick)
	{
		this(label, x, y, width, height, onClick, null);
	}
	
	@Override
	public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		if (visible)
		{
			renderWidget(poseStack, mouseX, mouseY, partialTicks);
			isHovered = coordinate.isWithinBounds(mouseX, mouseY);
		}
	}
	
	@Override
	public void onPress()
	{
	
	}
	
	@Override
	public void renderWidget(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		coordinate = new ScreenSpaceCoordinate(this);
		int backgroundColor = FluidTheme.getWidgetBackgroundColor();
		if (isHoveredOrFocused())
		{
			backgroundColor = FluidTheme.getWidgetHoverBackgroundColor();
		}
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
	}
	
	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton)
	{
		if (isValidMouseClick(mouseButton))
		{
			if (mouseButton == 0)
			{
				if (onClick != null)
				{
					onClick.accept(this);
				}
				playDownSound(Minecraft.getInstance().getSoundManager());
			} else if (mouseButton == 1 && onContextMenu != null)
			{
				onContextMenu.accept(this);
				playDownSound(Minecraft.getInstance().getSoundManager());
			}
		}
		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput)
	{
	
	}
	
	private boolean isValidMouseClick(int mouseButton)
	{
		return visible && active && isHovered && (mouseButton == 0 || mouseButton == 1);
	}
}
