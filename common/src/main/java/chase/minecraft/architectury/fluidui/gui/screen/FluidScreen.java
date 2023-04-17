package chase.minecraft.architectury.fluidui.gui.screen;

import chase.minecraft.architectury.fluidui.FluidTheme;
import chase.minecraft.architectury.fluidui.FluidUI;
import chase.minecraft.architectury.fluidui.util.ScreenSpaceCoordinate;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FastColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FluidScreen extends Screen
{
	@Nullable
	protected final Screen parent;
	private ScreenSpaceCoordinate coordinate;
	protected Minecraft minecraft;
	
	/**
	 * Creates a fluid screen
	 *
	 * @param title  - Screens title
	 * @param parent - the parent screen
	 */
	public FluidScreen(Component title, @Nullable Screen parent)
	{
		super(title);
		this.parent = parent;
		minecraft = Minecraft.getInstance();
		FluidUI.log.debug("Creating {} label of {}", this.getClass().getSimpleName(), title.getString());
	}
	
	/**
	 * Creates a fluid screen with the parent being null.
	 *
	 * @param title - Screens title
	 */
	public FluidScreen(Component title)
	{
		this(title, null);
	}
	
	
	@Override
	protected void init()
	{
		super.init();
	}
	
	@Override
	public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		coordinate = new ScreenSpaceCoordinate(this);
		renderBackground(poseStack);
		
		super.render(poseStack, mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void renderBackground(@NotNull PoseStack poseStack)
	{
		int backgroundColor = FluidTheme.getBackgroundColor();
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
	
	public ScreenSpaceCoordinate getCoordinate()
	{
		return coordinate;
	}
	
}
