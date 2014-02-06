package benjibobs.bouncer.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class SemiBouncerEvents {
	
	boolean semijump = false;
	
	@ForgeSubscribe
	public void semiBJump(LivingJumpEvent event){
		EntityPlayer player = null;
		if(event.entity instanceof EntityPlayer){
			player = (EntityPlayer)event.entity;
			int x = getFixedCoords(player.posX - 1);
			int y = getFixedCoords((player.posY - 2));
			int z = getFixedCoords(player.posZ);
			
			if(player.worldObj.getBlockId((int)x, (int)y, (int)z) == Bouncer.semiautotramp.blockID){
				player.motionY = 1;
				semijump = true;
			}
			
		}
	}
	
	@ForgeSubscribe
	public void sBCancel(LivingHurtEvent event){
		EntityPlayer player = null;
		if(event.entity instanceof EntityPlayer && event.source.equals(DamageSource.fall)){
			player = (EntityPlayer)event.entity;
			int x = getFixedCoords(player.posX - 1);
			int y = getFixedCoords((player.posY - 1));
			int z = getFixedCoords(player.posZ);
			if(semijump){
				event.setCanceled(true);
				event.ammount = 0.0F;
				player.setVelocity(0, 0, 0);
				semijump = false;
			}
			
			if(player.worldObj.getBlockId(x, y, z) == Bouncer.semiautotramp.blockID){
				player.motionY = 1;
				semijump = true;
			}
			
		}
	}
	
	@ForgeSubscribe
	public void sBCheck(LivingFallEvent event){
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.entity;
			if(semijump && event.distance <= 3){
				semijump = false;
			}
		}
	}
	
	public int getFixedCoords(double d){
		
		String fir = Double.toString(d);
		String[] sec = fir.split("\\.");
		int result = Integer.parseInt(sec[0]);
		
		return result;
	}

}
