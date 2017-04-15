package boblovespi.electricitymod.tileentity;

import boblovespi.electricitymod.block.machine.PoweredLight;
import boblovespi.electricitymod.energy.EnergyNetwork;
import boblovespi.electricitymod.energy.IRequiresEnergy;
import boblovespi.electricitymod.energy.IUsesEnergy;
import boblovespi.electricitymod.initialization.BlockInit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.chunk.Chunk;

import javax.annotation.Nullable;

/**
 * Created by Willi on 4/12/2017.
 */
public class TileEntityPoweredLight extends TileEntity
		implements IRequiresEnergy, ITickable
{
	private static final float energyRequirement = 5;
	private float energyAmount = 0;
	public boolean isOn = false;

	private EnergyNetwork network = null;

	public TileEntityPoweredLight(boolean isPowered)
	{
		isOn = isPowered;
	}

	public TileEntityPoweredLight()
	{
		isOn = false;
	}

	@Override public void update()
	{

	}

	@Override public EnergyNetwork getNetwork()
	{
		return network;
	}

	@Override public IUsesEnergy setNetwork(EnergyNetwork network)
	{
		markDirty();
		this.network = network;
		return this;
	}

	@Override public boolean isActive()
	{
		return true;
	}

	@Override public float MinPowerRequired()
	{
		return 1;
	}

	@Override public float MaxPowerRequired()
	{
		return 50;
	}

	@Override public void setHasEnoughPower()
	{
		isOn = true;
		ForceUpdate();
		markDirty();
	}

	@Override public void setHasTooMuchPower()
	{
		isOn = false;
		ForceUpdate();
		markDirty();
	}

	@Override public void setHasTooLittlePower()
	{
		isOn = false;
		ForceUpdate();
		markDirty();
	}

	@Override public void readFromNBT(NBTTagCompound compound)
	{
		isOn = compound.getBoolean("isOn");
		super.readFromNBT(compound);

	}

	@Override public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setBoolean("isOn", isOn);
		return super.writeToNBT(compound);
	}

	@Nullable @Override public SPacketUpdateTileEntity getUpdatePacket()
	{
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		int meta = getBlockMetadata();
		return new SPacketUpdateTileEntity(pos, meta, tag);
	}

	@Override public void onDataPacket(NetworkManager net,
			SPacketUpdateTileEntity pkt)
	{
		readFromNBT(pkt.getNbtCompound());
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

	private void ForceUpdate()
	{
		if (!worldObj.isRemote)
		{
			if (worldObj.getBlockState(pos).getActualState(worldObj, pos)
					.getBlock() == BlockInit.poweredLight.toBlock())
			{
				PoweredLight b = (PoweredLight) worldObj.getBlockState(pos)
						.getActualState(worldObj, pos).getBlock();

				IBlockState old = worldObj.getBlockState(pos);
				//				IBlockState new_ = worldObj.getBlockState(pos)
				//						.withProperty(PoweredLight.IsActivated, isOn);
				//				worldObj.setBlockState(pos, worldObj.getBlockState(pos)
				//						.withProperty(PoweredLight.IsActivated, isOn));

				// worldObj.getBlockState(pos).getBlock().LightToggle()

				if (isOn)
				{
					b.LightOn();
				} else
				{
					b.LightOff();
				}

				worldObj.notifyLightSet(pos);
				worldObj.notifyBlockUpdate(pos, old, worldObj.getBlockState(pos)
						.getActualState(worldObj, pos), 3);
				Chunk c = worldObj.getChunkFromBlockCoords(pos);
				c.checkLight();
				worldObj.scheduleUpdate(pos, b, 4);
			}
		}
	}
}
