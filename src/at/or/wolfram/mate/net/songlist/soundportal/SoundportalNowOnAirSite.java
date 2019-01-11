package at.or.wolfram.mate.net.songlist.soundportal;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import at.or.wolfram.mate.net.songlist.Song;
import at.or.wolfram.mate.net.songlist.SongListException;

public class SoundportalNowOnAirSite {

	private static final String BASE_NOW_ON_AIR_URL = "http://soundportal.at/nc/service/now-on-air/?tx_abanowonair_pi1_pointer=";
	
	private List<Song> songs = new ArrayList<Song>();
	
	protected SoundportalNowOnAirSite() throws SongListException {
	}
	
	protected void refresh(int sitePointer) throws SongListException {
		try {
			songs.clear();
			Source soundportalSource = new Source(getURL(sitePointer));
			readDatum(soundportalSource);
			readTitel(soundportalSource);
			readInterpret(soundportalSource);
		} catch (Exception e) {
			throw new SongListException(e);
		}
	}
	
	protected List<Song> getSongs() {
		return songs;
	}
	
	private URL getURL(int sitePointer) throws MalformedURLException {
		return new URL(BASE_NOW_ON_AIR_URL + sitePointer);
	}
	
	private void readDatum(Source source) {
		int i = 0;
		for (Element element : source.getAllElementsByClass("datum")) {
			if (element.getName().equals("td")) {
				songs.add(new Song());
				songs.get(i).setDate(element.getContent().toString());
				++i;
			}
		}
	}
	
	private void readTitel(Source source) {
		int i = 0;
		for (Element element : source.getAllElementsByClass("titel")) {
			if (element.getName().equals("td")) {
				songs.get(i).setTitle(element.getContent().toString());
				++i;
			}
		}
	}
	
	private void readInterpret(Source source) {
		int i = 0;
		for (Element element : source.getAllElementsByClass("interpret")) {
			if (element.getName().equals("td")) {
				songs.get(i).setArtist(element.getContent().toString());
				++i;
			}
		}
	}
	
}
