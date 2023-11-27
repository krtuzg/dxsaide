package net.dxriver.dxsaide.function;

import net.dxriver.dxsaide.myconfig.ConfigManager;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.damagesource.DamageSource;

import java.util.Objects;

import static net.minecraft.world.damagesource.DamageSource.LAVA;

public class MagmaKicksOutFunction {
    private static DamageSource damageSource;

    public static Double getI() {
        return i;
    }

    public static void setI(Double i) {
        MagmaKicksOutFunction.i = i;
    }

    private static Double i = 0.0;

    public static DamageSource getDamageSource() {
        return damageSource;
    }

    public static void setDamageSource(DamageSource damageSource) {
        MagmaKicksOutFunction.damageSource = damageSource;
    }


    public static void magmaKicksOutJudge() {
        if (ConfigManager.getInstance().isMagmaKicksOutEnabled()) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null) {

                if (!player.getAbilities().invulnerable) {
                    if (damageSource == LAVA) {
                        MagmaKicksOutFunction.setI(MagmaKicksOutFunction.getI() + 1);
                        player.sendMessage(new TextComponent("岩浆第" + MagmaKicksOutFunction.getI() + "次对你造成伤害"), Util.NIL_UUID);

                        if (player.getHealth() < (float) ConfigManager.getInstance().getMagmaKicksOutWarning()) {
                            player.sendMessage(new TextComponent("你的生命值过低"), Util.NIL_UUID);
                        }
                        if (player.getHealth() < (float) ConfigManager.getInstance().getMagmaKicksOutMin()) {
                            Objects.requireNonNull(Minecraft.getInstance().getConnection()).getLevel().disconnect();
                        }
                    }
                }

            }

        }
    }
}
