package com.zcx;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.GetSMSAttributesResult;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zcx
 * @Title AWS短信
 * @date 2018年11月07日 16:34
 **/
public class ShortMessage {
    private Map<String, MessageAttributeValue> smsAttributes;

    private String awsAppId = "AKIAIM2JYAXRKFJORTTA";

    private String awsAppKey = "3vzfF+XGB1CJ9YyXBMN4l8777W5k8AUQR+4wi4rm";

    public Map<String, MessageAttributeValue> getDefaultSMSAttributes() {
        if (smsAttributes == null) {
            smsAttributes = new HashMap<>();
            smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
                    .withStringValue("mySenderID")
                    .withDataType("String"));
            smsAttributes.put("AWS.SNS.SMS.MaxPrice", new MessageAttributeValue()
                    .withStringValue("0.050")
                    .withDataType("Number"));
//            smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
//                    .withStringValue("Promotional") //Sets the type to promotional.
//                    .withDataType("String"));
        }
        return smsAttributes;
    }

    public PublishResult sendSMSMessage(String phoneNumber, String message) {
        return sendSMSMessage(phoneNumber, message, getDefaultSMSAttributes());
    }

    public PublishResult sendSMSMessage(String phoneNumber, String message, Map<String, MessageAttributeValue> smsAttributes) {

        final AWSCredentials awsCredentials = new AWSCredentials() {
            @Override
            public String getAWSAccessKeyId() {
                return awsAppId; // 带有发短信权限的 IAM 的 ACCESS_KEY
            }

            @Override
            public String getAWSSecretKey() {
                return awsAppKey; // 带有发短信权限的 IAM 的 SECRET_KEY
            }
        };
        AWSCredentialsProvider provider = new AWSCredentialsProvider() {
            @Override
            public AWSCredentials getCredentials() {
                return awsCredentials;
            }

            @Override
            public void refresh() {
            }
        };
        AmazonSNS amazonSNS = null;
        try {
            amazonSNS = AmazonSNSClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                    .withRegion("ap-southeast-1")
                    .build();
//            amazonSNS = AmazonSNSClientBuilder.standard().withCredentials(provider).withRegion("us-east-1").build();
        } catch (Exception e) {

        }
        return amazonSNS.publish(
                new PublishRequest()
                        .withMessage(message)
                        .withPhoneNumber(phoneNumber)
                        .withMessageAttributes(smsAttributes));
    }


    public static void main(String[] args) {
        ShortMessage shortMessage = new ShortMessage();
//        PublishResult publishResult = shortMessage.sendSMSMessage("+8618859803010", "测试亚马逊验证码的福那是九年级发送到");
        System.out.println("auth code：1289".length());
        PublishResult publishResult = shortMessage.sendSMSMessage("+8618859803010", "auth code：1289");
        System.out.println(publishResult);
    }
}
