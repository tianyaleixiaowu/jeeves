package com.cherry.jeeves.core.dao;

import com.cherry.jeeves.core.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wuweifeng wrote on 2018/5/1.
 */
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}
