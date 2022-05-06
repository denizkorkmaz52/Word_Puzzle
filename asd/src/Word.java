
public class Word {
	String english;
	String turkish;
	
	int x;
	int y;
	int direction;
	
	
	public Word(String english, String turkish) {
		this.english = english;
		this.turkish = turkish;
		this.x = 0;
		this.y = 0;
		this.direction = 0;
	}
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public String getTurkish() {
		return turkish;
	}
	public void setTurkish(String turkish) {
		this.turkish = turkish;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
}
