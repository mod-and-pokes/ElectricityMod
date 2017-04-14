package boblovespi.electricitymod.util;

import boblovespi.electricitymod.ElectricityMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Willi on 4/12/2017.
 */
public class Debug
{
	private static Logger debugLog;

	public static Logger DebugLog()
	{
		if (debugLog == null)
			debugLog = LogManager.getFormatterLogger(ElectricityMod.MOD_ID);
		return debugLog;
	}

	public static void ChatLog(EntityPlayer player, String msg)
	{
		player.addChatComponentMessage(new TextComponentString(msg));
	}
}
