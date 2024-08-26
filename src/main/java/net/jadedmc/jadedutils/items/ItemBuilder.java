/*
 * This file is part of JadedUtils, licensed under the MIT License.
 *
 *  Copyright (c) JadedMC
 *  Copyright (c) contributors
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
package net.jadedmc.jadedutils.items;

import com.cryptomorin.xseries.XMaterial;
import net.jadedmc.jadedutils.chat.ChatUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.FoodComponent;
import org.bukkit.inventory.meta.components.JukeboxPlayableComponent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Makes ItemStacks easier to create.
 */
@SuppressWarnings("unused")
public class ItemBuilder {
    private ItemStack itemStack;
    private ItemMeta itemMeta;

    /**
     * Creates an item builder using a base ItemStack.
     * @param itemStack Starting ItemStack.
     */
    public ItemBuilder(@NotNull final ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    /**
     * Creates an item builder using a specific material and item count.
     * @param material Material to use.
     * @param amount Number of items.
     */
    public ItemBuilder(final Material material, final int amount) {
        this(new ItemStack(material, amount));
    }

    /**
     * Creates an item builder using a base material.
     * @param material Material to use.
     */
    public ItemBuilder(final Material material) {
        this(material, 1);
    }

    /**
     * Creates an item builder using a specific XMaterial and item count.
     * @param xMaterial XMaterial to use.
     * @param amount Number of items.
     */
    public ItemBuilder(final XMaterial xMaterial, final int amount) {
        this.itemStack = xMaterial.parseItem();
        this.itemStack.setAmount(amount);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    /**
     * Creates an item builder using a specific XMaterial.
     * @param xMaterial XMaterial to use.
     */
    public ItemBuilder(final XMaterial xMaterial) {
        this(xMaterial, 1);
    }

    /**
     * Adds an attribute to the item.
     * @param attribute Attribute to add.
     * @param attributeModifier Attribute modifier.
     * @return ItemBuilder.
     */
    public ItemBuilder addAttributeModifier(final Attribute attribute, final AttributeModifier attributeModifier) {
        this.itemMeta.addAttributeModifier(attribute, attributeModifier);
        return this;
    }

    /**
     * Adds an enchantment to the item.
     * @param enchantment Enchantment to add.
     * @param level Level of the enchantment.
     * @param ignore Whether the enchantment should ignore the enchantment cap.
     * @return ItemBuilder.
     */
    public ItemBuilder addEnchantment(final Enchantment enchantment, final int level, final boolean ignore) {
        this.itemMeta.addEnchant(enchantment, level, ignore);
        return this;
    }

    /**
     * Adds an enchantment to the item.
     * @param enchantment Enchantment to add.
     * @param level Level of the enchantment.
     * @return ItemBuilder.
     */
    public ItemBuilder addEnchantment(final Enchantment enchantment, int level) {
        return this.addEnchantment(enchantment, level, true);
    }

    /**
     * Add an ItemFlag to the item.
     * @param itemFlag ItemFlag to add.
     * @return ItemBuilder.
     */
    public ItemBuilder addFlag(final ItemFlag itemFlag) {
        this.itemStack.addItemFlags(itemFlag);
        return this;
    }

    /**
     * Add multiple ItemFlags to the item.
     * @param itemFlags ItemFlags to add.
     * @return ItemBuilder.
     */
    public ItemBuilder addFlags(final ItemFlag... itemFlags) {
        this.itemStack.addItemFlags(itemFlags);
        return this;
    }

    /**
     * Adds a line to the lore of the item.
     * @param component Adventure Component to add to the lore.
     * @return ItemBuilder.
     */
    public ItemBuilder addLore(@NotNull final Component component) {
        final List<Component> lore = new ArrayList<>(this.itemMeta.lore());
        lore.add(component);
        this.itemMeta.lore(lore);
        return this;
    }

    /**
     * Adds a line to the lore of the item.
     * Automatically translates with MiniMessage and legacy text to a component.
     * @param string String to add to the lore.
     * @return ItemBuilder.
     */
    public ItemBuilder addLore(@NotNull final String string) {
        return addLore(ChatUtils.translate(string));
    }

    /**
     * Creates the ItemStack.
     * @return Built ItemStack.
     */
    public ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack;
    }

    /**
     * Set the number of items in the ItemStack.
     * @param amount New Number of items.
     * @return ItemBuilder.
     */
    public ItemBuilder setAmount(final int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    /**
     * Change the CustomModelData of the item.
     * @param customModelData New CustomModelData.
     * @return ItemBuilder.
     */
    public ItemBuilder setCustomModelData(final int customModelData) {
        this.itemMeta.setCustomModelData(customModelData);
        return this;
    }

    /**
     * Sets the item display name with a Component.
     * @param displayName Component to use for the display name.
     * @return ItemBuilder.
     */
    public ItemBuilder setDisplayName(@NotNull final Component displayName) {
        this.itemMeta.displayName(displayName);
        return this;
    }

    /**
     * Sets the item display name with a String.
     * Gets translated to a Component with MiniMessage and legacy formatting codes.
     * @param displayName String to use for the display name.
     * @return ItemBuilder.
     */
    public ItemBuilder setDisplayName(@NotNull final String displayName) {
        return this.setDisplayName(ChatUtils.translate(displayName));
    }

    /**
     * Set if the item should be resistant to fire.
     * @param fireResistant true if fire-resistant, false if not.
     * @return ItemBuilder.
     */
    public ItemBuilder setFireResistant(final boolean fireResistant) {
        this.itemMeta.setFireResistant(fireResistant);
        return this;
    }

    /**
     * Set the food component of the Item.
     * @param foodComponent FoodComponent.
     * @return ItemBuilder.
     */
    public ItemBuilder setFood(@NotNull final FoodComponent foodComponent) {
        this.itemMeta.setFood(foodComponent);
        return this;
    }

    /**
     * Replaces the ItemStack being used in the builder.
     * Probably doing something wrong if you are using this, but you can I guess.
     * @param itemStack New ItemStack to use.
     * @return ItemBuilder.
     */
    public ItemBuilder setItemStack(@NotNull final ItemStack itemStack) {
        this.itemStack = itemStack;
        return this;
    }

    /**
     * Replaces the ItemMeta being used in the builder.
     * Probably doing something wrong if you are using this.
     * @param itemMeta New ItemMeta to use.
     * @return ItemBuilder.
     */
    public ItemBuilder setItemMeta(@NotNull final ItemMeta itemMeta) {
        this.itemMeta = itemMeta;
        return this;
    }

    /**
     * Set the JukeboxPlayableComponent of the item.
     * @param jukeboxPlayableComponent JukeboxPlayableComponent.
     * @return ItemBuilder.
     */
    public ItemBuilder setJukeboxPlayable(@NotNull final JukeboxPlayableComponent jukeboxPlayableComponent) {
        this.itemMeta.setJukeboxPlayable(jukeboxPlayableComponent);
        return this;
    }

    /**
     * Sets the max stack size of the item.
     * @param maxStackSize Maximum size of the stack.
     * @return ItemBuilder.
     */
    public ItemBuilder setMaxStackSize(final int maxStackSize) {
        this.itemMeta.setMaxStackSize(maxStackSize);
        return this;
    }

    /**
     * Adds persistent data to the item.
     * @param plugin Plugin being used.
     * @param key Key for the data.
     * @param value value for the data.
     * @return ItemBuilder.
     */
    public ItemBuilder setPersistentData(@NotNull final Plugin plugin, final String key, final String value) {
        final NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        this.itemMeta.getPersistentDataContainer().set(namespacedKey, PersistentDataType.STRING, value);
        return this;
    }

    /**
     * Adds persistent data to the item.
     * @param plugin Plugin being used.
     * @param key Key for the data.
     * @param value value for the data.
     * @return ItemBuilder.
     */
    public ItemBuilder setPersistentData(@NotNull final Plugin plugin, final String key, final int value) {
        final NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        this.itemMeta.getPersistentDataContainer().set(namespacedKey, PersistentDataType.INTEGER, value);
        return this;
    }

    /**
     * Sets the rarity of the item.
     * @param itemRarity New Item Rarity.
     * @return ItemBuilder.
     */
    public ItemBuilder setRarity(final ItemRarity itemRarity) {
        this.itemMeta.setRarity(itemRarity);
        return this;
    }

    /**
     * Set if the item should be unbreakable.
     * Items are unbreakable by default, so should only really be set to true.
     * @param unbreakable Whether the item should be unbreakable.
     * @return ItemBuilder.
     */
    public ItemBuilder setUnbreakable(final boolean unbreakable) {
        this.itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    /**
     * Replaces the XMaterial being used in the builder.
     * @param xMaterial New XMaterial.
     * @return ItemBuilder.
     */
    public ItemBuilder setXMaterial(final XMaterial xMaterial) {
        this.itemStack = xMaterial.parseItem();
        this.itemMeta = itemStack.getItemMeta();
        return this;
    }
}