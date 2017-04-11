package boblovespi.electricitymod.item.types;

import net.minecraft.util.IStringSerializable;

/**
 * Created by Willi on 4/11/2017.
 */
public enum MetalTypes implements IStringSerializable, IMultiTypeEnum
{
	IRON("iron", -2), GOLD("gold", -1), COPPER("copper", 0), TIN("tin",
		1), BRONZE("bronze", 2), STEEL("steel", 3);

	private MetalTypes(String name, int id)
	{
		this.name = name;
		this.id = id;
	}

	private int id;
	private String name;

	@Override public String getName()
	{
		return name;
	}

	public int getId()
	{
		return id;
	}

	@Override public String toString()
	{
		return getName();
	}

	public static MetalTypes getType(int id)
	{
		for (MetalTypes t : MetalTypes.values())
		{
			if (t.getId() == id)
				return t;
		}
		return null;
	}
}
