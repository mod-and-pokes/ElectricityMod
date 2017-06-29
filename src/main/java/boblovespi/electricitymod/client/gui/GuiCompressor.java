package boblovespi.electricitymod.client.gui;

import boblovespi.electricitymod.ElectricityMod;
import boblovespi.electricitymod.container.ContainerMachineCompressor;
import boblovespi.electricitymod.tileentity.TileEntityMachineCompressor;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Willi on 4/13/2017.
 */
public class GuiCompressor extends GuiContainer
{
	private TileEntityMachineCompressor te;
	private IInventory playerInv;

	public GuiCompressor(IInventory playerInv, TileEntityMachineCompressor te)
	{
		super(new ContainerMachineCompressor(playerInv, te));
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
						"textures/gui/container/machine_base.png"));
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		// int k = (int) (te.getRemainingBurnTime() / te.getLastBurnTime() * 14);
		int l = (int) (te.getCurrentRunTime() / te.getTotalRunTime() * 24);
		if (te.isPowered())
		{

			this.drawTexturedModalRect(guiLeft + 56, guiTop + 50, 176, 1, 14,
					0);
		}
		if (te.isRunning())
		{

			this.drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 14,
					24 - l, 16);
		}

		//		Debug.DebugLog()
		//				.debug("\n\n\tD E B U G\n-------------------------------------------------------\n");
		//
		//		Debug.DebugLog().debug("Is it burning?: " + te.isBurning());
		//
		//		Debug.DebugLog().debug("smelt progress: " + (
		//				te.getCurrentSmeltTime() / te.getTotalSmeltTime() * 24));
		//		Debug.DebugLog().debug("burn time: " + (
		//				te.getRemainingBurnTime() / te.getLastBurnTime() * 14) + "\n");
		//		Debug.DebugLog().debug("total smelt time: " + (te.getTotalSmeltTime()));
		//		Debug.DebugLog()
		//				.debug("current smelt time: " + (te.getCurrentSmeltTime())
		//						+ "\n");
		//		Debug.DebugLog().debug("last burn time: " + (te.getLastBurnTime()));
		//		Debug.DebugLog()
		//				.debug("remaining burn time: " + (te.getRemainingBurnTime()));

	}

	@Override protected void drawGuiContainerForegroundLayer(int mouseX,
			int mouseY)
	{
		drawCenteredString(mc.fontRendererObj, "Blast Furnace", 84, 6,
				180 + 100 * 256 + 100 * 256 * 256);
		fontRendererObj
				.drawString(playerInv.getDisplayName().getUnformattedText(), 8,
						this.ySize - 96 + 2, 4210752);
	}
}
