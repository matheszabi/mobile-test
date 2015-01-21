package com.goeuro.matheszabi.assignment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.goeuro.matheszabi.assignment.listener.AutoCompleteTextViewOnKeyListener;
import com.goeuro.matheszabi.assignment.listener.MyTextWatcher;
import com.goeuro.matheszabi.assignment.listener.OktoberfestDateChooseHandler;
import com.goeuro.matheszabi.assignment.listener.SearchButtonClickListener;
import com.goeuro.matheszabi.assignment.util.CristopherColumbus;
import com.goeuro.matheszabi.assignment.util.DexterLaboratory;
import com.goeuro.matheszabi.assignment.util.InternetConnectionChecker;

/**
 * The "MainActivity" holding the few components, which are required by specifications
 * 
 * @author matheszabi
 * 
 */
public class FunnyActivity extends Activity {

	// retrieved from LocationManager.NETWORK_PROVIDER
	private Location myLocation;

	// retrieved from http://maps.googleapis.com/maps/api/geocode
	private String countryCode;
	private String city;

	boolean usedMyLoacation;

	// ui references
	private AutoCompleteTextView actvFrom;
	private Spinner spFrom;
	private AutoCompleteTextView actvTo;
	private Spinner spTo;
	
	//
	private AutoCompleteTextViewOnKeyListener autoCompleteTextViewOnKeyListener;
	private MyTextWatcher fromTextWatcher;
	private MyTextWatcher toTextWatcher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.funny);
		
		//When displaying matches, they should be ordered by distance to the user's current location.
		actvFrom = (AutoCompleteTextView) findViewById(R.id.actvFrom);
		spFrom = (Spinner) findViewById(R.id.spFrom);
		actvTo = (AutoCompleteTextView) findViewById(R.id.actvTo);
		spTo = (Spinner) findViewById(R.id.spTo);
		
		autoCompleteTextViewOnKeyListener = new AutoCompleteTextViewOnKeyListener(this);
		
		// the values are the same, but make sure the adapter object it is different!!!
		DexterLaboratory.createAndSetAdapter(this, spFrom);
		DexterLaboratory.createAndSetAdapter(this, spTo);
		
		// the websevice is working on with min 2 characters
		actvFrom.setThreshold(2);
		actvTo.setThreshold(2);
		
		actvFrom.setOnKeyListener(autoCompleteTextViewOnKeyListener);// if you press the Back button
		actvTo.setOnKeyListener(autoCompleteTextViewOnKeyListener);
		
		fromTextWatcher = new MyTextWatcher(this, actvFrom);
		toTextWatcher = new MyTextWatcher(this, actvTo);		
		actvFrom.addTextChangedListener(fromTextWatcher);// if you type a letter
		actvTo.addTextChangedListener(toTextWatcher);
		
		
		EditText etOktoberfestDate = (EditText) findViewById(R.id.etOktoberfestDate);
		Button btChooseOktoberfestDate = (Button) findViewById(R.id.btChooseOktoberfestDate);
		

		OktoberfestDateChooseHandler oktoberfestDateChooseHandler = new OktoberfestDateChooseHandler(etOktoberfestDate, btChooseOktoberfestDate);
		oktoberfestDateChooseHandler.setUpParticipants(); // :)

		Button btSearch = (Button) findViewById(R.id.btSearch);
		btSearch.setOnClickListener(new SearchButtonClickListener((ImageView) findViewById(R.id.ivGirls)));
		
		setDestinationOctiberfestLocation();
	}

	private void setDestinationOctiberfestLocation() {
		DexterLaboratory.selectCountryCodeInSpinner(spTo, "de");
		actvTo.setText("München");		
		
		// set the From Country too to "de", because that company it is at Berlin, let they be happy with default DE settings
		// and maybe you are going to Octoberfest from Germany.
		DexterLaboratory.selectCountryCodeInSpinner(spFrom, "de");
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		InternetConnectionChecker.checkConnection(this);
		
		if(!usedMyLoacation){
			requestLocationUpdate();
		}

		// fade in the image: - animation with alpha + scaling
		ImageView myImageView = (ImageView) findViewById(R.id.ivGirls);
		Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		myImageView.startAnimation(myFadeInAnimation); // Set animation to your ImageView
		myImageView.setVisibility(View.VISIBLE);

	}

	private void requestLocationUpdate() {

		CristopherColumbus drunkDriver = new CristopherColumbus();
		drunkDriver.findWhereAmI(this);

	}

	public void setMyLocationString(String countryCode, String city) {
		this.countryCode = countryCode;
		this.city = city;
		
		if(!usedMyLoacation){
			actvFrom.setText(city);			
			
			DexterLaboratory.selectCountryCodeInSpinner(spFrom, countryCode);
						
			usedMyLoacation = true;
		}
	}

	public void noInternetConnection() {
		new AlertDialog.Builder(this)
	    .setTitle("Problems, problems everywhere!")
	    .setMessage("You are in a BIG trouble now!\n" +//
	    		"You don't have Internet connection.\n" +//
	    		"I can't use any API, I can't help you.\n" +//
	    		"Now or you turn on the Internet and move out from beer tent if is 3G and try again\n" +
	    		"\n" +
	    		"Or shut down this app and order a new beer and give a kiss to next girl!\n" +
	    		"\n" +
	    		"The choise is up to you! :)")
	    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	        	dialog.dismiss();
	        }
	     })
	    .setIcon(android.R.drawable.ic_dialog_info)
	     .show();
	}

	public Spinner getSpFrom() {
		return spFrom;
	}

	public Spinner getSpTo() {
		return spTo;
	}

	public Location getMyLocation() {
		return myLocation;
	}

	public void setMyLocation(Location myLocation) {
		this.myLocation = myLocation;
	}

	public AutoCompleteTextViewOnKeyListener getAutoCompleteTextViewOnKeyListener() {
		return autoCompleteTextViewOnKeyListener;
	}
		

}
