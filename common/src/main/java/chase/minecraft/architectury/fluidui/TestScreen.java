package chase.minecraft.architectury.fluidui;

import chase.minecraft.architectury.fluidui.gui.component.FluidButtonWidget;
import chase.minecraft.architectury.fluidui.gui.component.FluidDropdownWidget;
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
	public TestScreen(Component title, @Nullable Screen parent)
	{
		super(title, parent);
	}
	
	public TestScreen(Component title)
	{
		super(title);
	}
	
	@Override
	protected void init()
	{
		addRenderableWidget(
				new VerticalListWidget(Component.empty(), 0, 0, width, height, 20, Alignment.CENTER,
						new HorizontalListWidget(Component.empty(), 0, 5, width, 50, 10, Alignment.LEFT,
								new FluidButtonWidget(Component.literal("Button"), 0, 0, 100, 20, button ->
								{
								}),
								new FluidDropdownWidget<String>(Component.empty(), 0, 0, 100, 20, 20, "Value 1", new String[]{"Value 1", "Value 2", "Value 3", "Value 4"}, value ->
								{
								})
						)
				)
		);
		super.init();
	}
	
	@Override
	public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		super.render(poseStack, mouseX, mouseY, partialTicks);
	}
}
