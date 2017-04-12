package boblovespi.electricitymod.tileentity;

import boblovespi.electricitymod.tileentity.energy.IProducesEnergy;
import boblovespi.electricitymod.tileentity.energy.IUsesEnergy;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.EnumSkyBlock;

import java.util.List;

/**
 * Created by Willi on 4/11/2017.
 */
public class TileEntitySolarPanel extends TileEntity
		implements IRunnableMachine, IProducesEnergy
{

	private List<IUsesEnergy> connections;

	private boolean canSeeSky = false;

	private float powerGenerated = 0f;
	private float usedPower = 0f;
	private float remainingPower = 0f;

	private int timer = 20;

	@Override public boolean AttemptRun()
	{
		ForceUpdate();
		if (!worldObj.isRemote && Minecraft.getMinecraft().thePlayer != null)
			Minecraft.getMinecraft().thePlayer.addChatMessage(
					new TextComponentString(
							"Power: " + ((Float) powerGenerated).toString()
									+ " | Can see sky: " + canSeeSky));
		return true;
	}

	@Override public boolean IsRunning()
	{
		return false;
	}

	@Override public void update()
	{
		if (--timer == 0)
		{
			timer = 20;
			ForceUpdate();
		}
	}

	private void ForceUpdate()
	{
		if (worldObj.isRemote)
			return;
		timer = 20;

		int light = worldObj.getLightFor(EnumSkyBlock.SKY, getPos().up());
		canSeeSky = worldObj.canSeeSky(getPos().up());
		canSeeSky = light > 5;
		if (canSeeSky)
			powerGenerated = worldObj.isDaytime() ? light * light / 10 : 2;
		else
			powerGenerated = 0;

	}

	@Override public boolean hasEnoughEnergy()
	{
		return false;
	}

	@Override public boolean hasExcessEnergy()
	{
		return true;
	}

	@Override public void UpdateConnections()
	{

	}

	@Override public boolean Ping(IUsesEnergy machine)
	{
		return false;
	}

	@Override @Deprecated public float getEnergyAmount()
	{
		return remainingPower;
	}

	@Override public void AddConnection(IUsesEnergy machine)
	{

	}

	@Override public boolean RemoveConnection(IUsesEnergy machine)
	{
		if (connections.contains(machine))
		{
			if (connections.remove(machine))
			{

			}
		} else
			return false;
	}

	@Override public boolean RequestEnergy(float amount)
	{
		return false;
	}

	@Override public float GetTotalEnergyAmount()
	{
		return powerGenerated;
	}

	@Override public float GetRemainingEnergyAmount()
	{
		return remainingPower;
	}
}
