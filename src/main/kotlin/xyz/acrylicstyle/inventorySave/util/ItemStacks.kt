package xyz.acrylicstyle.inventorySave.util

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import xyz.acrylicstyle.tomeito_api.providers.ConfigProvider
import java.util.*

object ItemStacks {
    @JvmStatic
    fun serialize(item: ItemStack) = Base64.getEncoder().encode(item.serializeAsBytes())!!

    @JvmStatic
    fun deserialize(s: ByteArray?) = if (s == null) null else ItemStack.deserializeBytes(Base64.getDecoder().decode(s))
}

fun YamlConfiguration.serialize(prefix: String, from: Inventory) {
    from.forEachIndexed { index, item ->
        this.set("$prefix.inventory.slot$index", if (item == null) null else ItemStacks.serialize(item))
    }
    if (this is ConfigProvider) this.save()
}

fun YamlConfiguration.deserialize(prefix: String, to: Inventory) {
    for (i in 0..to.size) {
        to.setItem(i, ItemStacks.deserialize(this.get("$prefix.inventory.slot$i") as ByteArray?))
    }
}
