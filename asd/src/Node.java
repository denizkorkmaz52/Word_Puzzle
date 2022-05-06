
public class Node {
	Object data;
	Node link;
	Node(Object data){
		this.data = data;
		this.link = null;
	}
	public Object getData() {
		return data;
	}
	public void setData(char data) {
		this.data = data;
	}
	public Node getLink() {
		return link;
	}
	public void setLink(Node link) {
		this.link = link;
	}
	
	public void display() {
		System.out.println(data);
	}
}
