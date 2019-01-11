package at.or.wolfram.mate.android.onair;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import at.or.wolfram.mate.net.songlist.Song;

public class SongAdapter extends ArrayAdapter<Song> {

	public SongAdapter(Context context, int textViewResourceId,	List<Song> objects) {
		super(context, textViewResourceId, objects);
	}
	
	public void setSongs(List<Song> songs) {
		clear();
		if(songs != null && songs.size() > 0){
			for (Song song : songs) {
				add(song);
			}
		}
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list_item, null);
            }
            Song song = getItem(position);
            if (song != null) {
                    TextView titleField = (TextView) v.findViewById(R.id.titleField);
                    TextView artistField = (TextView) v.findViewById(R.id.artistField);
                    TextView dateField = (TextView) v.findViewById(R.id.dateField);
                    if (titleField != null) {
                    	titleField.setText(song.getTitle());
                    }
                    if (artistField != null) {
                    	artistField.setText(song.getArtist());
                    }
                    if (dateField != null) {
                    	dateField.setText(song.getDate());
                    }
            }
            return v;
    }

}
