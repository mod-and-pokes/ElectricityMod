package boblovespi.electricitymod.item.types;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nullable;

/**
 * Created by Willi on 4/13/2017.
 */
public enum WireSpoolTypes implements IStringSerializable, IMultiTypeEnum
{
	EMPTY("empty", 0), COPPER("copper", 1);

	private int id;
	private String name;

	WireSpoolTypes(String n, int i)
	{
		id = i;
		name = n;
	}

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

	@Nullable public static WireSpoolTypes getType(int i)
	{
		for (WireSpoolTypes t : WireSpoolTypes.values())
		{
			if (t.getId() == i)
				return t;
		}
		return null;
	}
}
