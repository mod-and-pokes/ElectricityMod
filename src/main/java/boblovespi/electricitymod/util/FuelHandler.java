package boblovespi.electricitymod.util;

import boblovespi.electricitymod.initialization.ItemInit;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

/**
 * Created by Willi on 4/13/2017.
 */
public class FuelHandler implements IFuelHandler
{
	private static final int TICKS_IN_FUEL_UNIT = 10 * 20;

	@Override public int getBurnTime(ItemStack fuel)
	{
		if (fuel == null)
			return 0;
		if (fuel.getItem() == ItemInit.coalCoke)
		{
			return 10 * TICKS_IN_FUEL_UNIT;
		}
		return 0;
	}
}
