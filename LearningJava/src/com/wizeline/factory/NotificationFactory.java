package com.wizeline.factory;

import com.wizeline.notifications.EmailNotification;
import com.wizeline.notifications.Notification;
import com.wizeline.notifications.PushNotification;
import com.wizeline.notifications.SMSNotification;

public class NotificationFactory {

	public Notification createNotification(String channel, String via) {
        if (channel == null || channel.isEmpty())
            return null;
        switch (channel) {
            case "SMS":
                return new SMSNotification(via);
            case "EMAIL":
                return new EmailNotification(via);
            case "PUSH":
                return new PushNotification(via);
            default:
                throw new IllegalArgumentException("Unknown channel " + channel);
        }
    }
	
}
