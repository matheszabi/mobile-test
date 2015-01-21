package com.goeuro.matheszabi.assignment.util;

import com.goeuro.matheszabi.assignment.FunnyActivity;
import com.matheszabi.util.HostAvailabilityTask;

/**
 * The other way how to call an Async Task, without override ( need more coding)
 * No funny names + free work => not fun!
 * 
 * @author matheszabi
 * 
 */
public class InternetConnectionChecker implements HostAvailabilityTask.Callback {

	public static void checkConnection(FunnyActivity funnyActivity) {
		InternetConnectionChecker internetConnectionChecker = new InternetConnectionChecker(funnyActivity);
		HostAvailabilityTask task = new HostAvailabilityTask(internetConnectionChecker);
		task.execute("http://www.google.com");
	}

	private FunnyActivity funnyActivity;

	public InternetConnectionChecker(FunnyActivity funnyActivity) {
		this.funnyActivity = funnyActivity;
	}

	@Override
	public void continueAfterCheck(boolean hostReached) {
		if (!hostReached) {
			funnyActivity.noInternetConnection();
		}
	}

}
