package benjibobs.bouncer.common;

import java.util.List;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.server.FMLServerHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;

public class BouncerEvents {

	MinecraftServer mc = FMLServerHandler.instance().getServer();
	Minecraft mc1 = FMLClientHandler.instance().getClient();
	
	boolean manjump = false;

	@ForgeSubscribe
	public void manualBJump(LivingJumpEvent event){
		EntityPlayer player = null;
		
		if(!(event.entity instanceof EntityPlayer)){
			
		}else{
		player = (EntityPlayer)event.entity;
		int x = getFixedCoords(player.posX - 1);
		int y = getFixedCoords((player.posY - 2));
		int z = getFixedCoords(player.posZ);
		
		if(player.worldObj.getBlockId((int)x, (int)y, (int)z) == Bouncer.mantramp.blockID){
			player.motionY = 1;
			manjump = true;
		}
		
	}
	}
	
	@ForgeSubscribe
	public void mBCancel(LivingHurtEvent event){
		if(event.entity instanceof EntityPlayer && event.source.equals(DamageSource.fall)){
			
			EntityPlayer player = (EntityPlayer)event.entity;
			
			if(manjump){
				event.setCanceled(true);
				event.ammount = 0.0F;
				player.setVelocity(0, 0, 0);
				manjump = false;
			}
		}
	}
	
	@ForgeSubscribe
	public void mBCheck(LivingFallEvent event){
		if(event.entity instanceof EntityPlayer){
			if(manjump && event.distance <= 3){
				manjump = false;
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
