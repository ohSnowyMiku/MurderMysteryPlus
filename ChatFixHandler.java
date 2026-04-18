package ohSnowyMiku.MurderMysteryPlus.other.font;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatFixHandler {
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        event.message = fixComponent(event.message);
    }

    private IChatComponent fixComponent(IChatComponent component) {
        // 先检查是否包含中文括号
        if (!needsFix(component)) {
            // 如果不需要修复，直接返回原组件，保留所有格式
            return component;
        }
        
        // 只在需要修复时才进行替换
        String text = component.getUnformattedTextForChat()
                .replace('（', '(')
                .replace('）', ')');

        // 创建新的组件并保留原样式
        ChatComponentText newComp = new ChatComponentText(text);
        ChatStyle style = component.getChatStyle();
        if (style != null) {
            newComp.setChatStyle(style.createShallowCopy());
        }

        // 递归处理子组件
        for (IChatComponent sibling : component.getSiblings()) {
            newComp.appendSibling(fixComponent(sibling));
        }

        return newComp;
    }

    private boolean needsFix(IChatComponent component) {
        // 检查当前组件的文本
        String text = component.getUnformattedTextForChat();
        if (text.contains("（") || text.contains("）")) {
            return true;
        }
        
        // 检查子组件
        for (IChatComponent sibling : component.getSiblings()) {
            if (needsFix(sibling)) {
                return true;
            }
        }
        
        return false;
    }
}