package boblovespi.electricitymod.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.EnumSkyBlock;

/**
 * Created by Willi on 4/11/2017.
 */
public class TileEntitySolarPanel extends TileEntity implements IRunnableMachine
{
	private boolean canSeeSky = false;
	private float powerGenerated = 0f;
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
		// canSeeSky = worldObj.canSeeSky(getPos());
		int light = worldObj.getLightFor(EnumSkyBlock.SKY, getPos().up());
		canSeeSky = light > 5;
		if (canSeeSky)
			powerGenerated = worldObj.isDaytime() ? light * light / 10 : 2;
		else
			powerGenerated = 0;
	}
}
