package boblovespi.electricitymod.tileentity;

import boblovespi.electricitymod.energy.EnergyNetwork;
import boblovespi.electricitymod.energy.IRequiresEnergy;
import boblovespi.electricitymod.energy.IUsesEnergy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

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
		markDirty();
	}

	@Override public void setHasTooMuchPower()
	{
		isOn = false;
		markDirty();
	}

	@Override public void setHasTooLittlePower()
	{
		isOn = false;
		markDirty();
	}

	@Override public void markDirty()
	{
		super.markDirty();
		if (worldObj.isRemote)
			return;
		getBlockType().updateTick(worldObj, pos, worldObj.getBlockState(pos),
				worldObj.rand);
	}
}
