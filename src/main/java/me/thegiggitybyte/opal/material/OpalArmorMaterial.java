package me.thegiggitybyte.opal.material;

import me.thegiggitybyte.opal.Opal;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class OpalArmorMaterial implements ArmorMaterial {
    private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
    private static final int[] PROTECTION_VALUES = new int[]{5, 8, 10, 5};
    
    @Override
    public String getName() {
        return "opal";
    }
    
    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * 40;
    }
    
    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_VALUES[slot.getEntitySlotId()];
    }
    
    @Override
    public int getEnchantability() {
        return 14;
    }
    
    @Override
    public float getToughness() {
        return 3.0f;
    }
    
    @Override
    public float getKnockbackResistance() {
        return 0.1f;
    }
    
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Opal.Items.OPAL_INGOT);
    }
    
    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE;
    }
}
