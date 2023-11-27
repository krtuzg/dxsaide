package net.dxriver.dxsaide.myconfig;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class MyModConfig {
    public static class General {
        public final ForgeConfigSpec.BooleanValue enableMagmaKicksOut;
        public final ForgeConfigSpec.IntValue MagmaKicksOutWarning;
        public final ForgeConfigSpec.IntValue MagmaKicksOutMin;

        public General(ForgeConfigSpec.Builder builder) {
            builder.push("general");

            enableMagmaKicksOut = builder
                    .comment("Enable kicks out when player stands on magma blocks (default: true)")
                    .define("enableMagmaKicksOut", true);
            MagmaKicksOutWarning = builder
                    .comment("Number of seconds to display warning before player is kicked out (default: 5)")
                    .defineInRange("MagmaKicksOutWarning", 10, 1, 20);
            MagmaKicksOutMin = builder
                    .comment("Minimum number of magma blocks required for player to be kicked out (default: 3)")
                    .defineInRange("MagmaKicksOutMin", 10, 1, 20);

            builder.pop();
        }
    }

    public static final ForgeConfigSpec commonSpec;
    public static final General GENERAL;

    static {
        final Pair<General, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(General::new);
        commonSpec = specPair.getRight();
        GENERAL = specPair.getLeft();
    }
}