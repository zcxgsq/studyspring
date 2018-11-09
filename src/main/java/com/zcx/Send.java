package com.zcx;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

/**
 * @author zcx
 * @Title 短信
 * @date 2018年11月07日 17:20
 **/
public class Send {
    private static final String awsKey = "AKIAIM2JYAXRKFJORTTA";
    private static final String awsSecret = "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";

    private static final BasicAWSCredentials credentials = new BasicAWSCredentials(awsKey, awsSecret);

    private static AmazonSNSClient snsClient = null;

    public static AmazonSNSClient getSnsClient(){
        if(snsClient==null){
            snsClient = new AmazonSNSClient(credentials).withRegion(Regions.AP_SOUTHEAST_1);
        }
        return snsClient;
    }

    public static void sendAmazonSMS(String phone, String content){
        PublishResult result = getSnsClient().publish(new PublishRequest().withMessage(content).withPhoneNumber(phone));

        System.out.println("Sent SMS message ID: " + result.getMessageId());
    }

    public static void main(String[] args){
        sendAmazonSMS("8618859803010","测试亚马逊验证码");
    }

}
