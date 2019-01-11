package at.or.wolfram.mate.android.onair;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import at.or.wolfram.mate.net.songlist.Song;
import at.or.wolfram.mate.net.songlist.SongListException;
import at.or.wolfram.mate.net.songlist.SongListProvider;
import at.or.wolfram.mate.net.songlist.soundportal.SoundportalSongListProvider;

public class OnairActivity extends ListActivity {

	private static final int NUM_SITES = 1;

	private ProgressDialog loadingDialog = null; 
	private Runnable loadSongs = null;
	private SongListProvider songListProvider;
	private List<Song> songs = new ArrayList<Song>();
	private SongAdapter songListAdapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		createSongListAdapter();
		
		//putNotification();

		loadSongs = new Runnable(){
			@Override
			public void run() {
				getSongs();
			}
		};
		Thread thread =  new Thread(null, loadSongs, "MagentoBackground");
		thread.start();
		loadingDialog = ProgressDialog.show(this, "Please wait...", "Retrieving data ...", true);
	}

	private Runnable returnRes = new Runnable() {
		@Override
		public void run() {
			songListAdapter.setSongs(songs);
			loadingDialog.dismiss();
			songListAdapter.notifyDataSetChanged();
		}
	};

	private void getSongs(){
		try	{
			songListProvider = new SoundportalSongListProvider(NUM_SITES);
			songListProvider.refresh();
			songs = songListProvider.getSongList();
		} catch (SongListException e) {
			Log.e("BACKGROUND_PROC", e.getMessage());
		}
		runOnUiThread(returnRes);
	}

	private void createSongListAdapter() {
		songListAdapter = new SongAdapter(this, R.layout.list_item, songs);
		setListAdapter(songListAdapter);
	}
	
	private void putNotification() {
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
		
		int icon = R.drawable.icon;
		CharSequence tickerText = "Hello";
		long when = System.currentTimeMillis();
		Notification notification = new Notification(icon, tickerText, when);
		
		// trying to alter flags
		notification.flags |= Notification.FLAG_INSISTENT;
		
		Context context = getApplicationContext();
		CharSequence contentTitle = "My notification";
		CharSequence contentText = "Hello World!";
		Intent notificationIntent = new Intent(this, OnairActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

		notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		
		mNotificationManager.notify(1/*HELLO_ID*/, notification);
		
	}
}