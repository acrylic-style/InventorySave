package xyz.acrylicstyle.inventorySave

import org.bukkit.plugin.java.JavaPlugin
import xyz.acrylicstyle.tomeito_api.TomeitoAPI
import xyz.acrylicstyle.tomeito_api.providers.ConfigProvider

class InventorySavePlugin: JavaPlugin() {
    companion object {
        lateinit var config: ConfigProvider

        const val PATTERN_STRING = "^[a-zA-Z0-9_]{1,32}$"

        @JvmStatic
        val PATTERN = "^[a-zA-Z0-9_]{1,32}$".toRegex()
    }

    override fun onEnable() {
        InventorySavePlugin.config = ConfigProvider.getConfig("./plugins/InventorySave/config.yml")
        TomeitoAPI.getInstance().registerCommands(classLoader, "inventories", "xyz.acrylicstyle.inventorySave.commands")
    }

    override fun onDisable() {
        InventorySavePlugin.config.save()
    }
}
