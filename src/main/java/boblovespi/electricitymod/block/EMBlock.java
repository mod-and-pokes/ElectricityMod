package boblovespi.electricitymod.block;

import net.minecraft.block.Block;

/**
 * Created by Willi on 4/12/2017.
 */
public interface EMBlock
{
	String UNLOCALIZED_NAME();

	String REGISTERY_NAME();

	String getMetaFilePath(int meta);

	Block toBlock();
}
