package benjibobs.bouncer.common;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemBBoots extends ItemArmor{

	public ItemBBoots(int id, EnumArmorMaterial MaterialEnum,
			int matid, int type) {
		super(id, MaterialEnum, matid, type);
		this.setCreativeTab(Bouncer.tabBouncer);
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(Bouncer.modid + ":" + "bouncyboots");

	}

	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			int layer) {
			if (stack.itemID == Bouncer.bouncyboots.itemID
			) {
			return "bbouncer:textures/models/armor/bouncyboots.png";
			}
			else {
			return null;
			}
	
	
	

}
}
