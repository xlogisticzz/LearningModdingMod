package com.xlogisticzz.learningModding.world.igloo;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WorldGenIgloo extends WorldGenerator {

    public List<Block> whitelist = getListOnArray(Blocks.grass, Blocks.dirt, Blocks.snow, Blocks.ice, Blocks.snow_layer);
    public int xDim = 12;
    public int yDim = 5;
    public int zDim = 11;
    public int solids = 80;
    public int nonsolids = 60;

    public List<Block> getListOnArray(Block... ints) {

        List<Block> intList = new ArrayList<Block>();
        for (Block i : ints) {
            intList.add(i);
        }
        return intList;
    }

    public boolean checkSpawn(World world, int x, int y, int z) {

        int solidBlocksInPlatform = 0, freeBlocksInSpace = 0;
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
        if (biome == BiomeGenBase.iceMountains || biome == BiomeGenBase.icePlains || biome == BiomeGenBase.taiga || biome == BiomeGenBase.frozenOcean || biome == BiomeGenBase.frozenRiver) {
            for (int i = 0; i < this.xDim; ++i) {
                for (int k = 0; k < this.zDim; ++k) {
                    if (world.getBlock(x + i, y - 1, z + k) != Blocks.air && this.whitelist.contains(world.getBlock(x + i, y - 1, z + k)) && !(world.getBlock(x + i, y - 1, z + k).getMaterial().isReplaceable())) {
                        solidBlocksInPlatform++;
                    }
                    for (int j = 0; j < this.yDim; ++j) {
                        if (world.isAirBlock(x + i, y + j, z + k) || world.getBlock(x + i, y + j, z + k).getMaterial().isReplaceable()) {
                            freeBlocksInSpace++;
                        }
                    }
                }
            }
        }
        return Math.round(freeBlocksInSpace / (this.xDim * this.zDim * this.yDim) * 100.0f) > this.solids && Math.round(solidBlocksInPlatform / this.xDim * this.zDim * 100.0f) > this.nonsolids;
    }

    @Override
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {

        if (checkSpawn(par1World, par3, par4, par5)) {
            new WorldGenIgloo1().generate(par1World, par2Random, par3, par4, par5);
            return true;
        } else {
            return false;
        }
    }
}
