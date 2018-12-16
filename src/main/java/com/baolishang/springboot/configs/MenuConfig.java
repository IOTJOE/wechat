package com.baolishang.springboot.configs;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

/**
 * Created by FirenzesEagle on 2016/6/1 0001.
 * Email:liumingbo2008@gmail.com
 */
public class MenuConfig {

    /**
     * 定义菜单结构
     *
     * @return
     */
    protected static WxMenu getMenu() {

        MainConfig mainConfig = new MainConfig();
        WxMpService wxMpService = mainConfig.wxMpService();

        WxMenu menu = new WxMenu();
        WxMenuButton button1 = new WxMenuButton();
        button1.setType(WxConsts.BUTTON_VIEW);
        button1.setName("我要投资");
        button1.setUrl(wxMpService.oauth2buildAuthorizationUrl("https://www.baojinsuo.com", "snsapi_base", "123"));
        System.out.println(button1.getUrl());
        WxMenuButton button2 = new WxMenuButton();
        button2.setName("我的帐户");

        WxMenuButton button21 = new WxMenuButton();
        button21.setType(WxConsts.BUTTON_VIEW);
        button21.setName("账户概览");
        button21.setUrl(wxMpService.oauth2buildAuthorizationUrl("https://www.baojinsuo.com", "snsapi_base", ""));

        WxMenuButton button22 = new WxMenuButton();
        button22.setType(WxConsts.BUTTON_VIEW);
        button22.setName("订阅通知");
        button22.setUrl(wxMpService.oauth2buildAuthorizationUrl("https://www.baojinsuo.com", "snsapi_base", ""));

        WxMenuButton button23 = new WxMenuButton();
        button23.setType(WxConsts.BUTTON_VIEW);
        button23.setName("绑定帐号");
        button23.setUrl(wxMpService.oauth2buildAuthorizationUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx931dc45ba9c45973&redirect_uri=http%3A%2F%2Fspringboot.baojinsuo.cn/wxuser/login&response_type=code&scope=snsapi_base&state=123#wechat_redirect", "snsapi_base", ""));

//        WxMenuButton button23 = new WxMenuButton();
//        button23.setType(WxConsts.BUTTON_VIEW);
//        button23.setName("发布商品");
//        button23.setUrl(wxMpService.oauth2buildAuthorizationUrl("", "snsapi_base", ""));
//
//        WxMenuButton button24 = new WxMenuButton();
//        button24.setType(WxConsts.BUTTON_VIEW);
//        button24.setName("商品管理");
//        button24.setUrl(wxMpService.oauth2buildAuthorizationUrl("", "snsapi_base", ""));

        button2.getSubButtons().add(button21);
        button2.getSubButtons().add(button22);
        button2.getSubButtons().add(button23);
//        button2.getSubButtons().add(button23);
//        button2.getSubButtons().add(button24);

        WxMenuButton button3 = new WxMenuButton();
        button3.setType(WxConsts.BUTTON_VIEW);
        button3.setName("使用帮助");
        button3.setKey("help");

        menu.getButtons().add(button1);
        menu.getButtons().add(button2);
        menu.getButtons().add(button3);

        return menu;
    }

    /**
     * 运行此main函数即可生成公众号自定义菜单
     *
     * @param args
     */
    public static void main(String[] args) {
        MainConfig mainConfig = new MainConfig();
        mainConfig.setAppid("wx931dc45ba9c45973");
        mainConfig.setAppsecret("666d271531987121a447da3b79c0b111");
        mainConfig.setAesKey("XAVFzHSsj8MjOxSPxxR4zI0tOvheW0QRi7FDT6YEaFV");
        mainConfig.setToken("bjswx");
        WxMpService wxMpService = mainConfig.wxMpService();
        try {
            wxMpService.getMenuService().menuCreate(getMenu());
//            wxMpService.getMenuService().menuDelete();
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

}
