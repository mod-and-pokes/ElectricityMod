package boblovespi.electricitymod.tileentity;

import boblovespi.electricitymod.initialization.ItemInit;
import boblovespi.electricitymod.util.ConfigLoader;
import boblovespi.electricitymod.util.FuelHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

/**
 * Created by Willi on 4/13/2017.
 */
public class TileEntityBlastFurnace extends TileEntity
		implements ITickable, ICapabilityProvider
{
	private ItemStackHandler itemHandler;

	public static final int[] COKE_SLOTS = { 3, 4 };
	public static final int IRON_SLOT = 1;
	public static final int FLUX_SLOT = 2;
	public static final int OUTPUT_SLOT = 5;
	public static final int SLAG_SLOT = 6;
	public static final int TUYERE_SLOT = 0;

	private boolean isBurning = false; // whether the blast furnace is burning or not
	private boolean isSmelting = false; // whether the blast furnace is smelting or not

	private float burnTime = 0; // the remaining burn time of the blast furnace
	private float fuelBurnTime = 200; // the amount of time a new piece of the same fuel would burn

	private float smeltTime; // the remaining time for the ingot to smelt to steel
	private float steelSmeltTime; // the amount of time it takes to smelt steel
	// private static final float smeltTimeScalar = ConfigLoader.BLAST_FURNACE_smeltSpeedScalar;
	// private static final float burnTimeScalar = ConfigLoader.BLAST_FURNACE_burnSpeedScalar;

	public TileEntityBlastFurnace()
	{
		itemHandler = new ItemStackHandler(7);
	}

	@Override public void readFromNBT(NBTTagCompound compound)
	{
		burnTime = compound.getInteger("burnTime");
		fuelBurnTime = compound.getInteger("fuelBurnTime");
		isBurning = compound.getBoolean("isBurning");

		smeltTime = compound.getInteger("smeltTime");
		steelSmeltTime = compound.getInteger("steelSmeltTime");
		isSmelting = compound.getBoolean("isSmelting");

		itemHandler.deserializeNBT(compound.getCompoundTag("itemHandler"));

		super.readFromNBT(compound);
	}

	@Override public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setInteger("burnTime", (int) burnTime);
		compound.setInteger("fuelBurnTime", (int) fuelBurnTime);
		compound.setBoolean("isBurning", isBurning);

		compound.setInteger("smeltTime", (int) smeltTime);
		compound.setInteger("steelSmeltTime", (int) steelSmeltTime);
		compound.setBoolean("isSmelting", isSmelting);

		compound.setTag("itemHandler", itemHandler.serializeNBT());

		return super.writeToNBT(compound);
	}

	@Override public void update()
	{
		if (worldObj.isRemote)
			return;
		steelSmeltTime = ConfigLoader.BLAST_FURNACE_smeltTime;
		if (isSmelting)
		{
			if (isBurning)
			{
				burnTime -= ConfigLoader.BLAST_FURNACE_burnSpeedScalar;
				smeltTime -= ConfigLoader.BLAST_FURNACE_smeltSpeedScalar;
				if (burnTime <= 0)
				{
					isBurning = false;
				}
				if (smeltTime <= 0)
				{
					isSmelting = false;
					itemHandler.extractItem(IRON_SLOT, 1, false);
					itemHandler.extractItem(FLUX_SLOT, 1, false);
					itemHandler.insertItem(OUTPUT_SLOT,
							new ItemStack(ItemInit.ingot.toItem(), 1, 3),
							false);
					itemHandler.insertItem(SLAG_SLOT,
							new ItemStack(ItemInit.slag.toItem(), 1, 0), false);
				}
			} else
			{
				if (itemHandler.getStackInSlot(COKE_SLOTS[0]) != null)
				{
					fuelBurnTime = new FuelHandler().getBurnTime(
							itemHandler.getStackInSlot(COKE_SLOTS[0]));

					itemHandler.extractItem(COKE_SLOTS[0], 1, false);
					burnTime = fuelBurnTime;
					isBurning = true;
				} else if (itemHandler.getStackInSlot(COKE_SLOTS[1]) != null)
				{
					fuelBurnTime = new FuelHandler().getBurnTime(
							itemHandler.getStackInSlot(COKE_SLOTS[0]));

					itemHandler.extractItem(COKE_SLOTS[1], 1, false);
					burnTime = fuelBurnTime;
					isBurning = true;
				} else
				{
					smeltTime = smeltTime
							+ 10 * ConfigLoader.BLAST_FURNACE_smeltSpeedScalar
							> ConfigLoader.BLAST_FURNACE_smeltTime ?
							ConfigLoader.BLAST_FURNACE_smeltTime :
							smeltTime + 10
									* ConfigLoader.BLAST_FURNACE_smeltSpeedScalar;
				}
			}
		} else if (isBurning)
		{
			if (itemHandler.getStackInSlot(IRON_SLOT) != null
					&& itemHandler.getStackInSlot(FLUX_SLOT) != null
					&& itemHandler.getStackInSlot(TUYERE_SLOT) != null)
			{
				smeltTime = ConfigLoader.BLAST_FURNACE_smeltTime;
				isSmelting = true;
			}
		} else if (itemHandler.getStackInSlot(IRON_SLOT) != null
				&& itemHandler.getStackInSlot(FLUX_SLOT) != null
				&& itemHandler.getStackInSlot(TUYERE_SLOT) != null)
		{
			if (itemHandler.getStackInSlot(COKE_SLOTS[0]) != null)
			{
				itemHandler.extractItem(COKE_SLOTS[0], 1, false);
				burnTime = fuelBurnTime;
				isBurning = true;
				smeltTime = ConfigLoader.BLAST_FURNACE_smeltTime;
				isSmelting = true;
			} else if (itemHandler.getStackInSlot(COKE_SLOTS[1]) != null)
			{
				itemHandler.extractItem(COKE_SLOTS[1], 1, false);
				burnTime = fuelBurnTime;
				isBurning = true;
				smeltTime = ConfigLoader.BLAST_FURNACE_smeltTime;
				isSmelting = true;
			}
		}
		markDirty();
		IBlockState state = worldObj.getBlockState(pos);
		worldObj.notifyBlockUpdate(pos, state, state, 3);
	}

	@Nullable @Override public SPacketUpdateTileEntity getUpdatePacket()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		int meta = getBlockMetadata();
		return new SPacketUpdateTileEntity(pos, meta, nbt);
	}

	@Override public void onDataPacket(NetworkManager net,
			SPacketUpdateTileEntity pkt)
	{
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override public NBTTagCompound getUpdateTag()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return nbt;
	}

	@Override public void handleUpdateTag(NBTTagCompound tag)
	{
		readFromNBT(tag);
	}

	@Override public NBTTagCompound getTileData()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return nbt;
	}

	@Override public <T> T getCapability(Capability<T> capability,
			EnumFacing facing)
	{
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) itemHandler;
		return super.getCapability(capability, facing);
	}

	@Override public boolean hasCapability(Capability<?> capability,
			EnumFacing facing)
	{
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}

	public boolean isBurning()
	{
		return isBurning;
	}

	public float getLastBurnTime()
	{
		return fuelBurnTime;
	}

	public float getRemainingBurnTime()
	{
		return burnTime;
	}

	public float getCurrentSmeltTime()
	{
		return smeltTime;
	}

	public float getTotalSmeltTime()
	{
		return steelSmeltTime;
	}

	public boolean isSmelting()
	{
		return isSmelting;
	}
}
