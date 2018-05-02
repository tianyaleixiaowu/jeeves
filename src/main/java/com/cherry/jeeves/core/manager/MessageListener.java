package com.cherry.jeeves.core.manager;

import com.cherry.jeeves.core.util.Common;
import com.cherry.jeeves.domain.shared.Message;
import com.cherry.jeeves.utils.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 收到群聊消息后进行保存
 *
 * @author wuweifeng wrote on 2018/5/1.
 */
@Component
public class MessageListener {
    @Resource
    private UserManager userManager;
    @Resource
    private MessageManager messageManager;
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 收到新消息
     */
    public void messageReceive(Message message) throws IOException {
        String roomId = message.getFromUserName();
        //将room里的所有人都保存起来
        userManager.dealRoomUser(roomId);
        //发消息的人
        String msgSender = MessageUtils.getSenderOfChatRoomTextMessage(message.getContent());
        String senderNickname = userManager.findNicknameById(roomId, msgSender);
        //logger.info("to: " + message.getToUserName());
        String content = MessageUtils.getChatRoomTextMessageContent(message.getContent());
        try {
            if (Common.containsEmoji(senderNickname)) {
                senderNickname = Common.filterEmoji(senderNickname);
            }
            if (Common.containsEmoji(content)) {
                content = Common.filterEmoji(msgSender);
            }
            messageManager.save(senderNickname, content);
        } catch (Exception e) {
            logger.error("senderNickname: " + senderNickname + " \nmessage:" + content);
            e.printStackTrace();
        }
    }

    public void newMember(String roomId) throws IOException {
        userManager.dealRoomUser(roomId);
    }

}
