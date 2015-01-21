package com.goeuro.matheszabi.assignment.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;

import com.goeuro.matheszabi.assignment.FunnyActivity;

public class MyTextWatcher implements TextWatcher{
	
	private FunnyActivity funnyActivity;
	private AutoCompleteTextView actv;

	public MyTextWatcher(FunnyActivity funnyActivity, AutoCompleteTextView actv) {
		this.actv = actv;
		this.funnyActivity = funnyActivity;
	}


	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

	@Override
	public void afterTextChanged(Editable s) {
		AutoCompleteTextViewOnKeyListener autoCompleteTextViewOnKeyListener = funnyActivity.getAutoCompleteTextViewOnKeyListener();
		// TODO extract the functionality to be able to call it here nicelly
		autoCompleteTextViewOnKeyListener.onKey(actv, 0, null);
	}
}


