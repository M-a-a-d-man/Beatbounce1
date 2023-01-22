import java.io.IOException;
//import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BeatBounceDemo {
	final static int fresh4 = 4; // Rate at which songs fade
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
		createSongs();
		new GUI();
		Song currentSong = Song.findSong("Super Rich Kids - Frank Ocean, Earl Sweatshirt"); // Default start
		int currentSongBPM = currentSong.getBpm();
		int currentTap = 0; // Default value
		int counter = 0;
		
		Player0 audioPlayer0;
		Player1 audioPlayer1;
		Player0.filePath = "./assets/richkids_60_8000000.wav";
		Player1.filePath = "./assets/richkids_60_8000000.wav";
		Song.setPlayingNow(Song.findSong("Super Rich Kids - Frank Ocean, Earl Sweatshirt"));
		audioPlayer0 = new Player0();
		audioPlayer0.play();
		audioPlayer1 = new Player1();
		audioPlayer1.stop();
		
		while (true) {
			Thread.sleep(6000);
			while (true) {
				currentTap = GUI.getBPM();
				System.out.println("Current BPM: " + currentTap);
				Thread.sleep(1000);
				if((Math.abs(currentSongBPM - currentTap)) > 12 && currentSong != Song.findSong(currentTap)) {
					break;
				}
			}
			
			currentSong = Song.findSong(currentTap);
			currentSongBPM = currentSong.getBpm();
			counter++;
			if(counter%2==0) {			
//				audioPlayer1.stop();
//				System.out.println("Player1 Stopped");
				Player0.filePath = currentSong.getPath();
				audioPlayer0 = new Player0();
				System.out.println("Player0 is Now Playing: " + currentSong.getName());
				audioPlayer0.play();
				audioPlayer0.jump(currentSong.getStartTime()-fresh4*100000);
				transition10(audioPlayer0, audioPlayer1);
				Song.setPlayingNow(currentSong);
			} else {
//				audioPlayer0.stop();
//				System.out.println("Player0 Stopped");
				Player1.filePath = currentSong.getPath();
				audioPlayer1 = new Player1();
				System.out.println("Player1 is Now Playing: " + currentSong.getName());
				audioPlayer1.play();
				audioPlayer1.jump(currentSong.getStartTime()-fresh4*100000);
				Song.setPlayingNow(currentSong);
				transition01(audioPlayer0, audioPlayer1);
			}
//			Player0.filePath = currentSong.getPath();
//			currentSongBPM = currentSong.getBpm();
//			audioPlayer0.play();
		}
	}
	
	public static void createSongs() {
		new Song("Super Rich Kids - Frank Ocean, Earl Sweatshirt", "./assets/richkids_60_8000000.wav", 60, 8000000);
		new Song("Don't Worry Be Happy - Bobby McFerrin",          "./assets/DontWorryBeHappy_69_29440000.wav", 69, 29440000);
		new Song("Beautiful Girls - Sean Kingston",                "./assets/beautifulgirls_87_0.wav", 87, 0);
		new Song("Doo Wop (That Thing) - Lauryn Hill",             "./assets/doowop_200_0.wav", 100, 0);
		//new Song("Dancing Queen - Abba",                         "./assets/dancing.wav", 101, 3000000);
		new Song("Call Me Maybe - Carly Rae Jepsen",               "./assets/callmemaybe_120_21000000.wav", 120, 21000000);
		new Song("Harder, Better, Faster, Stronger - Daft Punk",   "./assets/harder_123_21000000.wav", 123, 21000000);
		new Song("I Gotta Feeling - Black Eyed Peas",              "./assets/igottafeeling_128_29000000.wav", 128, 29000000);
		new Song("Beat It - Michael Jackson",                      "./assets/beatit_139_24000000.wav", 139, 24000000);
		new Song("Holding Out for a Hero - Bonnie Tyler",          "./assets/HoldingOutForAHero_150_58130000.wav", 150, 58130000);
		new Song("Hey Ya! - OutKast",                              "./assets/heyya_160_0.wav", 160, 0);
		new Song("Bad Blood - Taylor Swift",                       "./assets/badblood_170_12000000.wav", 170, 12000000);
		new Song("Mr. Blue Sky - Electric Light Orchestra",        "./assets/blue_178_10000000.wav", 178, 10000000);
		new Song("Love Me Like You Do - Ellie Goulding",           "./assets/loveme_190_122000000.wav", 190, 122000000);
		//new Song("A Worse Today - Infanticide",                    "./assets/aWorseDay_201_29440000.wav", 201, 29440000);
		new Song("I Wish - Stevie Wonder",                         "./assets/Iwish_212_7250000.wav", 212, 7250000);
		//new Song("Mr. Brightside - The Killers",                 "./brightside_148_24000000.wav", 148, 10000000);
		//new Song("Antisocial - Ed Sheeran, Travis Scott",        "./antisocial_150_0.wav", 150, 0);
	}
	
	public static void transition01(Player0 player0, Player1 player1) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		    player1.setToZero();
		    for(int i = 0; i<fresh4; i++){
		        player1.fadeIn(6);
		        Thread.sleep(1000);
		        player0.fadeOut(6);
		        
		    }
		    player0.stop();
            System.out.println("Player0 Stopped");
	}
	public static void transition10(Player0 player0, Player1 player1) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
	    player0.setToZero();
	    for(int i = 0; i<fresh4; i++){
	        player0.fadeIn(6);
	        Thread.sleep(1000);
	        player1.fadeOut(6);
	        
	    }
	    player1.stop();
        System.out.println("Player1 Stopped");
	}
}
