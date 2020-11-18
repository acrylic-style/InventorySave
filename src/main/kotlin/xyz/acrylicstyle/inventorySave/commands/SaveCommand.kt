package xyz.acrylicstyle.inventorySave.commands

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import xyz.acrylicstyle.inventorySave.InventorySavePlugin
import xyz.acrylicstyle.inventorySave.util.serialize
import xyz.acrylicstyle.tomeito_api.subcommand.PlayerSubCommandExecutor
import xyz.acrylicstyle.tomeito_api.subcommand.SubCommand

@SubCommand(name = "save", description = "Saves inventory.", usage = "/inventories save <id>")
class SaveCommand: PlayerSubCommandExecutor() {
    override fun onCommand(player: Player, args: Array<String>) {
        if (args.isEmpty()) return player.sendMessage("${ChatColor.RED}Please specify ID!")
        if (!InventorySavePlugin.PATTERN.matches(args[0])) return player.sendMessage("${ChatColor.RED}ID does not match the pattern! (must be '${InventorySavePlugin.PATTERN_STRING}')")
        InventorySavePlugin.config.serialize("inventories.${player.uniqueId}.${args[0]}", player.inventory)
        player.sendMessage("${ChatColor.GREEN}Saved your inventory as ${ChatColor.YELLOW}${args[0]}${ChatColor.GREEN}.")
    }
}
