package com.wizeline.notifications;

public class EmailNotification implements Notification {
	
	@Override
    public void notifyUser(String via) {
        System.out.println("Sending an Email notification to" + via + " ...");
    }
}