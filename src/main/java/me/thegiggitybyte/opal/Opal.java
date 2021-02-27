package me.thegiggitybyte.opal;

import me.thegiggitybyte.opal.item.OpalAxeItem;
import me.thegiggitybyte.opal.item.OpalPickaxeItem;
import me.thegiggitybyte.opal.material.OpalArmorMaterial;
import me.thegiggitybyte.opal.material.OpalToolMaterial;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class Opal implements ModInitializer {
    public static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier("opal", "items"), () -> new ItemStack(Opal.Items.OPAL_INGOT));
    
    @Override
    public void onInitialize() {
        // Register items
        Registry.register(Registry.ITEM, new Identifier("opal", "opal_ore"), Opal.Items.OPAL_ORE);
        Registry.register(Registry.ITEM, new Identifier("opal", "opal_dust"), Opal.Items.OPAL_DUST);
        Registry.register(Registry.ITEM, new Identifier("opal", "opal_ingot"), Opal.Items.OPAL_INGOT);
        Registry.register(Registry.ITEM, new Identifier("opal", "opal_block"), Opal.Items.OPAL_BLOCK);
        
        // Register blocks
        Registry.register(Registry.BLOCK, new Identifier("opal", "opal_ore"), Opal.Blocks.OPAL_ORE);
        Registry.register(Registry.BLOCK, new Identifier("opal", "opal_block"), Opal.Blocks.OPAL_BLOCK);
    
        // Register armor
        Registry.register(Registry.ITEM, new Identifier("opal", "opal_helmet"), Opal.Items.OPAL_HELMET);
        Registry.register(Registry.ITEM, new Identifier("opal", "opal_chestplate"), Opal.Items.OPAL_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("opal", "opal_leggings"), Opal.Items.OPAL_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("opal", "opal_boots"), Opal.Items.OPAL_BOOTS);
    
        // Register tools
        Registry.register(Registry.ITEM, new Identifier("opal", "opal_sword"), Opal.Items.OPAL_SWORD);
        Registry.register(Registry.ITEM, new Identifier("opal", "opal_pickaxe"), Opal.Items.OPAL_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("opal", "opal_axe"), Opal.Items.OPAL_AXE);
        Registry.register(Registry.ITEM, new Identifier("opal", "opal_shovel"), Opal.Items.OPAL_SHOVEL);
        
        // Overworld ore generation
        registerOreGeneration();
    }
    
    private void registerOreGeneration() {
        ConfiguredFeature<?, ?> oreGenFeature = Feature.ORE
                .configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, Opal.Blocks.OPAL_ORE.getDefaultState(), 3)) // vein size
                .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(5, 0, 18))) // bottom offset, top offset, y value
                .applyChance(1)
                .spreadHorizontally()
                .repeat(1); // number of veins per chunk
        
        RegistryKey<ConfiguredFeature<?, ?>> featureKey = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier("opal", "ore_opal_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, featureKey.getValue(), oreGenFeature);
        
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, featureKey);
    }
    
    public static class Blocks {
        public static final Block OPAL_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f, 3.0f).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
        public static final Block OPAL_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(3.0f, 3.0f));
    }
    
    public static class Items {
        public static final Item OPAL_DUST = new Item(new FabricItemSettings().group(Opal.GROUP));
        public static final Item OPAL_INGOT = new Item(new FabricItemSettings().group(Opal.GROUP));
        
        public static final Item OPAL_HELMET = new ArmorItem(Opal.Materials.OPAL_ARMOR, EquipmentSlot.HEAD, new Item.Settings().group(Opal.GROUP));
        public static final Item OPAL_CHESTPLATE = new ArmorItem(Opal.Materials.OPAL_ARMOR, EquipmentSlot.CHEST, new Item.Settings().group(Opal.GROUP));
        public static final Item OPAL_LEGGINGS = new ArmorItem(Opal.Materials.OPAL_ARMOR, EquipmentSlot.LEGS, new Item.Settings().group(Opal.GROUP));
        public static final Item OPAL_BOOTS = new ArmorItem(Opal.Materials.OPAL_ARMOR, EquipmentSlot.FEET, new Item.Settings().group(Opal.GROUP));
        
        public static final Item OPAL_SWORD = new SwordItem(Opal.Materials.OPAL_TOOL, 5, -2.2f, new Item.Settings().group(Opal.GROUP));
        public static final Item OPAL_PICKAXE = new OpalPickaxeItem(Opal.Materials.OPAL_TOOL, 3, -2.5f, new Item.Settings().group(Opal.GROUP));
        public static final Item OPAL_AXE = new OpalAxeItem(Opal.Materials.OPAL_TOOL, 5, -2.5f, new Item.Settings().group(Opal.GROUP));
        public static final Item OPAL_SHOVEL = new ShovelItem(Opal.Materials.OPAL_TOOL, 3, -2.5f, new Item.Settings().group(Opal.GROUP));
        
        public static final Item OPAL_ORE = new BlockItem(Opal.Blocks.OPAL_ORE, new Item.Settings().group(Opal.GROUP));
        public static final Item OPAL_BLOCK = new BlockItem(Opal.Blocks.OPAL_BLOCK, new Item.Settings().group(Opal.GROUP));
    }
    
    public static class Materials {
        public static final OpalArmorMaterial OPAL_ARMOR = new OpalArmorMaterial();
        public static final OpalToolMaterial OPAL_TOOL = new OpalToolMaterial();
    }
}
