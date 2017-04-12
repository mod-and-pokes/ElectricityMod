package boblovespi.electricitymod.creativetabs;

import boblovespi.electricitymod.initialization.BlockInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Willi on 4/11/2017.
 */
public class MachineTab extends CreativeTabs
{
	public static final MachineTab MACHINE_TAB = new MachineTab();

	public MachineTab()
	{
		super("tabMachines");
	}

	@Override public Item getTabIconItem()
	{
		return Item.getItemFromBlock(BlockInit.solarPanel.toBlock());
	}
}
