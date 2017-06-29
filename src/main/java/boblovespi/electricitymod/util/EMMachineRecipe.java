package boblovespi.electricitymod.util;

import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Willi on 4/16/2017.
 */
public abstract class EMMachineRecipe
{
	protected ItemStack input;
	protected ItemStack result;

	protected List<ItemStack> secondaryOutputs;
	protected List<Float> secondaryChances;

	public EMMachineRecipe(ItemStack input, ItemStack output)
	{
		this.input = input;
		this.result = output;
	}

	public boolean HasSecondaryOutputs()
	{
		return secondaryOutputs != null && secondaryOutputs.get(0) != null;
	}

	public ItemStack getInput()
	{
		return input;
	}

	public ItemStack getOutput()
	{
		return result;
	}
}
