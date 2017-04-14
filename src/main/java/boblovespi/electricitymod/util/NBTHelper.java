package boblovespi.electricitymod.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;

/**
 * Created by Willi on 4/13/2017.
 */
public class NBTHelper
{
	public static NBTTagCompound getTag(ItemStack items)
	{
		if (!items.hasTagCompound())
		{
			items.setTagCompound(new NBTTagCompound());
		}
		return items.getTagCompound();
	}

	public static boolean hasKey(ItemStack stack, String key)
	{
		return hasTag(stack) && getTag(stack).hasKey(key);
	}

	public static boolean hasTag(ItemStack stack)
	{
		return stack.hasTagCompound();
	}

	public static void setLocationTag(ItemStack stack, String key, int dim,
			int x, int y, int z)
	{
		getTag(stack).setIntArray(key, new int[] { 31415, dim, x, y, z });
	}

	@Nullable public static DimLocation getLocationTag(ItemStack stack,
			String key)
	{
		int[] locs = getTag(stack).getIntArray(key);
		if (locs[0] != DimLocation.CLASS_KEY && locs.length != 5)
			return null;

		return new DimLocation(locs[1], locs[2], locs[3], locs[4]);
	}
}
