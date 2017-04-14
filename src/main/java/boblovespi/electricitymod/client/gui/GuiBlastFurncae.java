package boblovespi.electricitymod.client.gui;

import boblovespi.electricitymod.ElectricityMod;
import boblovespi.electricitymod.container.ContainerBlastFurnace;
import boblovespi.electricitymod.tileentity.TileEntityBlastFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Willi on 4/13/2017.
 */
public class GuiBlastFurncae extends GuiContainer
{
	private TileEntityBlastFurnace te;
	private IInventory playerInv;

	public GuiBlastFurncae(IInventory playerInv, TileEntityBlastFurnace te)
	{
		super(new ContainerBlastFurnace(playerInv, te));
		this.te = te;
		this.playerInv = playerInv;

		this.xSize = 176;
		this.ySize = 166;

	}

	@Override protected void drawGuiContainerBackgroundLayer(float partialTicks,
			int mouseX, int mouseY)
	{
		GlStateManager.color(1, 1, 1);
		mc.getTextureManager().bindTexture(
				new ResourceLocation(ElectricityMod.MOD_ID,
						"textures/gui/container/blast_furnace.png"));
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	@Override protected void drawGuiContainerForegroundLayer(int mouseX,
			int mouseY)
	{
		drawCenteredString(mc.fontRendererObj, "Blast Furnace", 84, 8, 139);
	}
}
