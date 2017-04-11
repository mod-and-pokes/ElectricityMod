package boblovespi.electricitymod.block;

import net.minecraft.block.material.Material;

/**
 * Created by Willi on 4/11/2017.
 */
public abstract class Block extends net.minecraft.block.Block
{
	public Block(Material p_i45394_1_)
	{
		super(p_i45394_1_);
	}

	public abstract String UNLOCALIZED_NAME();

	public abstract String REGISTERY_NAME();

	public String getMetaFilePath(int meta)
	{
		return UNLOCALIZED_NAME();
	}
}
