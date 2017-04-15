package boblovespi.electricitymod.energy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Willi on 4/12/2017.
 * <p>
 * a class to store the energy network
 */
public class EnergyNetwork
{
	private List<IRequiresEnergy> machines;
	private List<IStoresEnergy> batteries;
	private List<IProducesEnergy> generators;

	private float maxPower;
	private float usedPower;
	private float remainingPower;
	private IUsesEnergy saveMachine;
	private static final int maxGridSize = 256;

	public EnergyNetwork(IUsesEnergy machine)
	{
		saveMachine = machine;
		machines = new ArrayList<IRequiresEnergy>(maxGridSize);
		batteries = new ArrayList<IStoresEnergy>(maxGridSize);
		generators = new ArrayList<IProducesEnergy>(maxGridSize);

		if (machine instanceof IStoresEnergy)
		{
			batteries.add((IStoresEnergy) machine);
		} else if (machine instanceof IProducesEnergy)
		{
			generators.add((IProducesEnergy) machine);
		} else if (machine instanceof IRequiresEnergy)
		{
			machines.add((IRequiresEnergy) machine);
		}
	}

	//	private EnergyNetwork(World world, List<IUsesEnergy> machines)
	//	{
	//		this.world = world;
	//		this.machines = machines;
	//		saveMachine = machines.get(0);
	//	}

	public boolean Add(IUsesEnergy machine)
	{
		boolean success = false;
		if (machine instanceof IStoresEnergy)
		{
			if (!batteries.contains(machine))
				batteries.add((IStoresEnergy) machine);
			success = true;
		} else if (machine instanceof IProducesEnergy)
		{
			if (!generators.contains(machine))
				generators.add((IProducesEnergy) machine);
			success = true;
		} else if (machine instanceof IRequiresEnergy)
		{
			if (!machines.contains(machine))
				machines.add((IRequiresEnergy) machine);
			success = true;
		}

		RecalculatePower();

		return success;
	}

	public void RecalculatePower()
	{
		float totalPower = 0;
		float usedPower = 0;
		float remainingPower = 0;

		for (IProducesEnergy gen : generators)
		{
			if (gen.isActive())
				totalPower += gen.getEnergyProduction();
		}

		for (IStoresEnergy bat : batteries)
		{
			if (bat.isActive())
			{
				if (bat.isDischarging())
					totalPower += bat.getDischargeRate();
			}
		}

		for (IStoresEnergy bat : batteries)
		{
			if (bat.isActive())
			{
				if (!bat.isDischarging())
				{
					if (bat.MinPowerRequired() + usedPower <= totalPower)
					{
						usedPower += bat.MinPowerRequired();
						bat.setChargingRate(bat.MinPowerRequired());
						bat.setHasEnoughPower();
					} else
					{
						bat.setChargingRate(0);
						bat.setHasTooLittlePower();
					}
				}
			}
		}

		for (IRequiresEnergy mac : machines)
		{
			if (mac.isActive())
			{
				if (mac.MinPowerRequired() + usedPower <= totalPower)
				{
					usedPower += mac.MinPowerRequired();
					mac.setHasEnoughPower();
				} else
				{
					mac.setHasTooLittlePower();
				}
			}
		}
		remainingPower = totalPower - usedPower;
		if (remainingPower > 0)
		{
			for (IStoresEnergy bat : batteries)
			{
				if (bat.isActive() && !bat.isDischarging()
						&& bat.MinPowerRequired() <= remainingPower)
				{
					if (bat.MaxPowerRequired() >= remainingPower)
					{
						bat.setChargingRate(remainingPower);
						bat.setHasEnoughPower();
						usedPower = totalPower;
						remainingPower = 0;
						break;
					} else
					{
						bat.setChargingRate(bat.MaxPowerRequired());
						bat.setHasEnoughPower();
						usedPower +=
								bat.MaxPowerRequired() - bat.MinPowerRequired();
						remainingPower = totalPower - usedPower;
					}
				}
			}
		}

		if (remainingPower > 0)
		{
			for (IRequiresEnergy mac : machines)
			{
				if (mac.isActive() && mac.MinPowerRequired() <= remainingPower)
				{
					if (mac.MaxPowerRequired() >= remainingPower)
					{
						mac.setHasEnoughPower();
						usedPower = totalPower;
						remainingPower = 0;
						break;
					} else
					{
						mac.setHasEnoughPower();
						usedPower +=
								mac.MaxPowerRequired() - mac.MinPowerRequired();
						remainingPower = totalPower - usedPower;
					}
				}
			}
		}

		if (remainingPower > 1)
		{
			if (!batteries.isEmpty())
			{
				batteries.get(0).setHasTooMuchPower();
			} else if (!machines.isEmpty())
			{
				machines.get(0).setHasTooMuchPower();
			}
		}
		this.maxPower = totalPower;
		this.usedPower = usedPower;
		this.remainingPower = remainingPower;
	}

	public void Join(EnergyNetwork other)
	{

		for (IUsesEnergy machine : other.machines)
		{
			Add(machine);
			machine.setNetwork(this);
		}

		for (IUsesEnergy machine : other.batteries)
		{
			Add(machine);
			machine.setNetwork(this);
		}

		for (IUsesEnergy machine : other.generators)
		{
			Add(machine);
			machine.setNetwork(this);
		}

		RecalculatePower();
	}

	public boolean isNetworkSaver(IUsesEnergy saveMachine)
	{
		return this.saveMachine == saveMachine;
	}

	public float getPowerGenerated()
	{
		return maxPower;
	}

	public float getPowerUsed()
	{
		return usedPower;
	}

	public float getPowerRemaining()
	{
		return remainingPower;
	}
}
