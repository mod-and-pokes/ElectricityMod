package boblovespi.electricitymod;

import boblovespi.electricitymod.initialization.*;
import boblovespi.electricitymod.proxy.CommonProxy;
import boblovespi.electricitymod.util.ConfigLoader;
import boblovespi.electricitymod.util.FuelHandler;
import boblovespi.electricitymod.util.OreDictionaryHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Willi on 4/10/2017.
 */

@Mod(modid = ElectricityMod.MOD_ID, version = ElectricityMod.VERSION, name = ElectricityMod.NAME, guiFactory = ElectricityMod.GUI_FACTORY) public class ElectricityMod
{
	public static final String MOD_ID = "electricitymod";
	public static final String VERSION = "0.0.0.0-a";

	public static final String NAME = "Electricity Mod";

	public static final String CLIENT_PROXY_CLASS = "boblovespi.electricitymod.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "boblovespi.electricitymod.proxy.ServerProxy";

	public static final String GUI_FACTORY = "boblovespi.electricitymod.util.EMConfigGuiFactory";

	@Mod.Instance public static ElectricityMod main = new ElectricityMod();

	@SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = SERVER_PROXY_CLASS) public static CommonProxy proxy;

	@Mod.EventHandler public void PreInit(FMLPreInitializationEvent e)
	{
		//Logger log = e.getModLog();

		//log.debug(MOD_ID + " is in preinitiaization");

		ConfigLoader.PreInit();

		ItemInit.Init();
		ItemInit.Register();

		ToolInit.Init();
		ToolInit.Register();

		BlockInit.Init();
		BlockInit.Register();

		proxy.PreInit();

		EntityInit.Init();

	}

	@Mod.EventHandler public void Init(FMLInitializationEvent e)
	{

		//log.debug(MOD_ID + " is in preinitiaization");

		GameRegistry.registerFuelHandler(new FuelHandler());

		TileEntityInit.Init();

		OreDictionaryHandler.Register();

		CraftingInit.Register();

		proxy.Init();

	}

	@Mod.EventHandler public void PostInit(FMLPostInitializationEvent e)
	{
		//Logger log = e.getModLog();

		//log.debug(MOD_ID + " is in preinitiaization");
	}
}
