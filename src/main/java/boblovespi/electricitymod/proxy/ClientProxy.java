package boblovespi.electricitymod.proxy;

import boblovespi.electricitymod.ElectricityMod;
import boblovespi.electricitymod.client.gui.GuiHandler;
import boblovespi.electricitymod.initialization.BlockInit;
import boblovespi.electricitymod.initialization.ItemInit;
import boblovespi.electricitymod.initialization.ToolInit;
import boblovespi.electricitymod.util.ConfigLoader;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Created by Willi on 4/10/2017.
 */
public class ClientProxy implements CommonProxy
{
	@Override public void PreInit()
	{
		ItemInit.RegisterVariations();
		ConfigLoader.ClientPreInit();
	}

	@Override public void Init()
	{
		ItemInit.RegisterRenders();
		BlockInit.RegisterRenders();
		ToolInit.RegisterRenders();

		NetworkRegistry.INSTANCE
				.registerGuiHandler(ElectricityMod.main, new GuiHandler());
	}
}
