package com.xlogisticzz.learningModding.blocks;

import com.xlogisticzz.learningModding.LearningModdingCreativeTab;
import com.xlogisticzz.learningModding.client.particles.Particles;
import com.xlogisticzz.learningModding.lib.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Learning Modding Mod
 *
 * @author xLoGisTicZz. Some code may be from tutorials.
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockPoison extends Block {

    public BlockPoison() {
        super(Material.rock);

        setCreativeTab(LearningModdingCreativeTab.tabLearningModding);
        setHardness(1F);
        setStepSound(soundTypeStone);
        setBlockName(Constants.UnLocalisedNames.POISON);
    }

    @SideOnly(Side.CLIENT)
    public IIcon ParticleIcon;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {

        this.blockIcon = par1IconRegister.registerIcon(Constants.Mod.MODID + ":" + Constants.Icons.POISON);
        this.ParticleIcon = par1IconRegister.registerIcon(Constants.Mod.MODID + ":particles/" + Constants.Particles.POISON_TEXTURE);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        for (int i = 0; i < 4; i++) {
            float particleX = x + rand.nextFloat();
            float particleY = y + rand.nextFloat();
            float particleZ = z + rand.nextFloat();

            float particleMotionX = -0.5F + rand.nextFloat();
            float particleMotionY = -0.5F + rand.nextFloat();
            float particleMotionZ = -0.5F + rand.nextFloat();

            Particles.POISON.spawnParticle(world, particleX, particleY, particleZ, particleMotionX, particleMotionY, particleMotionZ);
        }
    }

    @Override
    public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {
        if (!par1World.isRemote) {
            par5EntityPlayer.addPotionEffect(new PotionEffect(Potion.poison.id, 40, 1));
        }
    }
}
