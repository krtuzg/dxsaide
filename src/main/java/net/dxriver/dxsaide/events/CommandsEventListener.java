package net.dxriver.dxsaide.events;

import net.dxriver.dxsaide.Dxsaide;
import net.dxriver.dxsaide.commands.BruhCommand;
import net.dxriver.dxsaide.commands.DxsaideCommand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Dxsaide.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommandsEventListener {
    @SubscribeEvent
    public static void onRegisterClientCommands(RegisterClientCommandsEvent event) {
        BruhCommand.register(event.getDispatcher());
        DxsaideCommand.register(event.getDispatcher());
    }
}