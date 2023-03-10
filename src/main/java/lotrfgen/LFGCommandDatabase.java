package lotrfgen;

import java.util.*;

import lotrfgen.LFGDatabaseGenerator.Database;
import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class LFGCommandDatabase extends CommandBase {
	public Random rand = new Random();

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args) {
		if (args.length == 1) {
			List<String> list = Database.getNames();
			return CommandBase.getListOfStringsMatchingLastWord(args, list.toArray(new String[0]));
		}
		return Collections.emptyList();
	}

	@Override
	public String getCommandName() {
		return "db";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "Something went wrong.";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		World world = sender.getEntityWorld();
		Database db = Database.forName(args[0]);
		if (db == null) {
			CommandBase.func_152373_a(sender, this, "Database \"" + args[0] + "\" does not exist.");
		} else {
			LFGDatabaseGenerator.setDisplay(args[0]);
			CommandBase.func_152373_a(sender, this, "Database \"" + args[0] + "\" is prepared.");
			LFGDatabaseGenerator gen = new LFGDatabaseGenerator();
			gen.generate(world, (EntityPlayer) sender, rand);
		}
	}
}
