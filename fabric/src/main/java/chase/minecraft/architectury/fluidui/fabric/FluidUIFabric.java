package chase.minecraft.architectury.fluidui.fabric;

import chase.minecraft.architectury.fluidui.FluidUI;
import net.fabricmc.api.ClientModInitializer;

public class FluidUIFabric implements ClientModInitializer
{
	/**
	 * Runs the mod initializer on the client environment.
	 */
	@Override
	public void onInitializeClient()
	{
		FluidUI.init();
	}
}