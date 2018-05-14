package com.cherry.jeeves.core.cache;

import com.cherry.jeeves.core.bean.User;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 缓存群聊内用户
 * @author wuweifeng wrote on 2018/5/1.
 */
@Component
public class RoomUserCache {

    @Resource
    private Cache cache;

    public List<User> getUsers(String roomId) {
        if (cache.get(roomId) == null) {
            return null;
        }
        return (List<User>) cache.get(roomId).get();
    }

    public String getUserName() {
         return (String) cache.get("username").get();
    }

    public void setUserName(String userName) {
        cache.put("username", userName);
    }

    public void setUsers(String roomId, List<User> users) {
        this.cache.put(roomId, users);
    }
}
