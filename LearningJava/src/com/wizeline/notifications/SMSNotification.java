package com.wizeline.notifications;

public class SMSNotification implements Notification {

	@Override
	public void notifyUser(String via) {
		System.out.println("Sending a SMS notification to " + via + "...");
	}

}