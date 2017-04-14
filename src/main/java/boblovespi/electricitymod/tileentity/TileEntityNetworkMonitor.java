package boblovespi.electricitymod.tileentity;

import boblovespi.electricitymod.energy.EnergyNetwork;
import boblovespi.electricitymod.energy.IRequiresEnergy;
import boblovespi.electricitymod.energy.IUsesEnergy;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Willi on 4/13/2017.
 */
public class TileEntityNetworkMonitor extends TileEntity implements IRunnableMachine,
		IRequiresEnergy
{
	private EnergyNetwork network;

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
		return false;
	}

	@Override public float MinPowerRequired()
	{
		return 0;
	}

	@Override public float MaxPowerRequired()
	{
		return 0;
	}

	@Override public void setHasEnoughPower()
	{

	}

	@Override public void setHasTooMuchPower()
	{

	}

	@Override public void setHasTooLittlePower()
	{

	}

	@Override public boolean AttemptRun()
	{
		return false;
	}

	@Override public boolean IsRunning()
	{
		return false;
	}

	@Override public void update()
	{

	}
}
