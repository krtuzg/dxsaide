
package net.dxriver.dxsaide.mixin;

import net.dxriver.dxsaide.function.MagmaKicksOutFunction;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(LocalPlayer.class)
public class MagmaKicksOutFunctionMixin {
    @Inject(at = @At("HEAD"), method = "hurt")
    public void dxsHurtMixin(DamageSource pSource, float pAmount, CallbackInfoReturnable<Boolean> cir) {
        MagmaKicksOutFunction.setDamageSource(pSource);
    }
    @Inject(at = @At("HEAD"), method = "hurtTo")
    public void dxsHurtToMixin(float pHealth, CallbackInfo ci){
        MagmaKicksOutFunction.magmaKicksOutJudge();
    }
}
