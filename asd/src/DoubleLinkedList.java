
public class DoubleLinkedList {
	NodeDouble head;
	NodeDouble tail;
	public DoubleLinkedList() {
		head = null;
		tail = null;
	}
	
	public void addEnd(Object dataToAdd) {
		if(head == null && tail == null) {
			NodeDouble newnode = new NodeDouble(dataToAdd);
			head = newnode;
			tail = newnode;
		}
		else {
			NodeDouble newnode = new NodeDouble(dataToAdd);
			newnode.setPrev(tail);
			tail.setNext(newnode);
			tail = newnode;
		}
	}
	
	
	public void Display1() {
		NodeDouble temp = head;
		while(temp != null) {
			System.out.print(temp.getData() + " ");
			temp = temp.getNext();
		}
		System.out.println("");
	}
	public void Display2() {
		NodeDouble temp = tail;
		while(temp != null) {
			System.out.print(temp.getData() + " ");
			temp = temp.getPrev();
		}
		System.out.println("");
	}
	public void Swap(int x) {
		NodeDouble tempBegin = head;;
		NodeDouble tempEnd = tail;
		int tempFirst;
		int tempLast;
		
		for (int i = 0; i < x - 1; i++) {
			tempBegin = tempBegin.getNext();
			tempEnd = tempEnd.getPrev();
		}
		tempFirst = (int)tempBegin.getData();
		tempLast = (int)tempEnd.getData();
		tempBegin.setData(tempLast);
		tempEnd.setData(tempFirst);
		
	
	}
	public int size() {
		NodeDouble temp = head;
		int counter = 0;
		while(temp != null) {
			temp = temp.getNext();
			counter++;
		}
		return counter;
	}
}
