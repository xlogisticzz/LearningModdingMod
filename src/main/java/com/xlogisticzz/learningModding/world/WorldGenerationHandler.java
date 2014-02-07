package com.xlogisticzz.learningModding.world;

import com.xlogisticzz.learningModding.blocks.ModBlocks;
import com.xlogisticzz.learningModding.lib.Constants;
import com.xlogisticzz.learningModding.world.igloo.WorldGenIgloo;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WorldGenerationHandler implements IWorldGenerator {

    private WorldGenerator rubyGen;
    private WorldGenerator titaniumGen;

    public WorldGenerationHandler() {

        GameRegistry.registerWorldGenerator(this, 3);
        this.rubyGen = new WorldGenMinable(ModBlocks.ores.blockID, 0, Constants.WorldGen.RUBY_WORLD_GEN_AMOUNT, Block.stone.blockID);
        this.titaniumGen = new WorldGenMinable(ModBlocks.ores.blockID, 1, Constants.WorldGen.TITANIUM_WORLD_GEN_AMOUNT, Block.stone.blockID);
    }

    public void generateStandardOre(Random rand, int chunkX, int chunkZ, World world, int iterations, WorldGenerator gen, int lowestY, int highestY) {

        for (int i = 0; i < iterations; i++) {
            int x = chunkX * 16 + rand.nextInt(16);
            int y = rand.nextInt(highestY - lowestY) + lowestY;
            int z = chunkZ * 16 + rand.nextInt(16);

            gen.generate(world, rand, x, y, z);

        }
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

        switch (world.provider.dimensionId) {
            case -1:
                generateNether(world, random, chunkX, chunkZ);
            case 0:
                generateOverworld(world, random, chunkX, chunkZ);
        }
    }

    private void generateOverworld(World world, Random random, int chunkX, int chunkZ) {

        generateStandardOre(random, chunkX, chunkZ, world, Constants.WorldGen.RUBY_WORLD_GEN_ITERATIONS, this.rubyGen, Constants.WorldGen.RUBY_WORLD_GEN_LOWESTY, Constants.WorldGen.RUBY_WORLD_GEN_HIGHESTY);
        generateStandardOre(random, chunkX, chunkZ, world, Constants.WorldGen.TITANIUM_WORLD_GEN_ITERATIONS, this.titaniumGen, Constants.WorldGen.TITANIUM_WORLD_GEN_LOWESTY, Constants.WorldGen.TITANIUM_WORLD_GEN_HIGHESTY);

        for (int i = 0; i < 2; i++) {
            int iglooX = chunkX * 16 + random.nextInt(16);
            int iglooY = random.nextInt(70 - 50) + 50;
            int iglooZ = chunkZ * 16 + random.nextInt(16);

            new WorldGenIgloo().generate(world, random, iglooX, iglooY, iglooZ);
        }
    }

    private void generateNether(World world, Random random, int chunkX, int chunkZ) {

    }

}
