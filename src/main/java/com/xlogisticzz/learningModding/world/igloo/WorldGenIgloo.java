package com.xlogisticzz.learningModding.world.igloo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

/**
 * Learning Modding Mod
 * 
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WorldGenIgloo extends WorldGenerator {
    
    public List<Integer> whitelist = getListOnArray(2, 3, 80, 79, 78);
    public int xDim = 12;
    public int yDim = 5;
    public int zDim = 11;
    public int solids = 80;
    public int nonsolids = 60;
    
    public List<Integer> getListOnArray(int ... ints) {
    
        List<Integer> intList = new ArrayList<Integer>();
        for (int i : ints){
            intList.add(i);
        }
        return intList;
    }
    
    public boolean checkSpawn(World world, int x, int y, int z) {
    
        int solidBlocksInPlatform = 0, freeBlocksInSpace = 0;
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
        if (biome == BiomeGenBase.iceMountains || biome == BiomeGenBase.icePlains || biome == BiomeGenBase.taiga || biome == BiomeGenBase.frozenOcean || biome == BiomeGenBase.frozenRiver){
            for (int i = 0; i < this.xDim; ++i){
                for (int k = 0; k < this.zDim; ++k){
                    if (world.getBlockId(x + i, y - 1, z + k) != 0 && this.whitelist.contains(world.getBlockId(x + i, y - 1, z + k)) && !Block.blocksList[world.getBlockId(x + i, y - 1, z + k)].blockMaterial.isReplaceable()){
                        solidBlocksInPlatform++;
                    }
                    for (int j = 0; j < this.yDim; ++j){
                        if (world.isAirBlock(x + i, y + j, z + k) || Block.blocksList[world.getBlockId(x + i, y + j, z + k)].blockMaterial.isReplaceable()){
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
    
        if (checkSpawn(par1World, par3, par4, par5)){
            new WorldGenIgloo1().generate(par1World, par2Random, par3, par4, par5);
            return true;
        }else{
            return false;
        }
    }
}
