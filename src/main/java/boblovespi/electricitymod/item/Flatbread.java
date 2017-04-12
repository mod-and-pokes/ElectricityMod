package boblovespi.electricitymod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

/**
 * Created by Willi on 4/11/2017.
 */
public class Flatbread extends ItemFood implements EMItem
{
	public Flatbread()
	{
		super(8, 0.9f, true);
		setRegistryName(REGISTERY_NAME());
		setUnlocalizedName(UNLOCALIZED_NAME());
	}

	@Override public String UNLOCALIZED_NAME()
	{
		return "flatbread";
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

}
