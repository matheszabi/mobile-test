package com.goeuro.matheszabi.assignment.util;

import java.util.ArrayList;

import android.content.Context;

import com.goeuro.matheszabi.assignment.data.DataUsed;
import com.goeuro.matheszabi.assignment.util.WebserviceCallTask.TaskDoneListener;

public class ResultWriter implements TaskDoneListener {

	private Context context;
	
	public ResultWriter(Context context){
		this.context = context;
	}
	@Override
	public void taskDone(WebserviceCallTask task) {
		ArrayList<DataUsed> result = task.getResult();
		String countryCode = task.getCountryCode();
		String term = task.getTerm();
		
		
		
		
		// TODO: write it to the storage with context.getSharedPreferences(name, mode)
		
		// one of the best methods it would be to write to:
		// <expernalStorage>/goeuro/countryCode/term/filename.dat
		
		// need to escape the "term" because maybe you can't create a filename with that
	}

}
