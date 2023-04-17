package chase.minecraft.architectury.fluidui;

import chase.minecraft.architectury.fluidui.enums.Alignment;
import chase.minecraft.architectury.fluidui.gui.component.FluidButtonWidget;
import chase.minecraft.architectury.fluidui.gui.component.FluidCycleButtonWidget;
import chase.minecraft.architectury.fluidui.gui.component.FluidDropdownWidget;
import chase.minecraft.architectury.fluidui.gui.component.FluidToggleButtonWidget;
import chase.minecraft.architectury.fluidui.gui.component.layout.HorizontalListWidget;
import chase.minecraft.architectury.fluidui.gui.component.layout.VerticalListWidget;
import chase.minecraft.architectury.fluidui.gui.screen.FluidScreen;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TestScreen extends FluidScreen
{
	/**
	 * Creates a fluid screen
	 *
	 * @param title  - Screens title
	 * @param parent - the parent screen
	 */
	public TestScreen(Component title, @Nullable Screen parent)
	{
		super(FluidUI.THEME, title, parent);
	}
	
	/**
	 * Creates a fluid screen with the parent being null.
	 *
	 * @param title - Screens title
	 */
	public TestScreen(Component title)
	{
		super(FluidUI.THEME, title);
	}
	
	/**
	 * This runs when the screen is created.
	 */
	@Override
	protected void init()
	{
		addRenderableWidget(
				new VerticalListWidget(Component.empty(), 0, 0, width, height, 20, Alignment.CENTER,
						new HorizontalListWidget(Component.empty(), 0, 5, width, 50, 10, Alignment.CENTER,
								new FluidButtonWidget(FluidUI.THEME, Component.literal("Button"), 0, 0, 100, 20, button ->
								{
								}),
								new FluidDropdownWidget<>(FluidUI.THEME, Component.empty(), 0, 0, 100, 20, 20, "Value 1", new String[]{"Value 1", "Value 2", "Value 3", "Value 4"}, value ->
								{
								}),
								new FluidCycleButtonWidget<>(FluidUI.THEME, Component.empty(), 0, 0, 100, 20, "Value 1", new String[]{"Value 1", "Value 2", "Value 3", "Value 4"}, value ->
								{
								}),
								new FluidToggleButtonWidget(FluidUI.THEME, Component.literal("ACTIVE"), Component.literal("INACTIVE"), 0, 0, 100, 20, false, true, value ->
								{
								})
						)
				)
		);
		super.init();
	}
	
	/**
	 * This runs every frame
	 */
	@Override
	public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		super.render(poseStack, mouseX, mouseY, partialTicks);
	}
}
