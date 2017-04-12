package boblovespi.electricitymod.initialization;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Willi on 4/11/2017.
 */
public class CraftingInit
{
	public static void Register()
	{
		// Crafting recipes
		GameRegistry
				.addShapedRecipe(new ItemStack(ItemInit.computerChip, 3), "rct",
						"cSc", "tcr", 'r', Items.REDSTONE, 'c',
						new ItemStack(ItemInit.ingot, 1, 0), 't',
						Blocks.REDSTONE_TORCH, 'S', ItemInit.siliconPlate);
		GameRegistry
				.addShapedRecipe(new ItemStack(BlockInit.concrete, 4), "SSS",
						"SWS", "SSS", 'S', Blocks.SAND, 'W',
						Items.WATER_BUCKET);
		GameRegistry
				.addShapedRecipe(new ItemStack(BlockInit.siliconProcessor, 1),
						"ccc", "P P", "IFI", 'c',
						new ItemStack(ItemInit.ingot, 1, 0), 'P', Blocks.PISTON,
						'I', Blocks.IRON_BLOCK, 'F', Blocks.FURNACE);
		GameRegistry
				.addShapedRecipe(new ItemStack(BlockInit.solarPanel, 1), "GGG",
						"tqt", "tct", 'G', Blocks.GLASS, 't',
						new ItemStack(ItemInit.ingot, 1, 1), 'q', Items.QUARTZ,
						'c', ItemInit.computerChip);

		// Furnace recipes
		GameRegistry.addSmelting(Items.BREAD, new ItemStack(ItemInit.flatbread),
				1f);
	}
}
