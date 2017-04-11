package boblovespi.electricitymod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Willi on 4/11/2017.
 */
public class ComputerChip extends Item
{
	public ComputerChip()
	{
		super();
		System.out.println("[ELCM] unlocalized name: " + UNLOCALIZED_NAME());
		setUnlocalizedName(UNLOCALIZED_NAME());
		setRegistryName(REGISTERY_NAME());
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

	@Override public void getSubItems(Item itemIn, CreativeTabs tab,
			List<ItemStack> subItems)
	{
		subItems.add(new ItemStack(itemIn, 1, 0));
	}
}
