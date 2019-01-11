package at.or.wolfram.mate.net.songlist;

import java.util.List;

public interface SongListProvider {

	public void refresh() throws SongListException;
	
	public List<Song> getSongList();
	
}
