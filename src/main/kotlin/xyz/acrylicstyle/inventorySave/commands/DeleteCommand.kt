package xyz.acrylicstyle.inventorySave.commands

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import xyz.acrylicstyle.inventorySave.InventorySavePlugin
import xyz.acrylicstyle.tomeito_api.subcommand.PlayerSubCommandExecutor
import xyz.acrylicstyle.tomeito_api.subcommand.SubCommand

@SubCommand(name = "delete", description = "Deletes a inventory. This action cannot be undone.", usage = "/inventories delete <id>", alias = "remove")
class DeleteCommand: PlayerSubCommandExecutor() {
    override fun onCommand(player: Player, args: Array<String>) {
        if (args.isEmpty()) return player.sendMessage("${ChatColor.RED}Please specify ID!")
        if (!InventorySavePlugin.PATTERN.matches(args[0])) return player.sendMessage("${ChatColor.RED}ID does not match the pattern! (must be '${InventorySavePlugin.PATTERN_STRING}')")
        InventorySavePlugin.config.set("inventories.${player.uniqueId}.${args[0]}", null)
        player.sendMessage("${ChatColor.GREEN}Deleted inventory ${ChatColor.YELLOW}${args[0]}${ChatColor.GREEN}!")
    }
}