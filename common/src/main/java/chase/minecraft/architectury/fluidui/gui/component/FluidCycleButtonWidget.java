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
	private final LinkedHashMap<T, Component> values;
	private final T initialValue;
	private T selectedValue;
	private final Consumer<T> onValueChange;
	private int currentIndex = 0;
	
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
	
	@Override
	public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		super.render(poseStack, mouseX, mouseY, partialTicks);
		if (visible)
		{
			this.setMessage(values.get(selectedValue));
		}
	}
	
	
	public void next()
	{
		currentIndex++;
		if (currentIndex >= values.size())
			currentIndex = 0;
		T next = getElementByIndex(currentIndex);
		selectedValue = next;
		onValueChange.accept(next);
	}
	
	public void previous()
	{
		currentIndex--;
		if (currentIndex < 0)
			currentIndex = values.size() - 1;
		T previous = getElementByIndex(currentIndex);
		selectedValue = previous;
		onValueChange.accept(previous);
	}
	
	public void reset()
	{
		selectedValue = initialValue;
		currentIndex = getIndexOfKey(selectedValue);
	}
	
	public @Nullable T getElementByIndex(int index)
	{
		if (index <= values.size())
			return values.keySet().stream().toList().get(index);
		return null;
	}
	
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
