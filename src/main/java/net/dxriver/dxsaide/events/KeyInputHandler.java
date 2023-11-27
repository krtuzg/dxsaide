package net.dxriver.dxsaide.events;
import net.dxriver.dxsaide.Dxsaide;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Dxsaide.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class KeyInputHandler {
    public static boolean isShiftPressed =false;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        int modifiers = event.getModifiers();
        KeyInputHandler.isShiftPressed = (modifiers & GLFW.GLFW_MOD_SHIFT) != 0;
    }
}