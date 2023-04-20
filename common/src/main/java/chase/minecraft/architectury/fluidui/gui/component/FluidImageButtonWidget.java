package chase.minecraft.architectury.fluidui.gui.component;

import chase.minecraft.architectury.fluidui.FluidTheme;
import chase.minecraft.architectury.fluidui.util.ScreenSpaceCoordinate;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class FluidImageButtonWidget extends FluidButtonWidget
{
	protected ResourceLocation textureLocation;
	protected int textureWidth, textureHeight;
	
	private FluidImageButtonWidget(FluidTheme theme, ResourceLocation textureLocation, int x, int y, int width, int height, int textureWidth, int textureHeight, @Nullable Consumer<FluidButtonWidget> onClick, @Nullable Consumer<FluidButtonWidget> onContextMenu)
	{
		super(theme, Component.empty(), x, y, width, height, onClick, onContextMenu);
		this.textureLocation = textureLocation;
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
	}
	
	private FluidImageButtonWidget(FluidTheme theme, ResourceLocation textureLocation, int x, int y, int width, int height, int textureWidth, int textureHeight, Consumer<FluidButtonWidget> onClick)
	{
		this(theme, textureLocation, x, y, width, height, textureWidth, textureHeight, onClick, null);
	}
	
	private FluidImageButtonWidget(FluidTheme theme, ResourceLocation textureLocation, int width, int height, int textureWidth, int textureHeight, @Nullable Consumer<FluidButtonWidget> onClick, @Nullable Consumer<FluidButtonWidget> onContextMenu)
	{
		this(theme, textureLocation, 0, 0, width, height, textureWidth, textureHeight, onClick, onContextMenu);
	}
	
	private FluidImageButtonWidget(FluidTheme theme, ResourceLocation textureLocation, int width, int height, int textureWidth, int textureHeight, Consumer<FluidButtonWidget> onClick)
	{
		this(theme, textureLocation, 0, 0, textureWidth, textureHeight, width, height, onClick);
	}
	
	@Override
	public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		
		super.render(poseStack, mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void renderWidget(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		
		coordinate = new ScreenSpaceCoordinate(this);
		int backgroundColor = theme.getWidgetBackgroundColor();
		if (isHoveredOrFocused())
		{
			backgroundColor = theme.getWidgetHoverBackgroundColor();
		}
		float r = FastColor.ARGB32.red(backgroundColor) / 255f;
		float g = FastColor.ARGB32.green(backgroundColor) / 255f;
		float b = FastColor.ARGB32.blue(backgroundColor) / 255f;
		float a = 1f;
		RenderSystem.enableBlend();
		RenderSystem.enableDepthTest();
		RenderSystem.setShaderColor(r, g, b, a);
		fill(poseStack, coordinate.getLeft(), coordinate.getTop(), coordinate.getRight(), coordinate.getBottom(), 0xFF_FF_FF_FF);
		
		int foregroundColor = theme.getWidgetForegroundColor();
		r = FastColor.ARGB32.red(foregroundColor) / 255f;
		g = FastColor.ARGB32.green(foregroundColor) / 255f;
		b = FastColor.ARGB32.blue(foregroundColor) / 255f;
		RenderSystem.setShaderColor(r, g, b, a);
		renderTexture(poseStack);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	
	@Override
	public void renderTexture(@NotNull PoseStack poseStack, @NotNull ResourceLocation textureLocation, int x, int y, int textureXStart, int textureYStart, int textureYDifference, int width, int height, int textureWidth, int textureHeight)
	{
		super.renderTexture(poseStack, textureLocation, x, y, textureXStart, textureYStart, textureYDifference, width, height, textureWidth, textureHeight);
	}
	
	public void renderTexture(@NotNull PoseStack poseStack)
	{
		renderTexture(poseStack, textureLocation, getX() , getY() , 0, 0, 0, getTextureWidth(), getTextureHeight(), getTextureWidth(), getTextureHeight());
	}
	
	public void setTexture(ResourceLocation textureLocation)
	{
		this.textureLocation = textureLocation;
	}
	
	public ResourceLocation getTexture()
	{
		return textureLocation;
	}
	
	public int getTextureWidth()
	{
		return textureWidth;
	}
	
	public int getTextureHeight()
	{
		return textureHeight;
	}
}
