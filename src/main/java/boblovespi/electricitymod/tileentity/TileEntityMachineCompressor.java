package boblovespi.electricitymod.tileentity;

import boblovespi.electricitymod.energy.EnergyNetwork;
import boblovespi.electricitymod.energy.IRequiresEnergy;
import boblovespi.electricitymod.energy.IUsesEnergy;
import boblovespi.electricitymod.util.EMMachineRecipe;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Willi on 4/16/2017.
 */
public class TileEntityMachineCompressor extends TileEntity
		implements IRequiresEnergy, ITickable, ICapabilityProvider,
		EMRecipeMachine
{
	public static final int UPGRADE_SLOT = 2;
	private ItemStackHandler handler;

	public static final int INPUT_SLOT = 0;
	public static final int OUTPUT_SLOT = 1;

	private EnergyNetwork network;
	private boolean isActive = false;
	private boolean isPowered = false;
	private float currentRunTime = 0;
	private float totalRunTime = 1000;

	private static List<MachineCompressorRecipe> recipes = new ArrayList<MachineCompressorRecipe>();

	private MachineCompressorRecipe currentRecicpe;

	public TileEntityMachineCompressor()
	{
		handler = new ItemStackHandler(3);
	}

	public boolean isPowered()
	{
		return isPowered;
	}

	public boolean isRunning()
	{
		return isActive;
	}

	public float getCurrentRunTime()
	{
		return currentRunTime;
	}

	public float getTotalRunTime()
	{
		return totalRunTime;
	}

	@Override public EnergyNetwork getNetwork()
	{
		return network;
	}

	@Override public IUsesEnergy setNetwork(EnergyNetwork network)
	{
		this.network = network;
		return this;
	}

	@Override public boolean isActive()
	{
		return isActive;
	}

	@Override public float MinPowerRequired()
	{
		return 50;
	}

	@Override public float MaxPowerRequired()
	{
		return 10000;
	}

	@Override public void setHasEnoughPower()
	{
		isPowered = true;
	}

	@Override public void setHasTooMuchPower()
	{
		isPowered = false;
	}

	@Override public void setHasTooLittlePower()
	{
		isPowered = false;
	}

	@Override public void update()
	{
		boolean dirty = false;

		if (isPowered)
		{
			--currentRunTime;

			if (currentRunTime <= 0)
			{
				handler.insertItem(OUTPUT_SLOT, currentRecicpe.getOutput(),
						false);
				handler.extractItem(INPUT_SLOT,
						currentRecicpe.getInput().stackSize, false);
				isActive = false;
				dirty = true;
			}

			if (handler.getStackInSlot(INPUT_SLOT) == null
					|| handler.getStackInSlot(INPUT_SLOT).stackSize
					< currentRecicpe.getInput().stackSize)
			{
				isActive = false;
				dirty = true;
			}
		} else if (isActive)
		{
			currentRunTime += 10;
			if (currentRunTime > totalRunTime)
				currentRunTime = totalRunTime;

			if (handler.getStackInSlot(INPUT_SLOT).stackSize < currentRecicpe
					.getInput().stackSize)
			{
				isActive = false;
				dirty = true;
			}
		} else
		{
			ItemStack in = handler.getStackInSlot(INPUT_SLOT);
			ItemStack out = handler.getStackInSlot(OUTPUT_SLOT);

			if (in != null)
			{
				if (out == null)
				{
					for (MachineCompressorRecipe rec : recipes)
					{
						if (in.isItemEqual(rec.getInput())
								&& in.stackSize >= rec.getInput().stackSize)
						{
							currentRecicpe = rec;
							dirty = true;
							isActive = true;
							break;
						}
					}
				} else
				{
					if (in.isItemEqual(currentRecicpe.getInput())
							&& in.stackSize >= currentRecicpe
							.getInput().stackSize)
					{
						dirty = true;
						isActive = true;
					}
				}
			}
		}

		if (dirty)
		{
			markDirty();
			if (network != null)
				network.RecalculatePower();
		}
	}

	public static void Add(EMMachineRecipe recipe)
	{
		if (recipe instanceof MachineCompressorRecipe)
		{
			recipes.add((MachineCompressorRecipe) recipe);
		}
	}

	@Override public <T> T getCapability(Capability<T> capability,
			EnumFacing facing)
	{
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) handler;
		return super.getCapability(capability, facing);
	}

	@Override public boolean hasCapability(Capability<?> capability,
			EnumFacing facing)
	{
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}

	public static class MachineCompressorRecipe extends EMMachineRecipe
	{

		public MachineCompressorRecipe(ItemStack input, ItemStack output)
		{
			super(input, output);
		}
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

	@Override public void readFromNBT(NBTTagCompound tag)
	{
		isActive = tag.getBoolean("isActive");
		isPowered = tag.getBoolean("isPowered");
		currentRunTime = tag.getFloat("currentRunTime");
		totalRunTime = tag.getFloat("totalRunTime");

		handler.deserializeNBT(tag.getCompoundTag("handler"));
	}

	@Override public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		tag.setBoolean("isActive", isActive);
		tag.setBoolean("isPowered", isPowered);
		tag.setFloat("currentRunTime", currentRunTime);
		tag.setFloat("totalRunTime", totalRunTime);

		tag.setTag("handler", handler.serializeNBT());

		return super.writeToNBT(tag);
	}
}
