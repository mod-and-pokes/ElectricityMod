package boblovespi.electricitymod.util;

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
		OreDictionary.registerOre("ingotCopper",
				new ItemStack(ItemInit.ingot, 1, 0));
		OreDictionary
				.registerOre("ingotTin", new ItemStack(ItemInit.ingot, 1, 1));
		OreDictionary.registerOre("chipBasic", ItemInit.computerChip);
		OreDictionary.registerOre("plateSilicon", ItemInit.siliconPlate);
	}
}
