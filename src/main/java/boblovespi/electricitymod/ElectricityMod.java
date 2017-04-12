package boblovespi.electricitymod;

import boblovespi.electricitymod.initialization.BlockInit;
import boblovespi.electricitymod.initialization.CraftingInit;
import boblovespi.electricitymod.initialization.ItemInit;
import boblovespi.electricitymod.initialization.ToolInit;
import boblovespi.electricitymod.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Willi on 4/10/2017.
 */

@Mod(modid = ElectricityMod.MOD_ID, version = ElectricityMod.VERSION, name = ElectricityMod.NAME) public class ElectricityMod
{
	public static final String MOD_ID = "electricitymod";
	public static final String VERSION = "0.0.0.0/a";

	public static final String NAME = "Electricity Mod";

	public static final String CLIENT_PROXY_CLASS = "boblovespi.electricitymod.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "boblovespi.electricitymod.proxy.ServerProxy";

	@Mod.Instance public static ElectricityMod main = new ElectricityMod();

	@SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = SERVER_PROXY_CLASS) public static CommonProxy proxy;



	@Mod.EventHandler public void PreInit(FMLPreInitializationEvent e)
	{
		//Logger log = e.getModLog();

		//log.debug(MOD_ID + " is in preinitiaization");

		ItemInit.Init();
		ItemInit.Register();
		
		ToolInit.Init();
		ToolInit.Register();
		
		BlockInit.Init();
		BlockInit.Register();

		proxy.PreInit();

	}

	@Mod.EventHandler public void Init(FMLInitializationEvent e)
	{

		//log.debug(MOD_ID + " is in preinitiaization");

		CraftingInit.Register();

		proxy.Init();

	}

	@Mod.EventHandler public void PostInit(FMLPostInitializationEvent e)
	{
		//Logger log = e.getModLog();

		//log.debug(MOD_ID + " is in preinitiaization");
	}
}
