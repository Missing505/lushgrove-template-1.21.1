package net.missing.lushgrove.block;

import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.missing.lushgrove.LushGrove;
import net.missing.lushgrove.block.custom.VoidpepperCropBlock;

import static net.minecraft.block.Blocks.register;

public class ModBlocks {

    public static final Block ANEMONE_HARMONY = registerBlock("anemone_harmony",
            new FlowerBlock(StatusEffects.FIRE_RESISTANCE, 10,
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.PURPLE)
                            .noCollision()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.GRASS)
                            .offset(AbstractBlock.OffsetType.XZ)
                            .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block ANEMONE_HARMONY_POT = register("anemone_harmony_pot",
            Blocks.createFlowerPotBlock(ANEMONE_HARMONY));

    public static final Block BRAMBLE = registerBlock(
            "bramble",
            new DeadBushBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.GREEN)
                            .replaceable()
                            .noCollision()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.GRASS)
                            .burnable()
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );

    public static final Block VOIDPEPPER_CROP = registerBlockWithoutItem("voidpepper_crop",
            new VoidpepperCropBlock(AbstractBlock.Settings.create()
                    .noCollision()
                    .ticksRandomly()
                    .sounds(BlockSoundGroup.CROP)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(LushGrove.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(LushGrove.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(LushGrove.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        LushGrove.LOGGER.info("Registering Mod Blocks for " + LushGrove.MOD_ID);
    }
}
