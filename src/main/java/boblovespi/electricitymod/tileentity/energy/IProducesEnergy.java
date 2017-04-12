package boblovespi.electricitymod.tileentity.energy;

/**
 * Created by Willi on 4/11/2017.
 */
public interface IProducesEnergy extends IUsesEnergy
{
	public boolean RequestEnergy(float amount);

	public float GetTotalEnergyAmount();
	public float GetRemainingEnergyAmount();
}
