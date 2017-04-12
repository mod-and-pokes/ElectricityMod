package boblovespi.electricitymod.initialization;

import boblovespi.electricitymod.block.Block;
import boblovespi.electricitymod.block.Concrete;
import boblovespi.electricitymod.block.machine.BasicSolarPanel;
import boblovespi.electricitymod.block.machine.SiliconProcessor;
import boblovespi.electricitymod.item.Item;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Willi on 4/11/2017.
 */
public class BlockInit
{
	public static Block concrete;
	public static Block siliconProcessor;
	public static Block solarPanel;

	public static void Init()
	{
		concrete = new Concrete();
		siliconProcessor = new SiliconProcessor();
		solarPanel = new BasicSolarPanel();
	}

	public static void Register()
	{
		RegisterBlock(concrete);
		RegisterBlock(siliconProcessor);
		RegisterBlock(solarPanel);
	}

	private static void RegisterBlock(Block block)
	{
		GameRegistry.register(block);
		net.minecraft.item.Item itemBlock = new ItemBlock(block);

		itemBlock.setRegistryName(block.getRegistryName());
		GameRegistry.register(itemBlock);
	}

	public static void RegisterRenders()
	{
		RegisterRender(concrete);
		RegisterRender(siliconProcessor);
		RegisterRender(solarPanel);
	}

	private static void RegisterRender(Block block)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
				.register(Item.getItemFromBlock(block), 0,
						new ModelResourceLocation(block.getRegistryName(),
								"inventory"));
	}
}
