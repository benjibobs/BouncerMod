package benjibobs.bouncer.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class BlockSemiTramp extends Block {

	public BlockSemiTramp(int id, String texture) {
		super(id, Material.cloth);
		this.setCreativeTab(Bouncer.tabBouncer);
		
	}
	
	@SideOnly(Side.CLIENT)
	public static Icon topIcon;
	@SideOnly(Side.CLIENT)
	public static Icon bottomIcon;
	@SideOnly(Side.CLIENT)
	public static Icon sideIcon;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
	topIcon = icon.registerIcon(Bouncer.modid + ":" + "semibouncer_top");
	bottomIcon = icon.registerIcon(Bouncer.modid + ":" + "semibouncer_bottom");
	sideIcon = icon.registerIcon(Bouncer.modid + ":" + "semibouncer_side");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata) {
	if(side == 0) {
	return bottomIcon;
	} else if(side == 1) {
	return topIcon;
	} else {
	return sideIcon;
	}
	}
	
}
