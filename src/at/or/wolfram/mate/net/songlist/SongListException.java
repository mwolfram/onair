package at.or.wolfram.mate.net.songlist;

@SuppressWarnings("serial")
public class SongListException extends Exception {

	public SongListException(Throwable e) {
		super(e);
	}
	
	public SongListException(String message) {
		super(message);
	}
	
}
