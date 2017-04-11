package boblovespi.electricitymod.item;

//import net.minecraft.item.Item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Willi on 4/10/2017.
 */
public abstract class Item extends net.minecraft.item.Item
{
	public abstract String UNLOCALIZED_NAME();

	public abstract String REGISTERY_NAME();

	public abstract void getSubItems(Item itemIn, CreativeTabs tab,
			List<ItemStack> subItems);

	public String getMetaFilePath(int meta)
	{
		return UNLOCALIZED_NAME();
	}
}
