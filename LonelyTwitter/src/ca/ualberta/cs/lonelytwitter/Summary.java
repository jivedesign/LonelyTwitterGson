package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.List;

import ca.ualberta.cs.lonelytwitter.data.IDataManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Summary extends Activity {
	
	private int numTweets ;
	private long aveLength ;
	
	private String numTweets_string;
	private String aveTweets_string;
	
	private TextView numTweets_view;
	private TextView aveTweets_view;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.summary_view);
	    
	    
	
	    Intent i = getIntent();
	    
	    numTweets_string = i.getStringExtra("numTweets");
	    aveTweets_string = i.getStringExtra("averageTweets");
	    
	    
	    displaySummary();
	    
	}
	
	public void displaySummary() {
		
		numTweets_view = (TextView) findViewById(R.id.num_tweets_count);
	    aveTweets_view = (TextView) findViewById(R.id.length_tweets_count);
	    
	    Log.d("counts", "*num tweets " + numTweets_string);
	    Log.d("counts", "*ave tweets " + aveTweets_string);
	    
	    numTweets_view.setText(numTweets_string);
	    aveTweets_view.setText(aveTweets_string);
	    
	}
	

	public int getNumTweets() {
		return numTweets;
	}

	public void setNumTweets(int numTweets) {
		this.numTweets = numTweets;
	}

	public double getAveLength() {
		return aveLength;
	}

	public void setAveLength(long aveLength) {
		this.aveLength = aveLength;
	}

	
	
	
	
	

}
