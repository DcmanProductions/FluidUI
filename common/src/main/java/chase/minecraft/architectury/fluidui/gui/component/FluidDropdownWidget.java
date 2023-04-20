package chase.minecraft.architectury.fluidui.gui.component;

import chase.minecraft.architectury.fluidui.FluidTheme;
import chase.minecraft.architectury.fluidui.util.ScreenSpaceCoordinate;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FastColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.function.Consumer;

public class FluidDropdownWidget<T> extends FluidButtonWidget
{
	protected final LinkedHashMap<T, Component> values;
	protected final T initialValue;
	protected T selectedValue;
	protected final Consumer<T> onValueChange;
	@Nullable
	protected T hoveredValue;
	protected final int dropdownItemHeight;
	protected final DropdownMenu menu;
	
	public FluidDropdownWidget(FluidTheme theme, Component label, int x, int y, int width, int dropdownItemHeight, int height, T initialValue, LinkedHashMap<T, Component> values, Consumer<T> onValueChange)
	{
		super(theme, label, x, y, width, height, null, null);
		this.values = values;
		this.selectedValue = this.initialValue = initialValue;
		this.onValueChange = onValueChange;
		this.hoveredValue = null;
		this.dropdownItemHeight = dropdownItemHeight;
		
		
		Font font = Minecraft.getInstance().font;
		FluidButtonWidget[] buttons = new FluidButtonWidget[values.size()];
		for (int i = 0; i < buttons.length; i++)
		{
			Component name = values.values().stream().toList().get(i);
			T value = values.keySet().stream().toList().get(i);
			buttons[i] = new FluidButtonWidget(theme, name, width, 20, button ->
			{
				selectedValue = value;
			});
		}
		menu = new DropdownMenu(theme, getX(), getY() + getHeight() + (font.lineHeight / 2), getWidth(), 100, 10, buttons);
	}
	
	public FluidDropdownWidget(FluidTheme theme, Component label, int x, int y, int width, int height, int dropdownItemHeight, T initialValue, T[] values, Consumer<T> onValueChange)
	{
		super(theme, label, x, y, width, height, null, null);
		this.values = new LinkedHashMap<>();
		for (T item : values)
		{
			this.values.put(item, Component.literal(item.toString()));
		}
		
		this.selectedValue = this.initialValue = initialValue;
		this.onValueChange = onValueChange;
		this.hoveredValue = null;
		this.dropdownItemHeight = dropdownItemHeight;
		
		
		Font font = Minecraft.getInstance().font;
		FluidButtonWidget[] buttons = new FluidButtonWidget[values.length];
		for (int i = 0; i < buttons.length; i++)
		{
			T value = values[i];
			Component name = Component.literal(value.toString());
			buttons[i] = new FluidButtonWidget(theme, name, width, 20, button ->
			{
				selectedValue = value;
			});
		}
		menu = new DropdownMenu(theme, getX(), getY() + getHeight() + (font.lineHeight / 2), getWidth(), 100, 10, buttons);
		
	}
	
	public FluidDropdownWidget(FluidTheme theme, Component label, int width, int dropdownItemHeight, int height, T initialValue, LinkedHashMap<T, Component> values, Consumer<T> onValueChange)
	{
		this(theme, label, 0, 0, width, height, dropdownItemHeight, initialValue, values, onValueChange);
	}
	
	public FluidDropdownWidget(FluidTheme theme, Component label, int width, int height, int dropdownItemHeight, T initialValue, T[] values, Consumer<T> onValueChange)
	{
		this(theme, label, 0, 0, width, height, dropdownItemHeight, initialValue, values, onValueChange);
	}
	
	@Override
	public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		super.render(poseStack, mouseX, mouseY, partialTicks);
		if (visible)
		{
			isHovered = coordinate.isWithinBounds(mouseX, mouseY) || hoveredValue != null;
			this.setMessage(values.get(selectedValue));
			if (isFocused())
			{
				renderDropdownItems(poseStack, mouseX, mouseY);
//				menu.render(poseStack, mouseX, mouseY, partialTicks);
			}
		}
	}
	
	
	protected void renderDropdownItems(PoseStack poseStack, int mouseX, int mouseY)
	{
		Font font = Minecraft.getInstance().font;
		int y = getY() + getHeight() + (font.lineHeight / 2);
		int maxHeight = y + (dropdownItemHeight * (values.size() - 1)) + (font.lineHeight / 2);
		int hoverColor = theme.getWidgetHoverBackgroundColor();
		float r = FastColor.ARGB32.red(hoverColor) / 255f;
		float g = FastColor.ARGB32.green(hoverColor) / 255f;
		float b = FastColor.ARGB32.blue(hoverColor) / 255f;
//		RenderSystem.enableBlend();
//		RenderSystem.enableDepthTest();
		RenderSystem.disableDepthTest();
		RenderSystem.disableBlend();
		RenderSystem.setShaderColor(r, g, b, 1f);
		fill(poseStack, getX(), getY() + height, getX() + width, maxHeight, 0xFF_FF_FF_FF);
		RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
		
		boolean dropdownItemHover = false;
		for (int i = 0; i < values.size(); i++)
		{
			T item = values.keySet().stream().toList().get(i);
			if (item.equals(selectedValue))
				continue;
			
			ScreenSpaceCoordinate coordinate = new ScreenSpaceCoordinate(y, getX(), width + getX(), y + dropdownItemHeight);
			if (coordinate.isWithinBounds(mouseX, mouseY))
			{
				int color = theme.getDropdownHoverBackgroundColor();
				r = FastColor.ARGB32.red(color) / 255f;
				g = FastColor.ARGB32.green(color) / 255f;
				b = FastColor.ARGB32.blue(color) / 255f;
				RenderSystem.setShaderColor(r, g, b, 1f);
				fill(poseStack, coordinate.getLeft(), coordinate.getTop(), coordinate.getRight(), coordinate.getBottom(), 0xFF_FF_FF_FF);
				RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
				dropdownItemHover = true;
				hoveredValue = item;
			}
			if (!dropdownItemHover)
				hoveredValue = null;
			int textColor = dropdownItemHover ? theme.getDropdownHoverForegroundColor() : theme.getWidgetForegroundColor();
			drawCenteredString(poseStack, font, values.get(item), getX() + (width / 2), y + (dropdownItemHeight / 2) - (font.lineHeight / 2), textColor);
			y += dropdownItemHeight + 1;
		}
		RenderSystem.disableDepthTest();
		RenderSystem.disableBlend();
		
	}
	
	@Override
	public boolean mouseClicked(double x, double y, int mouseButton)
	{
		if (isHovered)
		{
			if (mouseButton == 0)
			{
				if (hoveredValue != null)
				{
					selectedValue = hoveredValue;
					hoveredValue = null;
					this.setMessage(values.get(selectedValue));
					onValueChange.accept(selectedValue);
					setFocused(false);
				} else
				{
					setFocused(!isFocused());
				}
			} else if (mouseButton == 1)
			{
				setFocused(false);
				reset();
			} else
			{
				setFocused(false);
			}
			playDownSound(Minecraft.getInstance().getSoundManager());
		} else
		{
			setFocused(false);
		}
		return false;
	}
	
	/**
	 * Resets the dropdowns selected value to the initial value
	 */
	public void reset()
	{
		selectedValue = initialValue;
	}
	
	
}
