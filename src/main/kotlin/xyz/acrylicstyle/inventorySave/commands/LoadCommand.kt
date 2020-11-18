package xyz.acrylicstyle.inventorySave.commands

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import xyz.acrylicstyle.inventorySave.InventorySavePlugin
import xyz.acrylicstyle.inventorySave.util.deserialize
import xyz.acrylicstyle.tomeito_api.subcommand.PlayerSubCommandExecutor
import xyz.acrylicstyle.tomeito_api.subcommand.SubCommand

@SubCommand(name = "load", description = "Loads inventory.", usage = "/inventories load <id>")
class LoadCommand: PlayerSubCommandExecutor() {
    override fun onCommand(player: Player, args: Array<String>) {
        if (args.isEmpty()) return player.sendMessage("${ChatColor.RED}Please specify ID!")
        if (!InventorySavePlugin.PATTERN.matches(args[0])) return player.sendMessage("${ChatColor.RED}ID does not match the pattern! (must be '${InventorySavePlugin.PATTERN_STRING}')")
        InventorySavePlugin.config.deserialize("inventories.${player.uniqueId}.${args[0]}", player.inventory)
        player.sendMessage("${ChatColor.GREEN}Loaded inventory ${ChatColor.YELLOW}${args[0]}${ChatColor.GREEN}.")
    }
}
