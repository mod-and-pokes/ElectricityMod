package boblovespi.electricitymod.item;

import net.minecraft.item.ItemFood;

/**
 * Created by Willi on 4/11/2017.
 */
public class Flatbread extends ItemFood
{
	public Flatbread()
	{
		super(8, 0.9f, true);
		setRegistryName(REGISTERY_NAME());
		setUnlocalizedName(UNLOCALIZED_NAME());
	}

	public String UNLOCALIZED_NAME()
	{
		return "flatbread";
	}

	public String REGISTERY_NAME()
	{
		return UNLOCALIZED_NAME();
	}

}
