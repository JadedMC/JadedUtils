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

import com.cryptomorin.xseries.profiles.builder.XSkull;
import com.cryptomorin.xseries.profiles.objects.ProfileInputType;
import com.cryptomorin.xseries.profiles.objects.Profileable;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Makes textured player heads easier to created.
 */
public class SkullBuilder {
    private ItemStack item;

    /**
     * Creates a SkullBuilder with an empty skull.
     */
    public SkullBuilder() {
        this.item = new ItemStack(Material.PLAYER_HEAD);
    }

    /**
     * Creates a Skull from it's Base64 encoded texture.
     * @param base64 Texture of the skull to use.
     * @return SkullBuilder.
     */
    public SkullBuilder fromBase64(@NotNull final String base64) {
        this.item = XSkull.createItem().profile(Profileable.of(ProfileInputType.BASE64, base64)).apply();
        return this;
    }

    /**
     * Creates a Skull from a player.
     * @param offlinePlayer OfflinePlayer to create the Skull of.
     * @return SkullBuilder.
     */
    public SkullBuilder fromPlayer(@NotNull final OfflinePlayer offlinePlayer) {
        this.item = XSkull.createItem().profile(Profileable.of(offlinePlayer)).apply();
        return this;
    }

    /**
     * Creates a Skull from the "CommonSkull" enum, which represents frequently used textures.
     * @param commonSkull CommonSkull to use.
     * @return  SkullBuilder.
     */
    public SkullBuilder fromSkull(final CommonSkull commonSkull) {
        return fromBase64(commonSkull.getTexture());
    }

    /**
     * Creates a Skull from a player's UUID.
     * @param uuid UUID of the player to create the skull from.
     * @return SkullBuilder.
     */
    public SkullBuilder fromUUID(@NotNull final UUID uuid) {
        this.item = XSkull.createItem().profile(Profileable.of(uuid)).apply();
        return this;
    }

    /**
     * Converts the builder to an ItemBuilder.
     * @return ItemBuilder.
     */
    public ItemBuilder asItemBuilder() {
        return new ItemBuilder(item);
    }
}