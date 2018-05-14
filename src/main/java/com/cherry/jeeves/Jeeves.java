package com.cherry.jeeves;

import com.cherry.jeeves.core.cache.RoomUserCache;
import com.cherry.jeeves.service.LoginService;
import com.cherry.jeeves.service.WechatHttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Component
public class Jeeves {
    @Autowired
    private LoginService loginService;
    @Value("${wechat.url.login}")
    private String url;
    @Value("${jeeves.instance-id}")
    private String instanceId;

    @Resource
    private WechatHttpService wechatHttpService;
    @Resource
    private RoomUserCache roomUserCache;

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @EventListener(ApplicationReadyEvent.class)
    public void start() throws Exception {
        try {
            logger.info("Jeeves starts");
            logger.info("Jeeves id = " + instanceId);
            System.setProperty("jsse.enableSNIExtension", "false");
            loginService.login();
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                wechatHttpService.sendText(roomUserCache.getUserName(), "系统发生故障，请及时重启");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(1);
        }
    }
}