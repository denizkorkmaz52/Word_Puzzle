import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import enigma.console.TextAttributes;
import java.awt.Color;
import java.util.*;

public class Game {
	public enigma.console.Console cn = Enigma.getConsole("Game", 240, 45, 12, 0);
	public TextMouseListener tmlis;
	public KeyListener klis;

	// ------ Standard variables for mouse and keyboard ------
	public int mousepr; // mouse pressed?
	public int mousex, mousey; // mouse text coords.
	public int keypr; // key pressed?
	public int rkey; // key (for press/release)
	// ----------------------------------------------------

	Game() throws Exception { // --- Contructor

		char[][] puzzle = new char[15][15];
		String fileName = "puzzle.txt";
		int counter = 0;
		int printX = 2;
		int printY = 2;
		int printFrameX = 2;
		int printFrameY = 1;
		if (fileName.equalsIgnoreCase("puzzle.txt")) {
			File file = new File("C:\\Users\\Acer\\Desktop\\puzzle.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
			String st;
			for (int i = 0; i < puzzle.length; i++) {
				cn.getTextWindow().setCursorPosition(printFrameX, printFrameY);
				System.out.println(counter);
				printFrameX++;
				if (counter != 9)
					counter++;
				else
					counter = 0;
			}
			counter = 0;
			printFrameX = 1;
			printFrameY = 2;
			for (int i = 0; i < puzzle.length; i++) {
				cn.getTextWindow().setCursorPosition(printFrameX, printFrameY);
				System.out.println(counter);
				printFrameY++;
				if (counter != 9)
					counter++;
				else
					counter = 0;
			}
			counter = 0;
			cn.getTextWindow().setCursorPosition(printX, printY);
			while ((st = br.readLine()) != null) {
				for (int i = 0; i < 15; i++) {
					puzzle[counter][i] = st.charAt(i);
					if (puzzle[counter][i] == '0') {
						cn.setTextAttributes(new enigma.console.TextAttributes(Color.BLACK, Color.BLACK));
						System.out.print(st.charAt(i));
					} else if (puzzle[counter][i] == '1') {
						cn.setTextAttributes(new enigma.console.TextAttributes(Color.BLACK, Color.WHITE));
						System.out.print('?');
					} else {
						cn.setTextAttributes(new enigma.console.TextAttributes(Color.BLACK, Color.WHITE));
						System.out.print(st.charAt(i));
					}

				}
				printY++;
				printX = 2;
				cn.getTextWindow().setCursorPosition(printX, printY);
				counter++;

			}
			System.out.println();

		}

		SingleLinkedList sll = new SingleLinkedList();
		MultiLinkedList mll = new MultiLinkedList();
		fileName = "word_list.txt";
		if (fileName.equalsIgnoreCase("word_list.txt")) {
			fileName = "word_list.txt";
			File file = new File("C:\\Users\\Acer\\Desktop\\word_list.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
			String st;
			while ((st = br.readLine()) != null) {
				String[] readWords = st.split(",");
				Word element = new Word(readWords[0], readWords[1]);
				sll.addBetween(element);
			}
		}

		cn.setTextAttributes(new enigma.console.TextAttributes(Color.BLACK, Color.WHITE));
		cn.getTextWindow().setCursorPosition(3, 20);
		
		Node temp1 = sll.getHead();
		mll.addRow(((Word) temp1.getData()).getEnglish().charAt(0));
		char letter = ((Word) temp1.getData()).getEnglish().charAt(0);
		while (temp1 != null) {
			if (((Word) temp1.getData()).getEnglish().charAt(0) != letter) {
				mll.addRow(((Word) temp1.getData()).getEnglish().charAt(0));
				letter = ((Word) temp1.getData()).getEnglish().charAt(0);
			}
			while (temp1 != null && ((Word) temp1.getData()).getEnglish().charAt(0) == letter) {
				mll.addColumn(letter, ((Word) temp1.getData()).getEnglish());
				temp1 = temp1.getLink();

			}
		}

		sll.display();
		System.out.println();
		mll.Display();

		Letter[][] solution = new Letter[15][15];
		counter = 0;
		fileName = "solution.txt";
		if (fileName.equalsIgnoreCase("solution.txt")) {
			fileName = "solution.txt";
			File file = new File("C:\\Users\\Acer\\Desktop\\solution.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
			String st;
			while ((st = br.readLine()) != null) {
				for (int i = 0; i < solution.length; i++) {
					Letter element = new Letter(st.charAt(i));
					solution[counter][i] = element;
				}
				counter++;
			}
		}
		for (int i = 0; i < solution.length; i++) {
			for (int j = 0; j < solution.length; j++) {
				if (solution[i][j].getLetter() != '0' && j != 15) {
					int data1 = j + 1;
					String word = String.valueOf(solution[i][j].getLetter());
					while (data1 != 15) {
						if (solution[i][data1].getLetter() != '0' && !solution[i][data1].isHorizontal()) {
							word = word + String.valueOf(solution[i][data1].getLetter());
							solution[i][data1].setHorizontal(true);
						} else {
							Node temp = sll.getHead();
							while (temp != null) {
								if ((((Word) temp.getData()).getEnglish()).equals(word)) {
									((Word) temp.getData()).setX(j);
									((Word) temp.getData()).setY(i);
									((Word) temp.getData()).setDirection(1);// Horizontal
								}
								temp = temp.getLink();
							}
							break;
						}
						data1++;
					}
				}
				if (solution[i][j].getLetter() != '0' && i != 15) {
					int data2 = i + 1;
					String word2 = String.valueOf(solution[i][j].getLetter());
					while (data2 != 15) {
						if (solution[data2][j].getLetter() != '0' && !solution[data2][j].isVertical()) {
							word2 = word2 + String.valueOf(solution[data2][j].getLetter());
							solution[data2][j].setVertical(true);
						} else {
							Node temp2 = sll.getHead();
							while (temp2 != null) {
								if ((((Word) temp2.getData()).getEnglish()).equals(word2)) {
									((Word) temp2.getData()).setX(j);
									((Word) temp2.getData()).setY(i);
									((Word) temp2.getData()).setDirection(2);// Vertical
								}
								temp2 = temp2.getLink();
							}
							break;
						}
						data2++;
					}
				}
			}
		}

		// ------ Standard code for mouse and keyboard ------ Do not change
		tmlis = new TextMouseListener() {
			public void mouseClicked(TextMouseEvent arg0) {
			}

			public void mousePressed(TextMouseEvent arg0) {
				if (mousepr == 0) {
					mousepr = 1;
					mousex = arg0.getX();
					mousey = arg0.getY();
				}
			}

			public void mouseReleased(TextMouseEvent arg0) {
			}
		};
		cn.getTextWindow().addTextMouseListener(tmlis);

		klis = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (keypr == 0) {
					keypr = 1;
					rkey = e.getKeyCode();
				}
			}

			public void keyReleased(KeyEvent e) {
			}
		};
		cn.getTextWindow().addKeyListener(klis);
		// ----------------------------------------------------

		cn.getTextWindow().setCursorType(1);
		int px = 5, py = 5;
		cn.getTextWindow().setCursorPosition(px, py);
		boolean isPressedLetter = false;
		while (true) {
			if (mousepr == 1) { // if mouse button pressed
//				px = mousex;
//				py = mousey;

				mousepr = 0; // last action
			}
			if (keypr == 1) { // if keyboard button pressed
				if (isPressedLetter == false) {
					if (rkey == KeyEvent.VK_LEFT) {
						px--;
						cn.getTextWindow().setCursorPosition(px, py);
					}
					if (rkey == KeyEvent.VK_RIGHT) {
						px++;
						cn.getTextWindow().setCursorPosition(px, py);
					}
					if (rkey == KeyEvent.VK_UP) {
						py--;
						cn.getTextWindow().setCursorPosition(px, py);
					}
					if (rkey == KeyEvent.VK_DOWN) {
						py++;
						cn.getTextWindow().setCursorPosition(px, py);
					}

					char rckey = (char) rkey;
					if (rkey != KeyEvent.VK_LEFT && rkey != KeyEvent.VK_UP && rkey != KeyEvent.VK_DOWN
							&& rkey != KeyEvent.VK_RIGHT) {
						cn.setTextAttributes(new enigma.console.TextAttributes(Color.BLACK, Color.WHITE));
						String character = String.valueOf(rckey);
						character = character.toLowerCase();
						System.out.print(character);
						isPressedLetter = true;
					}

					if (rkey == KeyEvent.VK_SPACE) {

					}
				} else {
					if (rkey == KeyEvent.VK_LEFT && px != 2) {
						if (puzzle[py - 2][px - 3] != '0') {
							px--;
							cn.getTextWindow().setCursorPosition(px, py);
						}
					}
					if (rkey == KeyEvent.VK_RIGHT && px != 17) {
						if (puzzle[py - 2][px - 1] != '0') {
							px++;
							cn.getTextWindow().setCursorPosition(px, py);
						}
					}
					if (rkey == KeyEvent.VK_UP && py != 2) {
						if (puzzle[py - 3][px - 2] != '0') {
							py--;
							cn.getTextWindow().setCursorPosition(px, py);
						}
					}
					if (rkey == KeyEvent.VK_DOWN && py != 17) {
						if (puzzle[py - 1][px - 2] != '0') {
							py++;
							cn.getTextWindow().setCursorPosition(px, py);
						}
					}

					char rckey = (char) rkey;
					if (rkey != KeyEvent.VK_LEFT && rkey != KeyEvent.VK_UP && rkey != KeyEvent.VK_DOWN
							&& rkey != KeyEvent.VK_RIGHT) {
						cn.setTextAttributes(new enigma.console.TextAttributes(Color.BLACK, Color.WHITE));
						String character = String.valueOf(rckey);
						character = character.toLowerCase();
						System.out.print(character);
						isPressedLetter = true;
					}
				}
				keypr = 0; // last action
			}

			Thread.sleep(20);
		}
	}
}
