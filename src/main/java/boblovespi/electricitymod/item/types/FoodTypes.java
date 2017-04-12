package boblovespi.electricitymod.item.types;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nullable;

/**
 * Created by Willi on 4/12/2017.
 */
public enum FoodTypes implements IStringSerializable, IMultiTypeEnum
{
	CHINESE_FOOD("chinese_food", 0);

	private FoodTypes(String name, int id)
	{
		this.name = name;
		this.id = id;
	}

	private int id;
	private String name;

	@Override public int getId()
	{
		return id;
	}

	@Override public String getName()
	{
		return name;
	}

	@Override public String toString()
	{
		return getName();
	}

	@Nullable public static FoodTypes getType(int id)
	{
		for (FoodTypes t : FoodTypes.values())
		{
			if (t.getId() == id)
				return t;
		}
		return null;
	}


}
