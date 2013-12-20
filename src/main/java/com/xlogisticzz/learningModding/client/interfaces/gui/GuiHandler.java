package com.xlogisticzz.learningModding.client.interfaces.gui;
/*
* @author xLoGisTicZz
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*/

import com.xlogisticzz.learningModding.LearningModding;
import com.xlogisticzz.learningModding.client.interfaces.contaners.ContainerMachine;
import com.xlogisticzz.learningModding.tileEntites.TileEntityMachine;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    public GuiHandler() {
        NetworkRegistry.instance().registerGuiHandler(LearningModding.instance, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch(ID){
            case  0 :
                TileEntity te = world.getBlockTileEntity(x, y, z);
                if(te != null && te instanceof TileEntityMachine){
                    return  new ContainerMachine(player.inventory, (TileEntityMachine) te);
                }
                break;

        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch(ID){
            case  0 :
                TileEntity te = world.getBlockTileEntity(x, y, z);
                if(te != null && te instanceof TileEntityMachine){
                    return  new GuiMachine(player.inventory, (TileEntityMachine) te);
                }
                break;

        }
        return null;
    }
}
