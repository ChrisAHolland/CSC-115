/*
Christopher Holland
V00876844
CSC 115 Assingment #3
StackRefBased.java
*/

public class StackRefBased<T> implements Stack<T> {

	int count;
	StackNode<T> head;
	
    public StackRefBased() {
		count = 0;
		head = null;
		
   }

	// Returns the size of the stack
    public int size() {
        return count;
    }

	// Asks whether the stack is empty or not
    public boolean isEmpty() {
        if (count == 0) {
			return true;
		} else {
			return false;
		}
	}

	// Adds a new node of a given value to the top of the stack
    public void push(T data) { 
		StackNode<T> n = new StackNode<T>(data);
		n.next = head;
		head = n;
		count++;
    }

	// Removes the top node of the stack and returns its data
    public T pop() throws StackEmptyException {
        if (count == 0) {
		   throw new StackEmptyException();
	   }
		T value;
		
		if (count == 1) {
			value = head.data;
			head = null;
			count--;
			return value;
		} else {
			value = head.data;
			head = head.next;
			count--;
			return value;
		}
	}

	// Returns the data sealed in the top element of the stack
    public T peek() throws StackEmptyException {
		if (count == 0) {
		   throw new StackEmptyException();
	   }
		T peeker = head.data;
		return peeker;
    }

	// Clears the stack
    public void makeEmpty() {
		head = null;
		count = 0;
  }

	// Returns a string representation of the stack
    public String toString() {
		String s = "";
	    StackNode<T> temp = head;
		
			while (temp != null) {
				s += temp.data;	
				if (temp.next != null) {
					s += " ";			
				    temp = temp.next;
				}	
		}
			return s;
   } 
}


