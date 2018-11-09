/**
 * Copyright (c) 2012 Conversant Solutions. All rights reserved.
 * <p/>
 * Created on 2016/8/26.
 */
package com.zcx;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Robin

 */
public class AwsSMSNotificationServiceImpl {

    protected final static Logger logger = LoggerFactory.getLogger(AwsSMSNotificationServiceImpl.class);

    private String awsAppId = "AKIAIM2JYAXRKFJORTTA";

    private String awsAppKey = "3vzfF+XGB1CJ9YyXBMN4l8777W5k8AUQR+4wi4rm";

    private Map<String, MessageAttributeValue> smsAttributes;

    private AmazonSNS amazonSNS;

    @PostConstruct
    public void init() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(awsAppId, awsAppKey);

        smsAttributes = new HashMap<String, MessageAttributeValue>();
        smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
                .withStringValue("2BLive") //The sender ID shown on the device.
                .withDataType("String"));
        smsAttributes.put("AWS.SNS.SMS.MaxPrice", new MessageAttributeValue()
                .withStringValue("0.50") //Sets the max price to 0.50 USD.
                .withDataType("Number"));
//        smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
//                .withStringValue("Transactional") //Sets the type to promotional.
//                .withDataType("String"));

        amazonSNS = AmazonSNSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion("ap-southeast-1")
                .build();
    }

        public void sendSMS(String message, String phoneNumber) {
        try {
            PublishResult result = amazonSNS.publish(new PublishRequest()
                    .withMessage(message)
                    .withPhoneNumber(phoneNumber)
                    .withMessageAttributes(smsAttributes));

            logger.info("Send SMS successfully, result : {}.", result);
        }
        catch (Exception e) {
            logger.error(message, phoneNumber, smsAttributes);
//            throw new ServiceRuntimeException(NotificationErrorCode.SEVER_ERROR);
        }
    }

    public static void main(String[] args) {
        AwsSMSNotificationServiceImpl awsSMSNotificationService = new AwsSMSNotificationServiceImpl();
        awsSMSNotificationService.sendSMS("This is a test sms message.", "+8618859803010");
    }
}
