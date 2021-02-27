package me.thegiggitybyte.opal.material;

import me.thegiggitybyte.opal.Opal;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class OpalToolMaterial implements ToolMaterial {
    @Override
    public int getDurability() {
        return 1815;
    }
    
    @Override
    public float getAttackDamage() {
        return 3.0f;
    }
    
    @Override
    public int getMiningLevel() {
        return 3;
    }
    
    @Override
    public float getMiningSpeedMultiplier() {
        return 8.5f;
    }
    
    @Override
    public int getEnchantability() {
        return 14;
    }
    
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Opal.Items.OPAL_INGOT);
    }
}
