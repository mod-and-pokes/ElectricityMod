package boblovespi.electricitymod.item;

import boblovespi.electricitymod.item.types.MetalTypes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Willi on 4/10/2017.
 */
public class Ingot extends Item implements IMulitTypable, EMItem
{
	public Ingot()
	{
		super();
		setUnlocalizedName(UNLOCALIZED_NAME());
		setRegistryName(REGISTERY_NAME());
		setHasSubtypes(true);
		setCreativeTab(CreativeTabs.MATERIALS);
	}

	@Override public String getUnlocalizedName(ItemStack items)
	{
		return super.getUnlocalizedName() + "." + getType(
				items.getItemDamage());
	}

	@Override public String getMetaFilePath(int meta)
	{
		System.out.println(
				"file path: " + UNLOCALIZED_NAME() + "_" + getType(meta));

		return "ingot_" + getType(meta);
	}

	@Override public Item toItem()
	{
		return this;
	}

	@Override public String UNLOCALIZED_NAME()
	{
		return "ingot";
	}

	@Override public String REGISTERY_NAME()
	{
		return UNLOCALIZED_NAME();
	}

	@Override public void getSubItems(Item itemIn, CreativeTabs tab,
			List<ItemStack> subItems)
	{
		for (int i = 0; i < MetalTypes.values().length; ++i)
			subItems.add(new ItemStack(itemIn, 1, i));
	}

	@Override public String getType(int meta)
	{
		if (MetalTypes.getType(meta) != null)
			return MetalTypes.getType(meta).getName();
		else
			return "copper";
	}

}
