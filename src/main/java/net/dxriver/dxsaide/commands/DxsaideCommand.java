package net.dxriver.dxsaide.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.dxriver.dxsaide.myconfig.ConfigManager;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;

import java.util.concurrent.CompletableFuture;

public class DxsaideCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("dxsaide")
                        .then(Commands.literal("function")
                                .then(Commands.argument("function", StringArgumentType.string())
                                        .suggests(DxsaideCommand::functionSuggest)
                                        .then(Commands.argument("changer", StringArgumentType.string())
                                                .executes(DxsaideCommand::changerExecute)
                                                .suggests(DxsaideCommand::changerSuggest))))
                        .then(Commands.literal("set")
                                .then(Commands.argument("function", StringArgumentType.string())
                                        .suggests(DxsaideCommand::setterSuggest)
                                        .then(Commands.argument("int", IntegerArgumentType.integer())
                                                .executes(DxsaideCommand::setterExecute)
                                                )))

        );
    }

    private static int setterExecute(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getEntity();
        String function = StringArgumentType.getString(context, "function");
        int amount = IntegerArgumentType.getInteger(context, "int");
        if (function.equals("MagmaKicksOutWarning")) {
            ConfigManager.getInstance().setMagmaKicksOutWarning(amount);
            if (player != null) {
                player.sendMessage(new TextComponent("设置成功"), Util.NIL_UUID);
            }

        } else if (function.equals("MagmaKicksOutMin")) {
            ConfigManager.getInstance().setMagmaKicksOutMin(amount);
            if (player != null) {
                player.sendMessage(new TextComponent("设置成功"), Util.NIL_UUID);
            }
        }
        return 1;
    }

    private static int changerExecute(CommandContext<CommandSourceStack> context) {
//        int amount = IntegerArgumentType.getInteger(context, "function");
        // 在这里使用参数执行逻辑
        Player player = (Player) context.getSource().getEntity();

        String function = StringArgumentType.getString(context, "function");
        String changer = StringArgumentType.getString(context, "changer");
        if (function.equals("MagmaKicksOut")) {
            if (changer.equals("on")) {
                ConfigManager.getInstance().setMagmaKicksOutEnabled(true);
                if (player != null) {
                    player.sendMessage(new TextComponent("岩浆踢出功能已设置为" + ConfigManager.getInstance().isMagmaKicksOutEnabled()), Util.NIL_UUID);
                }
            } else if (changer.equals("off")) {
                ConfigManager.getInstance().setMagmaKicksOutEnabled(false);
                if (player != null) {
                    player.sendMessage(new TextComponent("岩浆踢出功能已设置为" + ConfigManager.getInstance().isMagmaKicksOutEnabled()), Util.NIL_UUID);
                }
            }
        } else if (function.equals("print")) {
            System.out.println(Minecraft.getInstance().player);
        }

        return 1; // 返回命令执行结果
    }

    private static CompletableFuture<Suggestions> functionSuggest(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) {
        // 在这里为补全建议提供选项
        builder.suggest("MagmaKicksOut");
        builder.suggest("print");

        return CompletableFuture.completedFuture(builder.build());
    }

    private static CompletableFuture<Suggestions> setterSuggest(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) {
        // 在这里为补全建议提供选项
        builder.suggest("MagmaKicksOutWarning");
        builder.suggest("MagmaKicksOutMin");

        return CompletableFuture.completedFuture(builder.build());
    }

    private static CompletableFuture<Suggestions> changerSuggest(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) {
        // 在这里为补全建议提供选项
        builder.suggest("on");
        builder.suggest("off");

        return CompletableFuture.completedFuture(builder.build());
    }
}
