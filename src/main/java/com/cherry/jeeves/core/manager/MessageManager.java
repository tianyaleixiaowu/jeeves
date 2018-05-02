package com.cherry.jeeves.core.manager;

import com.cherry.jeeves.core.dao.MessageRepository;
import com.cherry.jeeves.core.model.MessageEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/5/1.
 */
@Service
public class MessageManager {
    @Resource
    private MessageRepository messageRepository;

    public void save(String nickName, String content) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setNickName(nickName);
        messageEntity.setContent(content);
        messageRepository.save(messageEntity);
    }
}
