package boblovespi.electricitymod.tileentity;

import boblovespi.electricitymod.tileentity.energy.IEnergyMachine;
import boblovespi.electricitymod.tileentity.energy.IProducesEnergy;
import boblovespi.electricitymod.tileentity.energy.IUsesEnergy;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Willi on 4/12/2017.
 */
public class TileEntityPoweredLight extends TileEntity implements IEnergyMachine
{
	private static final float energyRequirement = 5;
	private float energyAmount = 0;
	private boolean isOn = false;

	private List<IUsesEnergy> connections = new ArrayList<IUsesEnergy>(1);

	public TileEntityPoweredLight(boolean isPowered)
	{
		isOn = isPowered;
	}

	@Override public boolean hasEnoughEnergy()
	{
		return energyAmount >= energyRequirement;
	}

	@Override public boolean hasExcessEnergy()
	{
		return energyAmount > energyRequirement;
	}

	@Override public void UpdateConnections()
	{
		for (IUsesEnergy mac : connections)
		{
			if (!Ping(mac))
			{
				if (mac.RemoveConnection(this))
					RemoveConnection(mac);
			}
		}
	}

	@Override public boolean Ping(IUsesEnergy machine)
	{
		return false;
	}

	@Override public void AddConnection(IUsesEnergy machine)
	{

	}

	@Override public boolean RemoveConnection(IUsesEnergy machine)
	{
		return false;
	}

	@Override public boolean ReplyPing(IUsesEnergy machine)
	{
		return false;
	}

	@Override public float getEnergyUsed()
	{
		return isOn ? 5 : 0;
	}

	@Override public IProducesEnergy getPowerSource()
	{
		return null;
	}

	@Override public void CheckPowerSource()
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
