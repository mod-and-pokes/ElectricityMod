package boblovespi.electricitymod.container;

import boblovespi.electricitymod.client.gui.slot.SlotOutputItem;
import boblovespi.electricitymod.client.gui.slot.SlotRestrictedItem;
import boblovespi.electricitymod.initialization.ItemInit;
import boblovespi.electricitymod.tileentity.TileEntityBlastFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Arrays;

/**
 * Created by Willi on 4/13/2017.
 */
public class ContainerBlastFurnace extends Container
{
	TileEntityBlastFurnace te;

	public ContainerBlastFurnace(IInventory playerInv,
			TileEntityBlastFurnace te)
	{
		IItemHandler handler = te
				.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
						null);

		this.te = te;

		addSlotToContainer(
				new SlotItemHandler(handler, TileEntityBlastFurnace.TUYERE_SLOT,
						8, 8));
		addSlotToContainer(new SlotRestrictedItem(handler,
				TileEntityBlastFurnace.IRON_SLOT, 47, 17,
				Arrays.asList(Items.IRON_INGOT)));
		addSlotToContainer(new SlotRestrictedItem(handler,
				TileEntityBlastFurnace.FLUX_SLOT, 65, 17,
				Arrays.asList(Items.REDSTONE)));
		addSlotToContainer(new SlotRestrictedItem(handler,
				TileEntityBlastFurnace.COKE_SLOTS[0], 47, 53,
				Arrays.asList(ItemInit.coalCoke.toItem())));
		addSlotToContainer(new SlotRestrictedItem(handler,
				TileEntityBlastFurnace.COKE_SLOTS[1], 65, 53,
				Arrays.asList(ItemInit.coalCoke.toItem())));
		addSlotToContainer(
				new SlotOutputItem(handler, TileEntityBlastFurnace.OUTPUT_SLOT,
						116, 35));
		addSlotToContainer(
				new SlotOutputItem(handler, TileEntityBlastFurnace.SLAG_SLOT,
						142, 35));

		int x = 8;
		int y = 84;

		for (int j = 0; j < 3; ++j)
		{
			for (int i = 0; i < 9; ++i)
				addSlotToContainer(
						new Slot(playerInv, i + j * 9 + 9, x + i * 18,
								y + j * 18));
		}
		for (int i = 0; i < 9; i++)
		{
			addSlotToContainer(new Slot(playerInv, i, x + i * 18, 142));
		}
	}

	@Override public boolean canInteractWith(EntityPlayer playerIn)
	{
		return !playerIn.isSpectator();
	}

}
