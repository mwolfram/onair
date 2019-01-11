package at.or.wolfram.mate.net.songlist;

public class Song {

	private String date;
	private String title;
	private String artist;
	
	public Song() {
		super();
	}
	public Song(String date, String title, String artist) {
		super();
		this.date = date;
		this.title = title;
		this.artist = artist;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	@Override
	public String toString() {
		return "" + title + " - " + artist;
	}
	
	
	
}
