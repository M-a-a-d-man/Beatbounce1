import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class GUI {

	private int counter5s = 0;
//	private int counter15s = 0;

	private static int previousBPM = 0;
	private static int currentBPM = 0;
	private JLabel bpmDisplay;
	private JLabel artistDisplay;
	private JFrame frame;
	
	Timer timer1s = new Timer(1000, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			artistDisplay.setText(Song.getPlayingNow().getName());
		}
	});
	
	Timer timer5s = new Timer(5000, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			currentBPM = (previousBPM == 0) ? counter5s*12 : (counter5s*12 + previousBPM)/2;
			bpmDisplay.setText("CURRENT BPM: " + currentBPM);
			counter5s = 0;
			previousBPM = currentBPM;
		}
	});
	
	
	
//	Timer timer15s = new Timer(15000, new ActionListener() {
//		public void actionPerformed(ActionEvent e) {
//			currentBPM = counter15s*4;
//			bpmDisplay.setText("Current BPM: " + currentBPM);
//			counter15s = 0;
//			timer5s.stop();
//		}
//		
//	});

	
	public GUI() {
		
		frame = new JFrame();
		
		JButton button = new JButton("Step");
		button.setBounds(100,250,100,50);
		button.setFont(new Font("Plain",Font.PLAIN,20));
		button.setFocusable(false);
		
		// SO THAT THE BUTTON CAN BE CLICKED ON
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				counter5s++;
//				counter15s++;
			}
		});
		
		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(225,250,100,50);
		exitButton.setFont(new Font("Plain",Font.PLAIN,20));
		exitButton.setFocusable(false);
		
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		// SO THAT A KEYSTROKE "CLICKS" THE BUTTON
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				counter5s++;
//				counter15s++;
			}
			
		});
		
		bpmDisplay = new JLabel("CURRENT BPM: 0");
		bpmDisplay.setBounds(75,75,1000,100);
		bpmDisplay.setFont(new Font("Plain",Font.PLAIN,100));
		
		artistDisplay = new JLabel("Song - Artist");
		artistDisplay.setBounds(90, 150, 900, 75);
		artistDisplay.setFont(new Font("Plain",Font.PLAIN,20));
	
		frame.add(button);
		frame.add(exitButton);
		frame.add(bpmDisplay);
		frame.add(artistDisplay);
		frame.setTitle("BeatBounce");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 420);
		frame.setLayout(null);
		frame.setVisible(true);
		
		timer1s.start();
		timer5s.start();
		
//		timer15s.start();
	}


	// HOW TO GRAB BPM:
	public static int getBPM() {
		return currentBPM;
	}
	
//	// MAIN
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new GUI();
//	}
//	

}
