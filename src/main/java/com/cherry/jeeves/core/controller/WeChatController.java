package com.cherry.jeeves.core.controller;

import com.cherry.jeeves.domain.shared.Contact;
import com.cherry.jeeves.service.WechatHttpService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wuweifeng wrote on 2018/4/30.
 */
@RestController
public class WeChatController {
    @Resource
    private WechatHttpService wechatHttpService;

    //@RequestMapping("user")
    //public Object getAllUser() throws IOException {
    //    List<User> users = new ArrayList<>();
    //    Set<Contact> contactSet = wechatHttpService.getContact();
    //    for (Contact contact : contactSet) {
    //        User user = new User();
    //        BeanUtils.copyProperties(contact, user);
    //        users.add(user);
    //    }
    //    return users;
    //}

    @RequestMapping("room")
    public Object getAllRoom() throws IOException {
        Set<Contact> contactSet = wechatHttpService.getContact();
        return contactSet;
    }

    @RequestMapping("user")
    public Object getUserByRoom(String room) throws IOException {
        Set<String> names = new HashSet<>();
        names.add(room);
        Set<Contact> contactSet = wechatHttpService.batchGetContact(names);
        return contactSet;
    }
}
