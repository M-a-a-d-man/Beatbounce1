import java.util.ArrayList;

public class Song {
	
	// Attributes
	private String name;
	private String path;
	private int bpm;
	private int startTime;
	
	// Song that is currently playing
	private static Song playingNow = null;
	
	// Static ArrayList that will contain all songs as elements
	public static ArrayList<Song> songs = new ArrayList<Song>();
	
	
	// 4-parameter constructor
	// There is no default constructor active currently
	public Song(String name, String path, int bpm, int startTime){
		this.name = name;
		this.path = path;
		this.bpm = bpm;
		this.startTime = startTime;
		songs.add(this);
	}
	
	// Public static method that returns a song using its BPM as a search tool. Returns any song in the list with the closest BPM.
	public static Song findSong(int bpm) {
		Song chosenSong = null;
		int difference = Integer.MAX_VALUE;
		for(int i = 0; i < songs.size(); i++) {
			Song checkingSong = songs.get(i);
			if(Math.abs(checkingSong.bpm-bpm) < difference) {
				chosenSong = songs.get(i);
				difference = Math.abs(checkingSong.bpm-bpm);
			}
		}
		return chosenSong;
	}
	
	// Public static method that returns a song using its name as a search tool. Returns null if the name is not exactly the same.
	public static Song findSong(String name) {
		Song chosenSong = null;
		for(int i = 0; i < songs.size(); i++) {
			Song checkingSong = songs.get(i);
			if(checkingSong.name.equals(name)) {
				chosenSong = checkingSong;
				break;
			}
		}
		return chosenSong;
	}
	
	// Get methods for all attributes
	public String getName() {
		return this.name;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public int getBpm() {
		return this.bpm;
	}
	
	public int getStartTime() {
		return this.startTime;
	}
	
	// getter/setter for playingNow
	public static void setPlayingNow(Song song) {
		playingNow = song;
	}
	
	public static Song getPlayingNow() {
		return playingNow;
	}
	
	// No set methods were made since all the songs are hard-coded in the driver file.
}
