package boblovespi.electricitymod.energy;

/**
 * Created by Willi on 4/12/2017.
 */
public interface IRequiresEnergy extends IUsesEnergy
{
	float MinPowerRequired();

	float MaxPowerRequired();

	void setHasEnoughPower();

	void setHasTooMuchPower();

	void setHasTooLittlePower();
}
