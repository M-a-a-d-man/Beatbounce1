import java.io.File;
import java.io.IOException;
//import java.util.Scanner;
//import java.util.ArrayList;
import javax.sound.sampled.*;

public class Player1 {

	int currentFrame; 	// current position
	Clip clip;
	String status;
	AudioInputStream audioInputStream;
	static String filePath;
//	static String filePath1;

	// constructor to initialize streams and clip
	public Player1() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); // AudioInputStream object
		clip = AudioSystem.getClip(); // clip reference
		clip.open(audioInputStream); // audioInputStream to the clip
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

//	public class Song {
//		
//		// Attributes
//		private String name;
//		private String path;
//		private int bpm;
//		private int startTime;
//		
//		// Static ArrayList that will contain all songs as elements
//		private static ArrayList<Song> songs;
//
//		
//		// 4-parameter constructor
//		// There is no default constructor active currently
//		public Song(String name, String path, int bpm, int startTime) {
//			this.name = name;
//			this.path = path;
//			this.bpm = bpm;
//			this.startTime = startTime;
//			songs.add(this);
//		}
//		
//		// Public static method that returns a song using its BPM as a search tool. Returns any song in the list with the closest BPM.
//		public static Song findSong(int bpm) {
//			Song chosenSong = null;
//			int difference = Integer.MAX_VALUE;
//			for(int i = 0; i < songs.size(); i++) {
//				Song checkingSong = songs.get(i);
//				if(Math.abs(checkingSong.bpm-bpm) < difference) {
//					chosenSong = songs.get(i);
//					difference = Math.abs(checkingSong.bpm-bpm);
//				}
//			}
//			return chosenSong;
//		}
//		
//		// Public static method that returns a song using its name as a search tool. Returns null if the name is not exactly the same.
//		public static Song findSong(String name) {
//			Song chosenSong = null;
//			for(int i = 0; i < songs.size(); i++) {
//				Song checkingSong = songs.get(i);
//				if(checkingSong.name.equals(name)) {
//					chosenSong = checkingSong;
//					break;
//				}
//			}
//			return chosenSong;
//		}
//		
//		// Get methods for all attributes
//		public String getName() {
//			return this.name;
//		}
//		
//		public String getPath() {
//			return this.path;
//		}
//		
//		public int getBpm() {
//			return this.bpm;
//		}
//		
//		public int getStartTime() {
//			return this.startTime;
//		}
//		
//		// No set methods were made since all the songs are hard-coded in the driver file.
//	}
	
//	public static void playSong(String filepath0, String option) {
//		try {
//			filePath = filepath0;
//			Player0 audioPlayer = new Player0();
//			switch(option) {
//			case "play":
//				audioPlayer.play();
//			case "stop":
//				audioPlayer.stop();
//			}
//		}
//		
//		catch (Exception ex) {
//			System.out.println("Error with playing sound.");
//			ex.printStackTrace();
//		}
//	}
	
	public void playSong(String filepath) {
		filePath = filepath;
		this.play();
	}
	
//	private void gotoChoice(int c) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
//		switch (c) {
//			case 1:
//				pause();
//				break;
//			case 2:
//				resumeAudio();
//				break;
//			case 3:
//				restart();
//				break;
//			case 4:
//				stop();
//				break;
//			case 5:
//				Scanner sc = new Scanner(System.in);
//				System.out.println("Enter time (" + 0 + ", " + (clip.getMicrosecondLength()) + ")");
//				int c1 = sc.nextInt();
//				jump(c1);
//				break;
//		}
//	
//	}
	
	public void play() {
		clip.start();
		status = "play";
	}
	
	public void pause() {
		if (status.equals("paused")) {
			System.out.println("audio is already paused");
			return;
		}
		this.currentFrame = (int) this.clip.getMicrosecondPosition();
		clip.stop();
		status = "paused";
	}
	
	public void resumeAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (status.equals("play")) {
			System.out.println("Audio is already being played");
			return;
		}
		clip.close();
		resetAudioStream();
		clip.setMicrosecondPosition(currentFrame);
		this.play();
	}
	
	public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		clip.stop();
		clip.close();
		resetAudioStream();
		currentFrame = 0;
		clip.setMicrosecondPosition(0);
		this.play();
	}
	
	public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		currentFrame = 0;
		clip.stop();
		clip.close();
	}
	
	// Method to jump over a specific part
	public void jump(int c) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (c > 0 && c < clip.getMicrosecondLength()) {
			clip.stop();
			clip.close();
			resetAudioStream();
			currentFrame = c;
			clip.setMicrosecondPosition(c);
			this.play();
		}
	}
	
	// Method to reset audio stream
	public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
		clip.open(audioInputStream);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
//	public void fadeOut(){
////	    int max = 50;
//	    int vol = 10;
////
////	    while(true){
//	        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//	        gainControl.setValue(-vol);
//	        vol+=10;
//	        try {
//	            Thread.sleep(1500);
//	        } catch (InterruptedException ignored) {
//	            Thread.currentThread().interrupt();
//	        }
////	        if(vol == max){
////	            pause();
////	            break;
////	        }
//
////	    }
//	}
	
	public void setToZero() {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-80);
	}
	
	public void fadeOut(int vol) {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-vol);
	}
	
	public void fadeIn(int vol) {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(vol);
	}
	
	

}