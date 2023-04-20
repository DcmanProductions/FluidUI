package test;

import chase.minecraft.architectury.fluidui.FluidUI;
import chase.minecraft.architectury.fluidui.enums.Alignment;
import chase.minecraft.architectury.fluidui.gui.component.*;
import chase.minecraft.architectury.fluidui.gui.component.layout.HorizontalListWidget;
import chase.minecraft.architectury.fluidui.gui.component.layout.ScrollingListWidget;
import chase.minecraft.architectury.fluidui.gui.screen.FluidScreen;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
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
		int size = 25;
		HorizontalListWidget[] list = new HorizontalListWidget[size];
		for (int i = 0; i < size; i++)
		{
			list[i] = new HorizontalListWidget(theme, Component.empty(), width - 20, 20, 10,
					new FluidLabelWidget("Item " + i, theme, width, 10),
					new HorizontalListWidget(theme, Component.empty(), width - 20, 20, 10, Alignment.RIGHT,
							new FluidButtonWidget(theme, Component.literal("Button"), 50, 20, button ->
							{
							}),
							new FluidToggleButtonWidget(theme, Component.literal("Active"), Component.literal("Inactive"), 50, 20, true, button ->
							{
							}),
							new FluidDropdownWidget<String>(theme, Component.literal("Dropdown"), 50, 20, 20, "Value 1", new String[]{"Value 1", "Value 2", "Value 3", "Value 4", "Value 5"}, value ->
							{
							})
					)
			);
			
			
		}
		FluidScrollbarWidget scrollbar = new FluidScrollbarWidget(theme, width - 15, 0, 10, height, 7);
		addRenderableWidget(new ScrollingListWidget(theme, Component.empty(), 0, minecraft.font.lineHeight * 2, width, height - (minecraft.font.lineHeight * 2) - 60, 10, Alignment.TOP, scrollbar, list));
		addRenderableWidget(new HorizontalListWidget(theme, Component.empty(), 0, height - 50, width, 30, 10, Alignment.CENTER,
				new FluidButtonWidget(theme, CommonComponents.GUI_DONE, 100, 20, button ->
				{
					Minecraft.getInstance().setScreen(parent);
				}),
				new FluidButtonWidget(theme, CommonComponents.GUI_CANCEL, 100, 20, button ->
				{
					Minecraft.getInstance().setScreen(parent);
				})));
		super.init();
	}
	
	/**
	 * This runs every frame
	 */
	@Override
	public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		drawCenteredString(poseStack, minecraft.font, "Test Screen", width / 2, font.lineHeight, theme.getWidgetForegroundColor());
		super.render(poseStack, mouseX, mouseY, partialTicks);
	}
}
