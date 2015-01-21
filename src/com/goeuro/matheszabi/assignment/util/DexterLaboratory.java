package com.goeuro.matheszabi.assignment.util;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.goeuro.matheszabi.assignment.data.DataUsed;
import com.matheszabi.util.CountryCodesEu;

public class DexterLaboratory {

	// you forget to call it, you LAZY programmer! :)
	static {
		// it is called only once, so everything is fine
		transformCountryCodesForTheLazyProgrammer();
	}

	@SuppressLint("DefaultLocale")
	private static void transformCountryCodesForTheLazyProgrammer() {// shouldn't be accessible from outside
		for (int i = 0; i < CountryCodesEu.countryCodes.length; i++) {
			CountryCodesEu.countryCodes[i] = CountryCodesEu.countryCodes[i].toLowerCase();
		}
	}

	public static void createAndSetAdapter(Context context, Spinner spinner) {

		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, CountryCodesEu.countryCodes);
		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
		spinner.setAdapter(spinnerArrayAdapter);

	}

	public static void selectCountryCodeInSpinner(Spinner spinner, String countryCode) {

		int position = -1;
		// lets find what need to be selected

		@SuppressWarnings("unchecked")
		ArrayAdapter<String> adapter = (ArrayAdapter<String>) spinner.getAdapter();
		int count = adapter.getCount();
		for (int i = 0; i < count; i++) {
			String curItem = adapter.getItem(i);
			if(curItem.equalsIgnoreCase(countryCode)){
				position = i;
				break;
			}
		}

		if (position == -1) {
			return;// couldn't find it
		}

		// this is not working in an Android version, but forgot which one: it's an Android bug.

		// make backup:
		OnItemSelectedListener onItemSelectedListener = spinner.getOnItemSelectedListener();
		// remove
		spinner.setOnItemSelectedListener(null);
		// make changes:
		spinner.setSelection(position);
		// restore backup:
		spinner.setOnItemSelectedListener(onItemSelectedListener);

	}

	public static ArrayList<DataUsed> getFromCache(Context context, String countryCode, String text) {
		// TODO Implements this, I don't have time now
		// add the write to storage permission
		// write to to external storage the data  - or the internal storage
		return null;
	}

}
