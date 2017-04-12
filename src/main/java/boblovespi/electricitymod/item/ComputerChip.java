package boblovespi.electricitymod.item;

import boblovespi.electricitymod.creativetabs.MachineTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Willi on 4/11/2017.
 */
public class ComputerChip extends Item implements EMItem
{
	public ComputerChip()
	{
		super();
		System.out.println("[ELCM] unlocalized name: " + UNLOCALIZED_NAME());
		setUnlocalizedName(UNLOCALIZED_NAME());
		setRegistryName(REGISTERY_NAME());
		setCreativeTab(MachineTab.MACHINE_TAB);
	}

	@Override public String UNLOCALIZED_NAME()
	{
		System.out.println("[ELCM] Calling unlocalized_name(): computer_chip");
		return "computer_chip";
	}

	@Override public String REGISTERY_NAME()
	{
		return UNLOCALIZED_NAME();
	}

	@Override public String getMetaFilePath(int meta)
	{
		return REGISTERY_NAME();
	}

	@Override public Item toItem()
	{
		return this;
	}

	@Override public void getSubItems(Item itemIn, CreativeTabs tab,
			List<ItemStack> subItems)
	{
		subItems.add(new ItemStack(itemIn, 1, 0));
	}
}
