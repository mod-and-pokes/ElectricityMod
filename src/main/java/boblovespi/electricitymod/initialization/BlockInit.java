package boblovespi.electricitymod.initialization;

import boblovespi.electricitymod.block.Concrete;
import boblovespi.electricitymod.block.EMBlock;
import boblovespi.electricitymod.block.RiceCrop;
import boblovespi.electricitymod.block.machine.BasicSolarPanel;
import boblovespi.electricitymod.block.machine.SiliconProcessor;
import gruffindor.electricitymod.block.Ore;
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
	public static EMBlock tinOre;
	public static EMBlock concrete;
	public static EMBlock siliconProcessor;
	public static EMBlock solarPanel;
	public static EMBlock riceCrop;

	public static void Init()
	{
		tinOre = new Ore("tin");
		concrete = new Concrete();
		siliconProcessor = new SiliconProcessor();
		solarPanel = new BasicSolarPanel();
		riceCrop = new RiceCrop();
	}

	public static void Register()
	{
		RegisterBlock(concrete);
		RegisterBlock(siliconProcessor);
		RegisterBlock(solarPanel);
		RegisterBlock(riceCrop);
		RegisterBlock(tinOre);
	}

	private static void RegisterBlock(EMBlock block)
	{
		GameRegistry.register(block.toBlock());
		net.minecraft.item.Item itemBlock = new ItemBlock(block.toBlock());

		itemBlock.setRegistryName(block.toBlock().getRegistryName());
		GameRegistry.register(itemBlock);
	}

	public static void RegisterRenders()
	{
		RegisterRender(concrete);
		RegisterRender(siliconProcessor);
		RegisterRender(solarPanel);
		RegisterRender(riceCrop);
		RegisterRender(tinOre);
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
