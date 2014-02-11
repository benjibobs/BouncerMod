package benjibobs.bouncer.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;

public class BouncyBootsEvents {

	private static float fallam = 0;
	
	@ForgeSubscribe
	public void fallDmgCheck(LivingHurtEvent event){
		if(event.entity instanceof EntityPlayer && event.source.equals(DamageSource.fall)){
			EntityPlayer player = (EntityPlayer)event.entity;
			if(player.inventory.armorItemInSlot(0) != null){
				if(player.inventory.armorItemInSlot(0).itemID == Bouncer.bouncyboots.itemID){
					
					ItemStack pb = player.inventory.armorItemInSlot(0);
					int wbdur = pb.getItemDamage() + (int)fallam;
					int durc = pb.getMaxDamage() - pb.getItemDamage();
					if(wbdur < pb.getMaxDamage() && durc > 0){
						pb.setItemDamage(wbdur);
					
				}else{
					pb.setItemDamage(pb.getMaxDamage());
					player.inventory.clearInventory(pb.itemID, pb.getMaxDamage());
					
				}
				event.setCanceled(true);
				event.ammount = 0.0F;
			}
		}
	}
	
}
	
	@ForgeSubscribe
	public void bBootsFallCheck(LivingFallEvent event){
		if(event.entity instanceof EntityPlayer && event.distance > 3.0F){
			fallam = event.distance;
		}
	}
	
	@ForgeSubscribe
	public void bBootsJump(LivingJumpEvent event){
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.entity;
			if(player.inventory.armorItemInSlot(0) != null){
				if(player.inventory.armorItemInSlot(0).itemID == Bouncer.bouncyboots.itemID){
					//TODO: Add proper jump height
					player.motionY = 69;
				}
			}
		}
	}
}
