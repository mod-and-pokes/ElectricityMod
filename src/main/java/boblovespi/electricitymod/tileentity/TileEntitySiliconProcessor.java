package boblovespi.electricitymod.tileentity;

import boblovespi.electricitymod.initialization.ItemInit;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Willi on 4/11/2017.
 */
public class TileEntitySiliconProcessor extends TileEntity
		implements IRunnableMachine
{
	private int cooldown = 0;
	private static final int maxCooldown = 20;
	private static final float reqPower = 5;

	private float currentPower = 50;

	private boolean isProcessing = false;

	@Override public boolean AttemptRun()
	{
		if (!isProcessing)
		{
			isProcessing = true;
			cooldown = maxCooldown;
			return true;
		}
		return false;
	}

	@Override public boolean IsRunning()
	{
		return isProcessing;
	}

	@Override public void update()
	{
		boolean needsUpdate = false;
		if (isProcessing && currentPower > reqPower)
			--cooldown;
		if (worldObj.isRemote)
			return;

		if (cooldown < 0 && isProcessing)
		{
			worldObj.spawnEntityInWorld(
					new EntityItem(worldObj, pos.getX() + 0.5, pos.getY() + 1.1,
							pos.getZ() + 0.5,
							new ItemStack(ItemInit.siliconPlate)));
			isProcessing = false;
			needsUpdate = true;
		}
		if (cooldown > maxCooldown)
			cooldown = maxCooldown;
		if (needsUpdate)
			markDirty();
	}

	@Override public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setBoolean("isProcessing", isProcessing);
		if (cooldown > maxCooldown)
			cooldown = maxCooldown;
		if (cooldown < 0)
			cooldown = 0;
		tag.setInteger("cooldown", cooldown);
		return tag;
	}

	@Override public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		isProcessing = tag.getBoolean("isProcessing");
		cooldown = tag.getInteger("cooldown");
	}
}
