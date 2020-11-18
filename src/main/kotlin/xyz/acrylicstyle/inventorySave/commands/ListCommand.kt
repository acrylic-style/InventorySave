package xyz.acrylicstyle.inventorySave.commands

import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.chat.hover.content.Text
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import xyz.acrylicstyle.inventorySave.InventorySavePlugin
import xyz.acrylicstyle.tomeito_api.subcommand.PlayerSubCommandExecutor
import xyz.acrylicstyle.tomeito_api.subcommand.SubCommand

@SubCommand(name = "list", description = "Lists your saved inventories.", usage = "/inventories list")
class ListCommand: PlayerSubCommandExecutor() {
    override fun onCommand(player: Player, args: Array<String>) {
        InventorySavePlugin.config.getConfigSectionValue("inventories.${player.uniqueId}", false).keys.forEach { id ->
            val text = TextComponent()
            val load = TextComponent("${ChatColor.GOLD}[L] ")
            load.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/inventories load $id")
            load.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text("${ChatColor.GREEN}Loads ${ChatColor.YELLOW}$id${ChatColor.GREEN}."))
            val remove = TextComponent("${ChatColor.RED}[R] ")
            remove.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/inventories delete $id")
            remove.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text("${ChatColor.RED}Deletes ${ChatColor.YELLOW}${id}${ChatColor.RED}. This action cannot be undone!"))
            val desc = TextComponent("${ChatColor.GRAY}- ${ChatColor.LIGHT_PURPLE}${id}")
            text.addExtra(load)
            text.addExtra(remove)
            text.addExtra(desc)
            player.sendMessage(text)
        }
    }
}
