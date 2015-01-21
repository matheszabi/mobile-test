package com.goeuro.matheszabi.assignment.listener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import com.goeuro.matheszabi.assignment.exception.ToMuchBeerConsumedException;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

/**
 * Takes a Button and a TextView as parameter. On the button chick it will bring a date chooser. If the user selects a date it will be formatter and displayed
 * at the TextView. The TextView it is not editable by the user.
 * 
 * The company who made this test assignment it is from Berlin, Germany. For that special attention has this class the prefix Octoberfest, which is a festival
 * in Germany and is a variable date.
 * 
 * @author matheszabi
 */
public class OktoberfestDateChooseHandler implements OnClickListener {

	private EditText etOktoberfestDate;
	private Button btChooseOktoberfestDate;

	// selected date
	private int selectedYear;
	private int selectedMonthOfYear;
	private int selectedDayOfMonth;

	/**
	 * Null values are not allowed! Later setUpParticipants() it will crash otherwise.
	 * 
	 * @param tvOktoberfestDate
	 * @param btChooseOktoberfestDate
	 */
	public OktoberfestDateChooseHandler(EditText etOktoberfestDate, Button btChooseOktoberfestDate) {
		this.etOktoberfestDate = etOktoberfestDate;
		this.btChooseOktoberfestDate = btChooseOktoberfestDate;
	}

	public void setUpParticipants() {
		// check if you are drunk already or not:
		if (etOktoberfestDate == null || btChooseOktoberfestDate == null) {
			Log.w(getClass().getSimpleName(), "Hey! You have consumed to much beer and to early. You can't participate in the Octoberfest festival setup! ");
			throw new ToMuchBeerConsumedException("tvOktoberfestDate or btChooseOktoberfestDate is null and shouldn't be!"); // let is crash, because you did a miserable
																													// xml or didn't pay attention to setup
		}

		btChooseOktoberfestDate.setOnClickListener(this);
		// tvOktoberfestDate.setEnabled(false);
		etOktoberfestDate.setKeyListener(null);
		
		// do click on the button:
		etOktoberfestDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				btChooseOktoberfestDate.performClick();

			}
		});

		// 2015: September 19 - October 4.
		// 2016: September 17 - October 3.
		// 2017: September 16 - October 3.

		selectedYear = 2015;
		selectedMonthOfYear = Calendar.SEPTEMBER;// 8
		selectedDayOfMonth = 19;

		refreshDate();
	}

	@Override
	public void onClick(View v) {
		// check if you are drunk already or not:
		if (v == null || btChooseOktoberfestDate == null || v.getId() != btChooseOktoberfestDate.getId()) {
			Log.w(getClass().getSimpleName(), "Hey! The Octoberfest is going very well! All are are drunk! Make a break now! :) ");
			return;
		}

		// pressed effect programmatically:
		btChooseOktoberfestDate.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
		btChooseOktoberfestDate.invalidate();

		// this is an Event Dispatching Thread, can't use Thread.sleep(), but postDelayed( runnable, delay) can be used
		long delayMillis = 200;
		new Handler().postDelayed(new Runnable() {
			public void run() {
				releaseButton();
			}
		}, delayMillis);

	}

	private void releaseButton() {
		btChooseOktoberfestDate.getBackground().clearColorFilter();
		btChooseOktoberfestDate.invalidate();

		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		// watch out!
		// There is a bug with DatePickerDialog in API, but I forgot in which version it was! - maybe previous Android 2.3
		// the stupid fix it was: create the dialog each time and do not make it available to instance variable!
		DatePickerDialog dialog = new DatePickerDialog(btChooseOktoberfestDate.getContext(), new OktoberfestDateSelectedListener(), year, month, day);
		dialog.show();
	}

	private void refreshDate() {
		// construct the selected Date object
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, selectedYear);
		c.set(Calendar.MONTH, selectedMonthOfYear);
		c.set(Calendar.DAY_OF_MONTH, selectedDayOfMonth);
		Date date = c.getTime();

		// Germans are using other date formatting then in the US, so lets use the Locale preferred formatting ( from android phone Settings)
		// at the specification the screen it shows a "dd.MM.yyyy" formatter. Which differs from my phone settings and from my mother language to
		// This implementation it is different now:
		// "A clean, well-animated, beautiful UI is very important. Please, let your imagination fly here."

		DateFormat dateFormat = DateFormat.getDateInstance();
		String formattedDate = dateFormat.format(date);

		etOktoberfestDate.setText(formattedDate);

		// uncomment and it will override it as in screenshot it shows:

		// DateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
		// tvOktoberfestDate.setText(simpleDateFormat.format(date));
	}

	private class OktoberfestDateSelectedListener implements OnDateSetListener {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			selectedYear = year;
			selectedMonthOfYear = monthOfYear;
			selectedDayOfMonth = dayOfMonth;
			refreshDate();
		}
	}
}
