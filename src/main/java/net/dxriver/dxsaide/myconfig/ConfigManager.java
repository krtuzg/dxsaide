package net.dxriver.dxsaide.myconfig;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigManager {
    private static ConfigManager instance;
    private ForgeConfigSpec.BooleanValue enableMagmaKicksOut;

    public final ForgeConfigSpec.IntValue MagmaKicksOutWarning;
    public final ForgeConfigSpec.IntValue MagmaKicksOutMin;

    private ConfigManager() {
        // 在这里初始化配置项，并设置默认值
        enableMagmaKicksOut = MyModConfig.GENERAL.enableMagmaKicksOut;
        MagmaKicksOutWarning = MyModConfig.GENERAL.MagmaKicksOutWarning;
        MagmaKicksOutMin = MyModConfig.GENERAL.MagmaKicksOutMin;

    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    public boolean isMagmaKicksOutEnabled() {
        return enableMagmaKicksOut.get();
    }

    public void setMagmaKicksOutEnabled(boolean enabled) {
        enableMagmaKicksOut.set(enabled);
    }

    public int getMagmaKicksOutWarning() {
        return MagmaKicksOutWarning.get();
    }

    public void setMagmaKicksOutWarning(int magmaKicksOutWarning) {
        MagmaKicksOutWarning.set(magmaKicksOutWarning);
    }

    public int getMagmaKicksOutMin() {
        return MagmaKicksOutMin.get();
    }

    public void setMagmaKicksOutMin(int magmaKicksOutMin) {
        MagmaKicksOutMin.set(magmaKicksOutMin);
    }
}
