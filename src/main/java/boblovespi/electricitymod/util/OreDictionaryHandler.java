package boblovespi.electricitymod.util;

import boblovespi.electricitymod.initialization.BlockInit;
import boblovespi.electricitymod.initialization.ItemInit;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Willi on 4/11/2017.
 */
public class OreDictionaryHandler
{
	public static void Register()
	{
		// Ingots

		OreDictionary.registerOre("ingotCopper",
				new ItemStack(ItemInit.ingot.toItem(), 1, 0));
		OreDictionary.registerOre("ingotTin",
				new ItemStack(ItemInit.ingot.toItem(), 1, 1));
		OreDictionary.registerOre("ingotBronze",
				new ItemStack(ItemInit.ingot.toItem(), 1, 2));
		OreDictionary.registerOre("ingotSteel",
				new ItemStack(ItemInit.ingot.toItem(), 1, 3));

		// Misc

		OreDictionary.registerOre("chipBasic", ItemInit.computerChip.toItem());
		OreDictionary
				.registerOre("plateSilicon", ItemInit.siliconPlate.toItem());
		OreDictionary.registerOre("foodRice", ItemInit.riceGrain.toItem());
		OreDictionary
				.registerOre("blockConcrete", BlockInit.concrete.toBlock());
		OreDictionary.registerOre("itemSlag", ItemInit.slag.toItem());
		OreDictionary.registerOre("fuelCoke", ItemInit.coalCoke.toItem());
	}
}
