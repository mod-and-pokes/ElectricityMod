package boblovespi.electricitymod.block;

import net.minecraft.block.Block;

/**
 * Created by Willi on 4/12/2017.
 */
public interface EMBlock
{
	public String UNLOCALIZED_NAME();

	public String REGISTERY_NAME();

	public String getMetaFilePath(int meta);

	Block toBlock();
}
