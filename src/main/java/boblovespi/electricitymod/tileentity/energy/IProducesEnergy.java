package boblovespi.electricitymod.tileentity.energy;

/**
 * Created by Willi on 4/11/2017.
 */
@Deprecated public interface IProducesEnergy extends IUsesEnergy
{
	boolean RequestEnergy(float amount, IUsesEnergy requester);

	float GetTotalEnergyAmount();
	float GetRemainingEnergyAmount();
}
