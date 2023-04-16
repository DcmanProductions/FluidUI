package chase.minecraft.architectury.fluidui.mixin;

import chase.minecraft.architectury.fluidui.TestScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin
{
	@Inject(at = @At("RETURN"), method = "init")
	void init(CallbackInfo cb)
	{
		TitleScreen screen = (TitleScreen) ((Object) this);
		Minecraft minecraft = Minecraft.getInstance();
		
		minecraft.setScreen(new TestScreen(Component.literal("Test"), screen));
	}
}
