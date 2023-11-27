package net.dxriver.dxsaide.mixin;

import com.mojang.blaze3d.platform.InputConstants;
import net.dxriver.dxsaide.events.KeyInputHandler;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(Player.class)
public abstract class JueFunctionMixin {
    @Shadow private ItemStack lastItemInMainHand;

    @Shadow public abstract Component getName();

    @Inject(at = @At("TAIL"), method = "tick")
    public void dxsHurtMixin(CallbackInfo ci) {
        String key="key.keyboard.left.shift";
        if(this.getName().getString().equals("Dx_River")&&this.lastItemInMainHand.getItem()==Items.STICK){

            if (Minecraft.getInstance().player != null) {
                KeyMapping.set(InputConstants.getKey(key),true);
            }
        }else if(!KeyInputHandler.isShiftPressed){
            KeyMapping.set(InputConstants.getKey(key),false);
        }
    }
}
