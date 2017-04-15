package boblovespi.electricitymod.item;

import boblovespi.electricitymod.energy.EnergyNetwork;
import boblovespi.electricitymod.energy.IUsesEnergy;
import boblovespi.electricitymod.item.types.WireSpoolTypes;
import boblovespi.electricitymod.util.Debug;
import boblovespi.electricitymod.util.DimLocation;
import boblovespi.electricitymod.util.NBTHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Willi on 4/13/2017.
 */
public class WireSpool extends Item implements EMItem, IMulitTypable
{

	private static final NBTTagCompound nbt = new NBTTagCompound();

	public WireSpool()
	{
		super();
		setUnlocalizedName(UNLOCALIZED_NAME());
		setRegistryName(REGISTERY_NAME());
		setHasSubtypes(true);
		setMaxStackSize(1);
	}

	@Override public String UNLOCALIZED_NAME()
	{
		return "wire_spool";
	}

	@Override public String REGISTERY_NAME()
	{
		return UNLOCALIZED_NAME();
	}

	@Override public String getMetaFilePath(int meta)
	{
		return UNLOCALIZED_NAME() + "_" + getType(meta);
	}

	@Override public Item toItem()
	{
		return this;
	}

	@Override public String getType(int meta)
	{
		return WireSpoolTypes.getType(meta) != null ?
				WireSpoolTypes.getType(meta).getName() :
				"empty";
	}

	@Override public String getUnlocalizedName(ItemStack items)
	{
		return super.getUnlocalizedName() + "." + getType(
				items.getItemDamage());
	}

	@Override public void getSubItems(Item itemIn, CreativeTabs tab,
			List<ItemStack> subItems)
	{
		for (int i = 0; i < WireSpoolTypes.values().length; ++i)
			subItems.add(new ItemStack(itemIn, 1, i));
	}

	@Override public EnumActionResult onItemUseFirst(ItemStack stack,
			EntityPlayer player, World world, BlockPos pos, EnumFacing side,
			float hitX, float hitY, float hitZ, EnumHand hand)
	{

		if (world.isRemote)
			return EnumActionResult.PASS;

		if (WireSpoolTypes.getType(stack.getItemDamage())
				== WireSpoolTypes.EMPTY)
			return EnumActionResult.FAIL;

		TileEntity mac = world.getTileEntity(pos);

		if (mac instanceof IUsesEnergy)
		{
			player.addChatComponentMessage(new TextComponentString(
					"Connected to: " + pos.getX() + ", " + pos.getY() + ", "
							+ pos.getZ()));

			//			if (!stack.hasTagCompound())
			//			{
			//				Debug.ChatLog(player, "spool has no nbttag data, adding some");
			//				stack.getTagCompound().setIntArray("linkingPos",
			//						new int[] { 31415, world.provider.getDimension(),
			//								pos.getX(), pos.getY(), pos.getZ() });
			//				Debug.ChatLog(player,
			//						"current stuff in linkingPos" + stack.getTagCompound()
			//								.getIntArray("linkingPos")[2] + ", " + stack
			//								.getTagCompound().getIntArray("linkingPos")[3]
			//								+ ", " + stack.getTagCompound()
			//								.getIntArray("linkingPos")[4]);
			//			}

			if (!NBTHelper.hasKey(stack, "linkingPos"))
			{
				Debug.ChatLog(player, "linkingPos missing");

				NBTHelper.setLocationTag(stack, "linkingPos",
						world.provider.getDimension(), pos.getX(), pos.getY(),
						pos.getZ());

				return EnumActionResult.SUCCESS;
			}

			DimLocation loc1 = NBTHelper.getLocationTag(stack, "linkingPos");

			player.addChatComponentMessage(new TextComponentString(
					"Other machine: " + loc1.getX() + ", " + loc1.getY() + ", "
							+ loc1.getZ()));

			if (loc1.getDim() != world.provider.getDimension())
			{
				Debug.ChatLog(player, "Dimentions not the same");
				return EnumActionResult.FAIL;
			}

			IUsesEnergy machine1 = (IUsesEnergy) world
					.getTileEntity(loc1.getPos());
			IUsesEnergy machine2 = (IUsesEnergy) mac;

			if (machine1 == null)
			{
				Debug.ChatLog(player, "Machine 1 is null");
				return EnumActionResult.FAIL;
			}

			if (machine1.getNetwork() == null && machine2.getNetwork() == null)
			{
				Debug.ChatLog(player, "Both missing networks");
				machine1.setNetwork(new EnergyNetwork(machine1));
				machine1.getNetwork().Add(machine2);
			} else if (machine1.getNetwork() == null)
			{
				Debug.ChatLog(player, "Adding network to first machine");
				machine2.getNetwork().Add(machine1);
			} else if (machine2.getNetwork() == null)
			{
				Debug.ChatLog(player, "Adding network to second machine");
				machine1.getNetwork().Add(machine2);
			} else if (machine1.getNetwork() != machine2.getNetwork())
			{
				Debug.ChatLog(player, "Joining networks, networks different");
				machine1.getNetwork().Join(machine2.getNetwork());
			} else
			{
				Debug.ChatLog(player, "Networks the same, not taking action");
			}

			stack.setItemDamage(0);

			return EnumActionResult.SUCCESS;

		}
		return EnumActionResult.FAIL;
	}

	@Override public boolean updateItemStackNBT(NBTTagCompound nbt)
	{
		return true;
	}

	@Override public void addInformation(ItemStack stack, EntityPlayer playerIn,
			List<String> tooltip, boolean advanced)
	{
		if (NBTHelper.hasKey(stack, "linkingPos"))
		{
			DimLocation loc = NBTHelper.getLocationTag(stack, "linkingPos");
			tooltip.add(TextFormatting.DARK_GRAY + I18n
					.format(getUnlocalizedName() + ".tooltip", String.format(
							"Linking to: %1$d, %2$d, %3$d in dimension %4$d",
							loc.getX(), loc.getY(), loc.getZ(), loc.getDim())));
		}
	}
}

//	@Override public ActionResult<ItemStack> onItemRightClick(
//			ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
//			EnumHand hand)
//	{
//		RayTraceResult ray = rayTrace(worldIn, playerIn, false);
//		if (ray.typeOfHit != RayTraceResult.Type.BLOCK)
//			return new ActionResult<ItemStack>(EnumActionResult.PASS,
//					itemStackIn);
//
//		IBlockState mac = worldIn.getBlockState(ray.getBlockPos());
//
//		if (!worldIn.isRemote && mac.getBlock().hasTileEntity(mac))
//		{
//			TileEntity macTE = worldIn.getTileEntity(ray.getBlockPos());
//			if (macTE instanceof IUsesEnergy)
//			{
//				IUsesEnergy mach = (IUsesEnergy) macTE;
//				if (machine1 == null)
//				{
//					machine1 = mach;
//					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, new ItemStack(this));
//				} else if (machine2 == null)
//				{
//					machine2 = mach;
//
//					if (machine1.getNetwork() == null
//							&& machine2.getNetwork() == null)
//					{
//						machine1.setNetwork(new EnergyNetwork(machine1));
//						machine1.getNetwork().Add(machine2);
//					} else if (machine1.getNetwork() == null)
//					{
//						machine2.getNetwork().Add(machine1);
//					} else if (machine2.getNetwork() == null)
//					{
//						machine1.getNetwork().Add(machine2);
//					} else
//					{
//						machine1.getNetwork().Join(machine2.getNetwork());
//					}
//					machine1 = machine2 = null;
//				}
//			}
//		}
//
//		return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
//	}


