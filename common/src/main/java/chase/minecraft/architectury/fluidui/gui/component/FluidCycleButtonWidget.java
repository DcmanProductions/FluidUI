package chase.minecraft.architectury.fluidui.gui.component;

import chase.minecraft.architectury.fluidui.FluidTheme;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;

public class FluidCycleButtonWidget<T> extends FluidButtonWidget
{
	protected final LinkedHashMap<T, Component> values;
	protected final T initialValue;
	protected T selectedValue;
	protected final Consumer<T> onValueChange;
	protected int currentIndex = 0;
	
	/**
	 * Creates a Cycle Button
	 *
	 * @param theme         The theme of the mod
	 * @param label         the label message of the widget
	 * @param x             the x position
	 * @param y             the y position
	 * @param width         the width of the widget
	 * @param height        the height of the widget
	 * @param initialValue  the initial value of the cycle button
	 * @param values        a list of Values and their display names
	 * @param onValueChange runs everytime the value is changed
	 */
	public FluidCycleButtonWidget(FluidTheme theme, Component label, int x, int y, int width, int height, T initialValue, LinkedHashMap<T, Component> values, Consumer<T> onValueChange)
	{
		super(theme, label, x, y, width, height, button ->
		{
			FluidCycleButtonWidget<T> cycle = (FluidCycleButtonWidget) button;
			cycle.next();
		}, button ->
		{
			FluidCycleButtonWidget<T> cycle = (FluidCycleButtonWidget) button;
			cycle.previous();
		});
		this.values = values;
		this.selectedValue = this.initialValue = initialValue;
		this.onValueChange = onValueChange;
		currentIndex = getIndexOfKey(selectedValue);
	}
	
	/**
	 * Creates a Cycle Button
	 *
	 * @param theme         The theme of the mod
	 * @param label         the label message of the widget
	 * @param x             the x position
	 * @param y             the y position
	 * @param width         the width of the widget
	 * @param height        the height of the widget
	 * @param initialValue  the initial value of the cycle button
	 * @param values        a list of Values and their display names are the value cast to a string
	 * @param onValueChange runs everytime the value is changed
	 */
	public FluidCycleButtonWidget(FluidTheme theme, Component label, int x, int y, int width, int height, T initialValue, T[] values, Consumer<T> onValueChange)
	{
		super(theme, label, x, y, width, height, button ->
		{
			FluidCycleButtonWidget cycle = (FluidCycleButtonWidget) button;
			cycle.next();
		}, button ->
		{
			FluidCycleButtonWidget cycle = (FluidCycleButtonWidget) button;
			cycle.previous();
		});
		
		this.values = new LinkedHashMap<>();
		for (T item : values)
		{
			this.values.put(item, Component.literal(item.toString()));
		}
		
		this.selectedValue = this.initialValue = initialValue;
		this.onValueChange = onValueChange;
		currentIndex = getIndexOfKey(selectedValue);
	}
	
	/**
	 * Creates a Cycle Button.<br>
	 * This is used when placed inside a list panel.
	 *
	 * @param theme         The theme of the mod
	 * @param label         the label message of the widget
	 * @param width         the width of the widget
	 * @param height        the height of the widget
	 * @param initialValue  the initial value of the cycle button
	 * @param values        a list of Values and their display names
	 * @param onValueChange runs everytime the value is changed
	 */
	public FluidCycleButtonWidget(FluidTheme theme, Component label, int width, int height, T initialValue, LinkedHashMap<T, Component> values, Consumer<T> onValueChange)
	{
		this(theme, label, 0, 0, width, height, initialValue, values, onValueChange);
	}
	
	
	/**
	 * Creates a Cycle Button.<br>
	 * This is used when placed inside a list panel.
	 *
	 * @param theme         The theme of the mod
	 * @param label         the label message of the widget
	 * @param width         the width of the widget
	 * @param height        the height of the widget
	 * @param initialValue  the initial value of the cycle button
	 * @param values        a list of Values and their display names are the value cast to a string
	 * @param onValueChange runs everytime the value is changed
	 */
	public FluidCycleButtonWidget(FluidTheme theme, Component label, int width, int height, T initialValue, T[] values, Consumer<T> onValueChange)
	{
		this(theme, label, 0, 0, width, height, initialValue, values, onValueChange);
	}
	
	@Override
	public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		super.render(poseStack, mouseX, mouseY, partialTicks);
		if (visible)
		{
			this.setMessage(values.get(selectedValue));
		}
	}
	
	
	/**
	 * Gets the next element in the cycle
	 */
	public void next()
	{
		currentIndex++;
		if (currentIndex >= values.size())
			currentIndex = 0;
		T next = getElementByIndex(currentIndex);
		selectedValue = next;
		onValueChange.accept(next);
	}
	
	/**
	 * Gets the previous element in the cycle
	 */
	public void previous()
	{
		currentIndex--;
		if (currentIndex < 0)
			currentIndex = values.size() - 1;
		T previous = getElementByIndex(currentIndex);
		selectedValue = previous;
		onValueChange.accept(previous);
	}
	
	/**
	 * Resets the value to the initial value
	 */
	public void reset()
	{
		selectedValue = initialValue;
		currentIndex = getIndexOfKey(selectedValue);
	}
	
	/**
	 * Gets the element based on the index provided
	 *
	 * @param index the index of the element
	 * @return the element or null if none was found
	 */
	public @Nullable T getElementByIndex(int index)
	{
		if (index <= values.size())
			return values.keySet().stream().toList().get(index);
		return null;
	}
	
	/**
	 * Gets the index based on the element provided
	 *
	 * @param key the element
	 * @return the index of the element or -1 if none was found
	 */
	public int getIndexOfKey(T key)
	{
		if (values.containsKey(key))
		{
			List<T> keys = values.keySet().stream().toList();
			for (int i = 0; i < values.size(); i++)
			{
				if (keys.get(i).equals(key))
					return i;
			}
		}
		return -1;
	}
	
	/**
	 * gets the index of the specified display name
	 *
	 * @param value the display name
	 * @return the index of the display name or -1 if none was found.
	 */
	public int getIndexOfValue(Component value)
	{
		if (values.containsValue(value))
		{
			List<Component> values = this.values.values().stream().toList();
			for (int i = 0; i < this.values.size(); i++)
			{
				if (values.get(i).equals(value))
					return i;
			}
		}
		return -1;
	}
	
}
