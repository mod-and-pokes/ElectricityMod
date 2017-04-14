package boblovespi.electricitymod.client.gui;

import boblovespi.electricitymod.container.ContainerBlastFurnace;
import boblovespi.electricitymod.tileentity.TileEntityBlastFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by Willi on 4/13/2017.
 */
public class GuiHandler implements IGuiHandler
{
	public static final int BLAST_FURNACE = 0;

	@Override public Object getServerGuiElement(int ID, EntityPlayer player,
			World world, int x, int y, int z)
	{
		if (ID == BLAST_FURNACE)
		{
			return new ContainerBlastFurnace(player.inventory,
					(TileEntityBlastFurnace) world
							.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}

	@Override public Object getClientGuiElement(int ID, EntityPlayer player,
			World world, int x, int y, int z)
	{
		if (ID == BLAST_FURNACE)
		{
			return new GuiBlastFurncae(player.inventory,
					(TileEntityBlastFurnace) world
							.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}
}
