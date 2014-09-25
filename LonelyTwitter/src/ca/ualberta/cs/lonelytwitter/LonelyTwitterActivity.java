package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import ca.ualberta.cs.lonelytwitter.data.GsonDataManager;
import ca.ualberta.cs.lonelytwitter.data.IDataManager;

public class LonelyTwitterActivity extends Activity {

	private IDataManager dataManager;

	private EditText bodyText;

	private ListView oldTweetsList;

	private ArrayList<Tweet> tweets;

	private ArrayAdapter<Tweet> tweetsViewAdapter;
	
	private Summary mySummary = new Summary();
	
	private int numTweets = 0;
	private long averageTweets = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		dataManager = new GsonDataManager(this);

		bodyText = (EditText) findViewById(R.id.body);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);
	}

	@Override
	protected void onStart() {
		super.onStart();

		tweets = dataManager.loadTweets();
		tweetsViewAdapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweets);
		oldTweetsList.setAdapter(tweetsViewAdapter);
	}

	public void save(View v) {

		String text = bodyText.getText().toString();

		Tweet tweet = new Tweet(new Date(), text);
		tweets.add(tweet);

		tweetsViewAdapter.notifyDataSetChanged();

		bodyText.setText("");
		dataManager.saveTweets(tweets);
	}

	public void clear(View v) {

		tweets.clear();
		tweetsViewAdapter.notifyDataSetChanged();
		dataManager.saveTweets(tweets);
	}
	
	

	public void showSummary(View view) {
		
		createSummary();
	
		Log.d("counts", "**** SHOW num tweets " + numTweets);
	    Log.d("counts", "**** SHOW ave tweets " + averageTweets);
		
		Intent i = new Intent(this, Summary.class);
		
		i.putExtra("numTweets", Integer.toString(numTweets) );
		i.putExtra("averageTweets", Long.toString(averageTweets) );
		startActivity(i);
		
	}
	
	
	private void createSummary() {
		mySummary.setAveLength(averageTweets());
		mySummary.setNumTweets(countTweets());
		
		numTweets = countTweets();
		averageTweets = averageTweets();
		
	}	
	
	
	public int countTweets() {
		
		return tweets.size();
	}
	
	public long averageTweets() {
		
		int averageLength = 0;
		
		for (int i = 0; i < tweets.size(); i++) {
			averageLength += tweets.get(i).getTweetBody().length();
		}
		
		return averageLength/tweets.size();
	}
	
	

}