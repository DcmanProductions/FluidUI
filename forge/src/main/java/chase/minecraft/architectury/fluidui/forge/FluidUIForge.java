package chase.minecraft.architectury.fluidui.forge;

import dev.architectury.platform.forge.EventBuses;
import chase.minecraft.architectury.fluidui.FluidUI;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FluidUI.MOD_ID)
public class FluidUIForge
{
	public FluidUIForge()
	{
		// Submit our event bus to let architectury register our content on the right time
		EventBuses.registerModEventBus(FluidUI.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
		FluidUI.init();
	}
}