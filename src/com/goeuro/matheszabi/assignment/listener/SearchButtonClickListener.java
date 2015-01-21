package com.goeuro.matheszabi.assignment.listener;

import android.graphics.PorterDuff;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.goeuro.matheszabi.assignment.R;
import com.goeuro.matheszabi.assignment.exception.ToMuchBeerConsumedException;

public class SearchButtonClickListener implements OnClickListener {

	private ImageView ivGirls;

	/**
	 * call it with param null, if you don't want to fade out with pressing the Search Button
	 * 
	 * @param ivGirls
	 */
	public SearchButtonClickListener(ImageView ivGirls) {
		this.ivGirls = ivGirls;
	}

	@Override
	public void onClick(final View v) {
		if (v == null) {
			throw new ToMuchBeerConsumedException("The Unit Tester or the Developer is already drunk!");
		}
		// set an overlay, creating the "pressed" - not need another resource from the designer

		v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
		v.invalidate();

		if (ivGirls != null) {
			Animation myFadeOutAnimation = AnimationUtils.loadAnimation(v.getContext(), R.anim.fade_out);
			ivGirls.startAnimation(myFadeOutAnimation);
			ivGirls.setVisibility(View.INVISIBLE);
		}

		// this is an Event Dispatching Thread, can't use Thread.sleep(), but postDelayed( runnable, delay) can be used
		long delayMillis = 200;
		new Handler().postDelayed(new Runnable() {
			public void run() {
				releaseButton(v);
			}
		}, delayMillis);
	}

	private void releaseButton(final View button) {
		button.getBackground().clearColorFilter();
		button.invalidate();

		// do the search logic:
		// By specification:
		// Tapping the "search" button should display a "Search is not yet implemented" message to the user.
		// can be a Toast message or a dialog.
		// a Custom dialog it is more complex to implement ( with custom layout )

		Toast toast = Toast.makeText(button.getContext(), "Search is not yet implemented", Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.show();

		Toast.makeText(button.getContext(), "Anyway, Oktoberfest it is a touris trap.\n" + //
				"You need like 100 euro for food and beer per day\n" + //
				"+ costs for distraction, girls, whatever\n" + //
				"+ Hotel\n" + //
				"+ trasportation\n" + //
				"\nTake 300 euro per day for a minimum living!\n" + "Or save the money and buy a used car for that festival price!", Toast.LENGTH_LONG).show();

		if (ivGirls != null) {// put back the girls after a time elapsed:

			long delayMillis = 6000;
			new Handler().postDelayed(new Runnable() {
				public void run() {
					Animation myFadeInAnimation = AnimationUtils.loadAnimation(button.getContext(), R.anim.fade_in);
					ivGirls.startAnimation(myFadeInAnimation); // Set animation to your ImageView
					ivGirls.setVisibility(View.VISIBLE);
				}
			}, delayMillis);
		}
	}

}
