package com.rogrand.enterprisehome.modules.pay;
/** 
 * 微信支付配置文件 
 * 2018/10/30
 * @author shaoxiao.dong 
 * 
 */  
public class WeChatConfig {

	 /**
     * 微信开发平台应用ID
     */
    public static final String APP_ID="wx0a3242424aae13c6";
    /**
     * 应用对应的凭证
     */
    public static final String APP_SECRET="1d29d733ewrewrfwed1b58968cce";
    /**
     * 应用对应的密钥
     */
    public static final String APP_KEY="dfsfdvdfvgk32423423oGdfsfdsvBO66";
    /**
     * 微信支付商户号
     */
    public static final String MCH_ID="1469dfsf02";
    /**
     * 商品描述
     */
    public static final String BODY="QQ游戏-账户充值";
    /**
     * 商户号对应的密钥
     */
    public static final String PARTNER_key="123466";
    
    /**
     * 商户id
     */
    public static final String PARTNER_ID="14698sdfs402dsfdew402";
    /**
     * 常量固定值
     */
    public static final String GRANT_TYPE="client_credential";
    /**
     * 获取预支付id的接口url
     */
    public static String UFDODER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * 微信服务器回调通知url
     */
    public static String WECHAT_NOTIFY_URL_PC="http://www.bairuoheng.cn/app/tenpay/notify";

}
