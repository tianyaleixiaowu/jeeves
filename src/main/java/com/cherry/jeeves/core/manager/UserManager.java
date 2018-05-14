package com.cherry.jeeves.core.manager;

import com.cherry.jeeves.core.bean.User;
import com.cherry.jeeves.core.cache.RoomUserCache;
import com.cherry.jeeves.domain.shared.ChatRoomMember;
import com.cherry.jeeves.domain.shared.Contact;
import com.cherry.jeeves.service.WechatHttpService;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wuweifeng wrote on 2018/5/1.
 */
@Service
public class UserManager {
    @Resource
    private RoomUserCache roomUserCache;
    @Resource
    private WechatHttpService wechatHttpService;

    public void dealRoomUser(String roomId) throws IOException {
        //获取系统宕机时的联系人
        fetchWolf();

        List<User> userList = roomUserCache.getUsers(roomId);
        if (userList == null) {
            refreshRoomUser(roomId);
        }
    }

    @Async
    public void fetchWolf() throws IOException {
        Set<Contact> contactSet = wechatHttpService.getContact();
        for (Contact contact : contactSet) {
            String nickName = contact.getNickName();
            if (nickName.contains("张磊")) {
                roomUserCache.setUserName(contact.getUserName());
                return;
            }
        }
    }

    public void refreshRoomUser(String roomId) throws IOException {
        roomUserCache.setUsers(roomId, fetchUsers(roomId));
    }

    public List<User> fetchUsers(String roomId) throws IOException {
        Set<String> names = new HashSet<>();
        names.add(roomId);
        List<User> userList = new ArrayList<>();
        Set<Contact> contactSet = wechatHttpService.batchGetContact(names);
        for (ChatRoomMember member : contactSet.iterator().next().getMemberList()) {
            User user = new User();
            BeanUtils.copyProperties(member, user);
            userList.add(user);
        }
        return userList;
    }

    public String findNicknameById(String roomId, String userId) throws IOException {
        List<User> userList = roomUserCache.getUsers(roomId);
        dealRoomUser(roomId);
        for (User user : userList) {
            if (user.getUserName().equals(userId)) {
                return user.getNickName();
            }
        }
        return "未知";
    }
}
