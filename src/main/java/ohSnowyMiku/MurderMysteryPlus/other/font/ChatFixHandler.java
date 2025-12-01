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
        // 替换全角括号为特殊占位符（这里用半角括号占位）
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
}
