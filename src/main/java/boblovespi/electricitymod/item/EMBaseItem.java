package boblovespi.electricitymod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Willi on 4/13/2017.
 */

public class EMBaseItem extends Item implements EMItem
{
	private String unlocalizedName;

	public EMBaseItem(String unlocalizedName, CreativeTabs ct)
	{
		this.unlocalizedName = unlocalizedName;
		setUnlocalizedName(UNLOCALIZED_NAME());
		setRegistryName(REGISTERY_NAME());
		setCreativeTab(ct);

	}

	@Override public String UNLOCALIZED_NAME()
	{
		return unlocalizedName;
	}

	@Override public String REGISTERY_NAME()
	{
		return UNLOCALIZED_NAME();
	}

	@Override public String getMetaFilePath(int meta)
	{
		return UNLOCALIZED_NAME();
	}

	@Override public Item toItem()
	{
		return this;
	}
}
