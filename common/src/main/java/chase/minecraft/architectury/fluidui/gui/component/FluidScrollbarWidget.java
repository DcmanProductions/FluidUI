package chase.minecraft.architectury.fluidui.gui.component;

import chase.minecraft.architectury.fluidui.FluidTheme;
import chase.minecraft.architectury.fluidui.FluidUI;
import chase.minecraft.architectury.fluidui.util.ScreenSpaceCoordinate;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class FluidScrollbarWidget extends AbstractWidget
{
	protected int padding;
	protected int maxScroll;
	protected boolean hoverHandle;
	protected boolean hoverTrack;
	protected int scrollAmount = 20;
	protected int scrollPosition;
	protected final FluidTheme theme;
	protected ScreenSpaceCoordinate handleCoords;
	protected ScreenSpaceCoordinate trackCoords;
	protected boolean canScrollUp;
	protected boolean canScrollDown;
	protected AbstractWidget parent;
	protected boolean show = true;
	protected boolean drag = false;
	
	public FluidScrollbarWidget(FluidTheme theme, int x, int y, int width, int height, int padding)
	{
		super(x, y, width, height, Component.empty());
		this.padding = padding;
		this.theme = theme;
	}
	
	public FluidScrollbarWidget(FluidTheme theme, AbstractWidget parent, int width, int height, int padding)
	{
		this(theme, parent.getX() - width - padding, parent.getY() - padding, width, height, padding);
		this.parent = parent;
	}
	
	@Override
	public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		canScrollUp = scrollPosition > 0;
		canScrollDown = scrollPosition < maxScroll;
		if (show && (canScrollUp || canScrollDown))
			renderWidget(poseStack, mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void renderWidget(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		int trackColor = theme.getWidgetBackgroundColor();
		int handleColor = theme.getPrimaryColor();
		
		float r;
		float g;
		float b;
		float a = 1f;
		int trackTop = getY() + padding;
		int trackLeft = getX();
		int trackBottom = getY() + getHeight() - padding;
		int trackRight = getX() + getWidth();
		
		int handleTop = (trackTop + (padding / 2));
		int handleLeft = trackLeft + (padding / 2);
		int handleBottom = (int) (trackBottom - (getHeight() / 1.5));
		int handleRight = trackRight - (padding / 2);
		int handleHeight = handleTop - handleBottom;
		
		float scrollPercentage = getPercentScrolled();
		
		handleTop = (int) Mth.lerp(scrollPercentage, (trackTop - handleHeight) + ((float) padding / 2), trackBottom - ((float) padding / 2));
		handleBottom = handleTop + handleHeight;
		
		handleCoords = new ScreenSpaceCoordinate(handleBottom, handleLeft, handleRight, handleTop);
		trackCoords = new ScreenSpaceCoordinate(trackTop, trackLeft, trackRight, trackBottom);
		hoverHandle = handleCoords.isWithinBounds(mouseX, mouseY);
		hoverTrack = trackCoords.isWithinBounds(mouseX, mouseY);
		
		RenderSystem.enableBlend();
		RenderSystem.enableDepthTest();
		
		r = FastColor.ARGB32.red(trackColor) / 255f;
		g = FastColor.ARGB32.green(trackColor) / 255f;
		b = FastColor.ARGB32.blue(trackColor) / 255f;
		RenderSystem.setShaderColor(r, g, b, a);
		
		fill(poseStack, trackLeft, trackTop, trackRight, trackBottom, 0xFF_FF_FF_FF);
		
		r = FastColor.ARGB32.red(handleColor) / 255f;
		g = FastColor.ARGB32.green(handleColor) / 255f;
		b = FastColor.ARGB32.blue(handleColor) / 255f;
		RenderSystem.setShaderColor(r, g, b, a);
		
		fill(poseStack, handleLeft, handleTop, handleRight, handleBottom, hoverHandle ? 0xEE_EE_EE_FF : 0xFF_FF_FF_FF);
		
		RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
		RenderSystem.disableDepthTest();
		RenderSystem.disableBlend();
		
		
		if (drag)
		{
			setPercentScrolled(calculateMouseScreenPercentage(mouseY));
		}

		FluidUI.log.info("Percentage: {}", calculateMouseScreenPercentage(mouseY));
	}
	
	@Override
	protected void updateWidgetNarration(@NotNull NarrationElementOutput narrationElementOutput)
	{
	
	}
	
	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton)
	{
		if (hoverTrack)
		{
			if (hoverHandle)
			{
				drag = true;
				return true;
			}
			setPercentScrolled(calculateMouseScreenPercentage((float) mouseY));
			return true;
		}
		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int mouseButton)
	{
		drag = false;
		setPercentScrolled(calculateMouseScreenPercentage((float) mouseY));
		return super.mouseReleased(mouseX, mouseY, mouseButton);
	}
	
	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double direction)
	{
		if (parent != null && parent.isHovered())
		{
			if (direction > 0 && canScrollUp)
			{
				// Scroll Up
				scrollPosition -= scrollAmount;
			} else if (direction < 0 && canScrollDown)
			{
				// Scroll Down
				scrollPosition += scrollAmount;
			}
		}
		return super.mouseScrolled(mouseX, mouseY, direction);
	}
	
	public float getPercentScrolled()
	{
		float percentage = Math.abs(scrollPosition) / (maxScroll + 0f);
		return percentage > 1f ? 1f : percentage < 0 ? 0 : percentage;
	}
	
	public void setPercentScrolled(float percent)
	{
		scrollPosition = (int) (maxScroll * percent);
	}
	
	protected float calculateMouseScreenPercentage(float mouseY)
	{
		float percentage = mouseY / height;
		percentage = percentage > 1f ? 1f : percentage <= 0 ? 0f : percentage;
		return percentage;
	}
	
	public AbstractWidget getParent()
	{
		return parent;
	}
	
	public void setParent(AbstractWidget parent)
	{
		this.parent = parent;
	}
	
	public int getScrollAmount()
	{
		return scrollAmount;
	}
	
	public void setScrollAmount(int scrollAmount)
	{
		this.scrollAmount = scrollAmount;
	}
	
	
	public boolean canScroll()
	{
		return canScrollDown || canScrollUp;
	}
	
	
	public int getScrollPosition()
	{
		return scrollPosition;
	}
	
	public void setScrollPosition(int scrollPosition)
	{
		this.scrollPosition = scrollPosition;
	}
	
	public void setMaxScroll(int maxScroll)
	{
		this.maxScroll = maxScroll;
	}
	
	public int getMaxScroll()
	{
		return maxScroll;
	}
	
	public boolean isHoverHandle()
	{
		return hoverHandle;
	}
	
	public boolean isHoverTrack()
	{
		return hoverTrack;
	}
	
	public FluidTheme getTheme()
	{
		return theme;
	}
	
	public ScreenSpaceCoordinate getHandleCoords()
	{
		return handleCoords;
	}
	
	public ScreenSpaceCoordinate getTrackCoords()
	{
		return trackCoords;
	}
	
	public boolean isCanScrollUp()
	{
		return canScrollUp;
	}
	
	public boolean isCanScrollDown()
	{
		return canScrollDown;
	}
	
	public int getPadding()
	{
		return padding;
	}
	
	public void setPadding(int padding)
	{
		this.padding = padding;
	}
	
	public boolean isShow()
	{
		return show;
	}
	
	public void setShow(boolean show)
	{
		this.show = show;
	}
}
