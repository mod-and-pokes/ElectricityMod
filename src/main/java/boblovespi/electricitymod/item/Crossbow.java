package boblovespi.electricitymod.item;

import boblovespi.electricitymod.initialization.ItemInit;
import boblovespi.electricitymod.util.Debug;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Willi on 4/14/2017.
 */
public class Crossbow extends Item implements EMItem
{
	public Crossbow()
	{
		super();
		setUnlocalizedName(UNLOCALIZED_NAME());
		setRegistryName(REGISTERY_NAME());
		setCreativeTab(CreativeTabs.COMBAT);
		setMaxStackSize(1);
		setHasSubtypes(true);

	}

	@Override public String UNLOCALIZED_NAME()
	{
		return "crossbow";
	}

	@Override public String REGISTERY_NAME()
	{
		return UNLOCALIZED_NAME();
	}

	@Override public String getMetaFilePath(int meta)
	{
		return UNLOCALIZED_NAME() + (meta == 0 ? "" : "_loaded");
	}

	@Override public Item toItem()
	{
		return this;
	}

	private ItemStack FindAmmo(EntityPlayer player)
	{
		if (isAmmo(player.getHeldItem(EnumHand.OFF_HAND)))
		{
			return player.getHeldItem(EnumHand.OFF_HAND);

		} else if (isAmmo(player.getHeldItem(EnumHand.MAIN_HAND)))
		{
			return player.getHeldItem(EnumHand.MAIN_HAND);

		} else
		{
			for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
			{
				ItemStack item = player.inventory.getStackInSlot(i);
				if (isAmmo(item))
					return item;
			}
			return null;
		}
	}

	private boolean isAmmo(ItemStack stack)
	{
		if (stack != null)
		{
			if (stack.getItem() == ItemInit.crosbowBolt.toItem())
				return true;
			if (stack.getItem() instanceof ItemArrow)
				return true;
		}
		return false;

	}

	@Override public int getMaxItemUseDuration(ItemStack stack)
	{
		return stack.getItemDamage() == 0 ? 200 : 0;
	}

	@Override public void getSubItems(Item itemIn, CreativeTabs tab,
			List<ItemStack> subItems)
	{
		subItems.add(new ItemStack(itemIn, 1, 0));
		subItems.add(new ItemStack(itemIn, 1, 1));
	}

	//	@Nullable @Override public ItemStack onItemUseFinish(ItemStack stack,
	//			World world, EntityLivingBase entity)
	//	{
	//
	//		if (entity instanceof EntityPlayer)
	//		{
	//			if (!NBTHelper.hasTag(stack) && !NBTHelper.hasKey(stack, "loaded"))
	//			{
	//				NBTHelper.setBoolTag(stack, "loaded", false);
	//			}
	//			if (!NBTHelper.getBoolTag(stack, "loaded"))
	//			{
	//
	//				if (!world.isRemote)
	//				{
	//					ItemStack ammo = FindAmmo((EntityPlayer) entity);
	//					if (ammo != null)
	//					{
	//						stack.setItemDamage(1);
	//						NBTHelper.ToggleBoolTag(stack, "loaded");
	//						--ammo.stackSize;
	//						if (ammo.stackSize <= 0)
	//						{
	//							((EntityPlayer) entity).inventory.deleteStack(ammo);
	//						}
	//					}
	//				}
	//
	//			} else
	//			{
	//				if (!world.isRemote)
	//				{
	//					CrossbowBolt bolt = new CrossbowBolt(world, entity);
	//
	//					bolt.setAim(entity, ((EntityPlayer) entity).rotationPitch,
	//							entity.rotationYaw, 0, 10, 0.05f);
	//					bolt.setDamage(6);
	//					// bolt.setPosition(((EntityPlayer) entity).posX, ((EntityPlayer) entity).posY, ((EntityPlayer) entity).posZ);
	//
	//					((EntityPlayer) entity).worldObj.spawnEntityInWorld(bolt);
	//
	//					// world.spawnEntityInWorld(bolt);
	//
	//					stack.setItemDamage(0);
	//
	//				}
	//			}
	//		}
	//		return stack;
	//	}

	@Override public ActionResult<ItemStack> onItemRightClick(ItemStack stack,
			World world, EntityPlayer player, EnumHand hand)
	{

		if (FindAmmo(player) != null)
		{
			player.setActiveHand(hand);

			// NBTHelper.ToggleBoolTag();

			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
		}
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
	}

	@Override public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.NONE;
	}

	@Override public void onPlayerStoppedUsing(ItemStack stack, World world,
			EntityLivingBase entityLiving, int timeLeft)
	{
		if (entityLiving instanceof EntityPlayer)
		{

			EntityPlayer player = (EntityPlayer) entityLiving;

			Debug.ChatLog(player, "is player, doing tests");

			if (stack.getItemDamage() == 0) // not loaded
			{
				Debug.ChatLog(player, "charging up");

				ItemStack ammo = FindAmmo(player);
				if (ammo != null)
				{
					Debug.ChatLog(player, "found ammo");

					stack.setItemDamage(getMaxItemUseDuration(stack)
							- timeLeft); // set charge of crossbow
					--ammo.stackSize;
					if (ammo.stackSize <= 0)
						player.inventory.deleteStack(ammo);
				}

			} else // loaded
			{
				Debug.ChatLog(player, "shooting");

				EntityArrow bolt = new EntityTippedArrow(world, player);
				bolt.setAim(player, player.rotationPitch, player.rotationYaw,
						10f, stack.getItemDamage() / 10f, 0.05f);

				world.spawnEntityInWorld(bolt);

			}

		}
	}

	//	@Nullable @Override public ItemStack onItemUseFinish(ItemStack stack,
	//			World worldIn, EntityLivingBase entityLiving)
	//	{
	//
	//		return stack;
	//	}

	//	@Override public void onUpdate(ItemStack stack, World world, Entity entity,
	//			int itemSlot, boolean isSelected)
	//	{
	//		if (!world.isRemote && isSelected)
	//		{
	//
	//		}
	//	}
}
