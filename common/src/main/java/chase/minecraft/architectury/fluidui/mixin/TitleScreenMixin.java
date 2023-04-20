package chase.minecraft.architectury.fluidui.mixin;

import chase.minecraft.architectury.fluidui.FluidTheme;
import chase.minecraft.architectury.fluidui.gui.component.FluidButtonWidget;
import chase.minecraft.architectury.fluidui.impl.AsyncThemeLoader;
import dev.architectury.platform.Platform;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import test.TestScreen;

import static dev.architectury.hooks.client.screen.ScreenHooks.addRenderableWidget;

@Mixin(TitleScreen.class)
public class TitleScreenMixin
{
	@Inject(at = @At("RETURN"), method = "init")
	void init(CallbackInfo cb)
	{
		// Loads all the themes
		AsyncThemeLoader.getInstance().loadAll();

//		 TODO: Remove after testing
		if (Platform.isDevelopmentEnvironment())
		{
			TitleScreen screen = (TitleScreen) ((Object) this);
			addRenderableWidget(screen,
					new FluidButtonWidget(FluidTheme.DEFUALT, Component.literal("Show Demo"), 5, 5, 100, 20, b ->
					{
						Minecraft.getInstance().setScreen(new TestScreen(Component.literal("Test"), screen));
					}));
		}
	}
}
