package com.xlogisticzz.learningModding.blocks;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.client.particles.Particles;
import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockHeightParticle extends Block {

    @SideOnly(Side.CLIENT)
    public IIcon ParticleIcon;

    public BlockHeightParticle() {
        super(Material.rock);
        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        setHardness(1F);
        setStepSound(soundTypeStone);
        setBlockName(Constants.UnLocalisedNames.HEIGHT_PARTICLE);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        blockIcon = par1IconRegister.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.HEIGHT_PARTICLE);
        ParticleIcon = par1IconRegister.registerIcon(Constants.Mod.MODID + ":particles/" + Constants.Particles.HEIGHT_TEXTURE);
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        float particleX = x + rand.nextFloat();
        float particleY = y + rand.nextFloat();
        float particleZ = z + rand.nextFloat();
        float particleMotionX = -0.5F + rand.nextFloat();
        float particleMotionY = rand.nextFloat();
        float particleMotionZ = -0.5F + rand.nextFloat();

        Particles.HEIGHT.spawnParticle(world, particleX, particleY, particleZ, particleMotionX, particleMotionY, particleMotionZ);
    }
}
