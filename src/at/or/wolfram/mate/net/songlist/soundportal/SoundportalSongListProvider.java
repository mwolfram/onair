package at.or.wolfram.mate.net.songlist.soundportal;

import java.util.ArrayList;
import java.util.List;

import at.or.wolfram.mate.net.songlist.Song;
import at.or.wolfram.mate.net.songlist.SongListException;
import at.or.wolfram.mate.net.songlist.SongListProvider;

public class SoundportalSongListProvider implements SongListProvider {

	private List<Song> songs = new ArrayList<Song>();
	private SoundportalNowOnAirSite nowOnAirSite;
	private int numSites;
	
	public SoundportalSongListProvider(int numSites) throws SongListException {
		this.nowOnAirSite = new SoundportalNowOnAirSite();
		this.numSites = numSites;
	}
	
	@Override
	public void refresh() throws SongListException {
		songs.clear();
		for (int i = 0; i < numSites; ++i) {
			nowOnAirSite.refresh(i);
			songs.addAll(nowOnAirSite.getSongs());
		}
	}
	
	@Override
	public List<Song> getSongList() {
		return songs;
	}
	
}
