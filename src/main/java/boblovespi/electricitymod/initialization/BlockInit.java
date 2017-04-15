package boblovespi.electricitymod.initialization;

import boblovespi.electricitymod.block.Concrete;
import boblovespi.electricitymod.block.EMBlock;
import boblovespi.electricitymod.block.RiceCrop;
import boblovespi.electricitymod.block.machine.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Willi on 4/11/2017.
 */
public class BlockInit
{
	public static EMBlock concrete;
	public static EMBlock siliconProcessor;
	public static EMBlock solarPanel;
	public static EMBlock riceCrop;
	public static EMBlock poweredLight;
	public static EMBlock blastFurnace;
	public static EMBlock networkMonitor;

	public static void Init()
	{
		concrete = new Concrete();
		siliconProcessor = new SiliconProcessor();
		solarPanel = new BasicSolarPanel();
		riceCrop = new RiceCrop();
		poweredLight = new PoweredLight();
		blastFurnace = new BlastFurnace();
		networkMonitor = new EnergyNetworkMonitor();
	}

	public static void Register()
	{
		RegisterBlock(concrete);
		RegisterBlock(siliconProcessor);
		RegisterBlock(solarPanel);
		RegisterBlock(poweredLight);
		RegisterBlock(blastFurnace);
		RegisterBlock(networkMonitor);

		RegisterOnlyBlock(riceCrop);
	}

	private static void RegisterBlock(EMBlock block)
	{
		GameRegistry.register(block.toBlock());
		net.minecraft.item.Item itemBlock = new ItemBlock(block.toBlock());

		itemBlock.setRegistryName(block.toBlock().getRegistryName());
		GameRegistry.register(itemBlock);
	}

	private static void RegisterOnlyBlock(EMBlock block)
	{
		GameRegistry.register(block.toBlock());
	}

	public static void RegisterRenders()
	{
		RegisterRender(concrete);
		RegisterRender(siliconProcessor);
		RegisterRender(solarPanel);
		RegisterRender(poweredLight);
		RegisterRender(blastFurnace);
		RegisterRender(networkMonitor);

		RegisterRender(riceCrop);
	}

	private static void RegisterRender(EMBlock block)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
				.register(Item.getItemFromBlock(block.toBlock()), 0,
						new ModelResourceLocation(
								block.toBlock().getRegistryName(),
								"inventory"));
	}
}
