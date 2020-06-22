package com.hs.config;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-04-06 13:04
 **/
import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016102400754358";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQD7uZWvfdU6fMp6babhkxh//Vf/ZprAV1548BpW1+qgI2CtCPOoZV9TKOQ5qjQGHuR+YT7U/vd/7vRCac3oUAVbY9Oa6Z8KyoILLOk4H6u9PRaSjSFPlTVD/7aY8dmpl+TaD31JL/ZbVuPFYzNwYk4gmjkeVtXPcXp8A00xuLi5Ps1kyuDyUhZ7dRm5vLbVDxXKGLUuVI+dl6L9XQUOVnJgWzYdDcnnfXU7+yTJtWNqRfYtTrKSwIzFlmFeRYZzbY2+6HLpSUF4CrV/umAtjcLpz9RxekZcJL7kzIpzTxPuNIGCRxbDr4q3BUEfwyJgUW04Zc6d2gAq8AA764NTh6zJAgMBAAECggEAZdXZgYqFhcjQXSEZHy7T/YtvWyqWNnPiC0kPTszA5ZIKXVY+xIRmQQ1M7ys1Kri6tYn/0klntUDlXizRmUcuM5+6DnSu2727SGwd2Y+uebtJ83XW4Qw65UyBAXTuGpcRhUo45C/cGW2YbdVJDREoDDZ4HxcAHpEI/TyUsSXKzV2WcejRGXHAGzkBvZ/J2HJRkak+MuneCIHeXqBp+0P9HUcDCK5OPz174h1US6ckDv3lM9XlLPMyv+3CtwrcES5Ola0oQQyb2V/CV5r0X+H3rPKLyr3heo0WfOGKlNXndTRi4tGS8gbnfBNUDtLOAahXfSXUecMXr1HZgkchO6LeEQKBgQD/blUSp4vdeuR/GD58uXib2qU5SOo2+OQzaom5oLz1m7g7QX26vGKXZio5EObMfDs4VIOVe2GQMAmmZZkWzY3Jb3GCJ06ZB05+wko+ty5MRtn4lXzt4sddpT3FxrJdw+f/zBVBB6MNy4EEhLLO1LzRSCAGnHEVFlvt856PmHXnnQKBgQD8SSOPGmG2N4Ed4i5GFBfrcAcGBMHJy1CVI4vTzrdeL6Ydb/7gsz4PZsipSBetB8PZFofXuga/JOR3BstSzmA6TXB9vNzraXzTyRRw4bEqACUT0hYK5/lsn3eFzYEVfou7H0XGrPVeD42e6IhFNjilu/v1N5eAn3qyszOhSwswHQKBgQC6vMalJKgz+ywhXnAQbuelyWsRPiU8myj6DeEyPDjotjcx2uc4JZFObfpgTeLti8UfoWmxVso0x2nGpHAPI6eMpFSvel0TsyvHr/LndrP6P7IK8f6rSyfFZpCKjoDH2/k4INNI/3RNGYHw87JlU1HXDoubSbPCXt5GT9yTzjokYQKBgQCtOHKn4Q9SE44dvJzVFRrPVt4brcx5B5ZOsFdjmL2id8jw0WMJWUi1Pndsc8l9zqUUOzoOTvYu69/KMeoM8xJzNZSVYMW3BXjl2VylnPENy4epskfcOSazBFMGlfGZlofOdUxte3gFm1w7zaEzFGWUthvrZL4rXFTcudorVuFZqQKBgQCZ2g2Tzrd37yKN5hQTj0v0fTrh+VTFnCXsDqPhvLcEXHZphZAvDtPhhFY+msN22djcb43C8FnH9Wrcj/s8QCMgkF4k9vC5zHZlDsDc9lrGxS5Mf2F/M4kT2FrMpts6ql3XHt/2XvFSKt3rJoEWzHSBGIN43lOXzbicE1ror8MW2w==";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmJ5acnt9yfA7lMKkoQbkDdi+XJZQbvx7cbwiFhvnURMHznLIDRtGtK2KeqJjCptTTTrfScMFvBaEQ/jkt487vzYoi6QQkTb78GC8VjVL6nlBJRJZqYf4AMpqsOp7iM8d+5J2RJuCej+5tDmKSw+ycut03QsJ1DM0TdILAwTLH0xXFWs1GlvlcROVcFtMyOiHmVC7IY/5HVRfYck/a6rbwp4DgFcgZP+kB1lYpWwvZIj1P5erXvrf0hctZo+41tmq0zKnJwWQr8C7JtKfJS+vJLxXTDJx38rOmrAltoUfn5hJHHVR3pcBFUllOv9EX9pGw4Cds8tGc9KOD4e+mZQuJwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/returnPay/aliReturnPay";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/returnPay/aliReturnPay";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    //日志
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
