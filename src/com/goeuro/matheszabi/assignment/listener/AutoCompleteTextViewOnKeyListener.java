package com.goeuro.matheszabi.assignment.listener;

import java.util.ArrayList;
import java.util.Collections;

import android.location.Location;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import com.goeuro.matheszabi.assignment.FunnyActivity;
import com.goeuro.matheszabi.assignment.R;
import com.goeuro.matheszabi.assignment.data.DataUsed;
import com.goeuro.matheszabi.assignment.data.DataUsedComparator;
import com.goeuro.matheszabi.assignment.exception.ToMuchBeerConsumedException;
import com.goeuro.matheszabi.assignment.util.DexterLaboratory;
import com.goeuro.matheszabi.assignment.util.ResultWriter;
import com.goeuro.matheszabi.assignment.util.WebserviceCallTask;

public class AutoCompleteTextViewOnKeyListener implements OnKeyListener {

	private FunnyActivity funnyActivity;

	/**
	 * @param funnyActivity
	 *            can't be null
	 */
	public AutoCompleteTextViewOnKeyListener(FunnyActivity funnyActivity) {
		if (funnyActivity == null) {
			throw new ToMuchBeerConsumedException("funnyActivity is null");
		}
		this.funnyActivity = funnyActivity;
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if (v == null) {
			return false;
		}

		// final int id = v.getId();/// let's store it who was

		if (v.getId() == R.id.actvFrom || v.getId() == R.id.actvTo) {
			final AutoCompleteTextView actv = (AutoCompleteTextView) v;
			String text = actv.getText().toString();

			if (text.length() < 2) {
				return false;
			}

			Spinner curSpinner = (v.getId() == R.id.actvFrom) ? funnyActivity.getSpFrom() : funnyActivity.getSpTo();
			String countryCode = (String) curSpinner.getSelectedItem();

			// TODO: check if we have it at the cache - after is implemented the "WriteToStorageTheResult"
			ArrayList<DataUsed> cachedResults = DexterLaboratory.getFromCache(funnyActivity, countryCode, text);
			
			if (cachedResults == null) {

				Log.d("AutoCompleteTextViewOnKeyListener", "countryCode: " + countryCode + ", text: " + text);

				WebserviceCallTask task = new WebserviceCallTask(countryCode, text) {

					@Override
					protected void onPostExecute(Void result) {
						super.onPostExecute(result);
						buildNewAdapter(listDataUsed, actv);
					}
				};
				
				task.setTaskDoneListener(new ResultWriter(funnyActivity));
				
				task.execute();
			} else { // use the cache:
				buildNewAdapter(cachedResults, actv);
			}

		}

		// return true; - if consumed
		return false;
	}

	private void buildNewAdapter(ArrayList<DataUsed> listDataUsed, AutoCompleteTextView actv) {
		if(listDataUsed == null){
			return;
		}
		Location myLocation = funnyActivity.getMyLocation();
		if (myLocation != null) {
			// need to sort the result by my location
			Log.d("AutoCompleteTextViewOnKeyListener", "sorting..." + listDataUsed.size() + " elements");
			// not a "search in Google and Paste the first result"!
			DataUsedComparator comparator = new DataUsedComparator(myLocation);
			// do the sort
			Collections.sort(listDataUsed, comparator);
		}
		// sorted or not: use it
		String[] locations = new String[listDataUsed.size()];
		for (int i = 0; i < listDataUsed.size(); i++) {
			DataUsed curDataUsed = listDataUsed.get(i);
			locations[i] = curDataUsed.getName() + " (" + curDataUsed.getCountry() + ")";
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(funnyActivity, android.R.layout.select_dialog_item, locations);
		actv.setAdapter(adapter);
		adapter.setNotifyOnChange(true);
	}
	

}
