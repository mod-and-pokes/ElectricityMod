package boblovespi.electricitymod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Willi on 4/11/2017.
 */
public class SiliconPlate extends Item implements EMItem
{
	public SiliconPlate()
	{
		super();
		setRegistryName(REGISTERY_NAME());
		setUnlocalizedName(UNLOCALIZED_NAME());
		setCreativeTab(CreativeTabs.MATERIALS);
	}

	@Override public String UNLOCALIZED_NAME()
	{
		return "plate_silicon";
	}

	@Override public String REGISTERY_NAME()
	{
		return UNLOCALIZED_NAME();
	}

	@Override public String getMetaFilePath(int meta)
	{
		return REGISTERY_NAME();
	}

	@Override public Item toItem()
	{
		return this;
	}

	@Override public void getSubItems(Item itemIn, CreativeTabs tab,
			List<ItemStack> subItems)
	{
		subItems.add(new ItemStack(itemIn, 1, 0));
	}
}
