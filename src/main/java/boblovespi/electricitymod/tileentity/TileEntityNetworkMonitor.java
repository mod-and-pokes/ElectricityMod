package boblovespi.electricitymod.tileentity;

import boblovespi.electricitymod.energy.EnergyNetwork;
import boblovespi.electricitymod.energy.IRequiresEnergy;
import boblovespi.electricitymod.energy.IUsesEnergy;
import boblovespi.electricitymod.util.Debug;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Willi on 4/13/2017.
 */
public class TileEntityNetworkMonitor extends TileEntity
		implements IRequiresEnergy
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

	public void OnRightClick(EntityPlayer player)
	{
		if (worldObj.isRemote || player == null)
			return;
		Debug.ChatLog(player, "\n------[NETWORK INFO]------");
		Debug.ChatLog(player,
				"network power generated: " + network.getPowerGenerated());
		Debug.ChatLog(player, "network power used: " + network.getPowerUsed());
		Debug.ChatLog(player,
				"network power remaining: " + network.getPowerRemaining());
		Debug.ChatLog(player, "\n--------[END INFO]--------");


	}
}
