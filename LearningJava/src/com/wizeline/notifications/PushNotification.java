package com.wizeline.notifications;

public class PushNotification implements Notification {

	@Override
    public void notifyUser(String via) {
        System.out.println("Sending a Push notification to" + via +" ...");
    }
	
}
