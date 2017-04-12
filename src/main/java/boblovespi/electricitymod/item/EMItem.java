package boblovespi.electricitymod.item;

import net.minecraft.item.Item;

/**
 * Created by Willi on 4/12/2017.
 */
public interface EMItem
{
	String UNLOCALIZED_NAME();

	String REGISTERY_NAME();

	String getMetaFilePath(int meta);

	Item toItem();
}
