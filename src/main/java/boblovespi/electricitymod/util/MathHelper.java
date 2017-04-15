package boblovespi.electricitymod.util;

/**
 * Created by Willi on 4/14/2017.
 */
public class MathHelper
{
	public static double DEGREES_TO_RADIANS = Math.PI / 360;
	public static double RADIANS_TO_DEGREES = 360 / Math.PI;

	public static double Sin(double deg)
	{
		return net.minecraft.util.math.MathHelper
				.sin((float) (deg * DEGREES_TO_RADIANS));
	}

	public static double Cos(double deg)
	{
		return net.minecraft.util.math.MathHelper
				.cos((float) (deg * DEGREES_TO_RADIANS));
	}

	public static double Tan(double deg)
	{
		return Sin(deg) / Cos(deg);
	}

}
