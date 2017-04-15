package boblovespi.electricitymod.entity;

import boblovespi.electricitymod.initialization.ItemInit;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Willi on 4/14/2017.
 */
public class CrossbowBolt extends EntityArrow
{
	public CrossbowBolt(World worldIn, EntityLivingBase shooter)
	{
		super(worldIn, shooter);
		setDamage(6);
		// setVelocity(-10 * MathHelper.Sin(shooter.rotationYawHead),
		//		10 * MathHelper.Sin(shooter.cameraPitch),
		// 		10 * MathHelper.Cos(shooter.rotationYawHead));

	}

	@Override protected ItemStack getArrowStack()
	{
		return new ItemStack(ItemInit.crosbowBolt.toItem());
	}
}
