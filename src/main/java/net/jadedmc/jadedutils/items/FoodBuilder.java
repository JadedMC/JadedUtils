package net.jadedmc.jadedutils.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.FoodComponent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Makes creating Food ItemStacks easier.
 */
public class FoodBuilder extends ItemBuilder {
    private final List<FoodComponent.FoodEffect> effects = new ArrayList<>();
    private boolean canAlwaysEat = false;
    private float eatSeconds = 0;
    private int nutrition = 0;
    private float saturation = 0;
    private ItemStack convertsTo = null;

    /**
     * Creates the Food ItemStack from a given ItemStack.
     * @param itemStack ItemStack to turn into food.
     */
    public FoodBuilder(@NotNull final ItemStack itemStack) {
        super(itemStack);
    }

    /**
     * Creates a Food ItemStack with a given material and amount.
     * @param material Material of the food ItemStack.
     * @param amount Amount of items in the stack.
     */
    public FoodBuilder(final Material material, final int amount) {
        super(new ItemStack(material, amount));
    }

    /**
     * Creates a Food ItemStack with a given material and a stack size of 1.
     * @param material Material of the food ItemStack.
     */
    public FoodBuilder(final Material material) {
        super(material, 1);
    }

    /**
     * Adds an effect to the food when it is consumed.
     * @param effect Effect to add to the food.
     * @return Food Builder.
     */
    public FoodBuilder addEffect(@NotNull final FoodComponent.FoodEffect effect) {
        this.effects.add(effect);
        return this;
    }

    /**
     * Allows the food to be eaten even if the player's hunger bar is full.
     * @return Food Builder.
     */
    public FoodBuilder canAlwaysEat() {
        return canAlwaysEat(true);
    }

    /**
     * Set if the food should be edible even when the player's hunger bar is full.
     * @param canAlwaysEat Whether it should still be edible.
     * @return Food Builder.
     */
    public FoodBuilder canAlwaysEat(final boolean canAlwaysEat) {
        this.canAlwaysEat = canAlwaysEat;
        return this;
    }

    /**
     * Set the ItemStack that the food should be converted to once consumed.
     * @param convertsTo ItemStack the food turns into.
     * @return Food Builder.
     */
    public FoodBuilder convertsTo(@NotNull final ItemStack convertsTo) {
        this.convertsTo = convertsTo;
        return this;
    }

    /**
     * Sets how many seconds it should take to consume the food.
     * @param eatSeconds Number of seconds it takes.
     * @return Food Builder.
     */
    public FoodBuilder setEatSeconds(final int eatSeconds) {
        this.eatSeconds = eatSeconds;
        return this;
    }

    /**
     * Sets the amount of nutrition the food should give when eaten.
     * @param nutrition Amount of hunger points the food should fill.
     * @return Food Builder.
     */
    public FoodBuilder setNutrition(final int nutrition) {
        this.nutrition = nutrition;
        return this;
    }

    /**
     * Sets the amount of saturation the food should give when eaten.
     * @param saturation Saturation that should be given.
     * @return Food Builder.
     */
    public FoodBuilder setSaturation(final int saturation) {
        this.saturation = saturation;
        return this;
    }

    /**
     * Makes creating Potion ItemStacks easier.
     */
    @Override
    public ItemStack build() {
        // Build's the parent ItemStack.
        final ItemStack itemStack = super.build();
        ItemMeta itemMeta = itemStack.getItemMeta();

        // Creates the FoodComponent.
        final FoodComponent foodComponent = itemStack.getItemMeta().getFood();
        foodComponent.setCanAlwaysEat(this.canAlwaysEat);
        foodComponent.setEatSeconds(this.eatSeconds);
        foodComponent.setNutrition(this.nutrition);
        foodComponent.setSaturation(this.saturation);
        foodComponent.setUsingConvertsTo(this.convertsTo);

        // Update the ItemMeta
        itemMeta.setFood(foodComponent);
        itemStack.setItemMeta(itemMeta);

        // Return the updated ItemStack.
        return itemStack;
    }
}