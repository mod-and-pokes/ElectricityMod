package boblovespi.electricitymod.energy;

/**
 * Created by Willi on 4/12/2017.
 */
public interface IStoresEnergy extends IRequiresEnergy, IProducesEnergy
{
	float getStoredEnergy();

	float getDischargeRate();

	boolean isDischarging();

	void setChargingRate(float rate);
}
