/*
 * Name: Christopher Holland
 * Student Number: V00876844
 */

public class IntegerLinkedList implements IntegerList
{
	private IntegerNode head;
	private IntegerNode tail;
	private int count;
	
	public IntegerLinkedList()
	{
		this.head = null;
		this.tail = null;
		this.count = 0;
	}

	/*
	 * PURPOSE:
	 *   Add the element x to the front of the list.
	 *
	 * PRECONDITIONS:
	 *   None.
	 *
	 * Examples:
	 *
	 * If l is {1,2,3} and l.addFront(9) returns, then l is {9,1,2,3}.
	 * If l is {} and l.addFront(3) returns, then l is {3}.
	 */
	public void addFront (int x)
	{
		IntegerNode temp = head;
		IntegerNode n = new IntegerNode(x);

		if (head == null) {
			head = n;
			tail = head;
			head.next = null;
			head.prev = null;
		} else {
			n.next = temp;
			temp.prev = n;
			head = n;
		}

		count++;
	}


	/*
	 * PURPOSE:
	 *   Add the element x to the back of the list.
	 *
	 * PRECONDITIONS:
	 *   None.
	 *
	 * Examples:
	 *
	 * If l is {1,2,3} and l.addBack(9) returns, then l is {1,2,3,9}.
	 * If l is {} and l.addBack(9) returns, then l is {9}.
	 */
	public void addBack (int x)
	{
		IntegerNode tail = new IntegerNode();
		tail.value = x;
		tail.next = null;

		if (head == null) {
			addFront(x);
		} else {
			IntegerNode temp = head;
			while (temp.next != null) {
				temp = temp.next;
			}

			temp.next = tail;
			tail.prev = temp;
			count++;
		}
	}
	

	/*
	 * PURPOSE:
	 *   Add the element x at position pos in the list.
	 *
	 * Note:
	 *   In a list with 3 elements, the valid positions for addAt are
	 *   0, 1, 2, 3.
	 *
	 * PRECONDITIONS:
	 *   pos >= 0 and pos <= l.size()
	 *
	 * Examples:
	 *
	 * If l is {} and l.addAt(9,0) returns, then l is {9}.
	 * If l is {1} and l.addAt(9,0) returns, then l is {9,1}.
	 * If l is {1,2} and l.addAt(9,1) returns, then l is {1,9,2}
	 * If l is {1,2} and l.addAt(9,2) returns, then l is {1,2,9}
	 */
	public void addAt (int x, int pos) 
	{
		if (count == 0) {
			addFront(x);
		} else if (pos == 0) {
			addFront(x);
		} else if (pos == count) {
			addBack(x);
		} else {
			IntegerNode n = new IntegerNode(x);
			IntegerNode temp = head;
			count++;
			int i = 1;
			while (temp.next != null && i < pos) {
				temp = temp.next;
				i++;
			}

			temp.next.prev = n;
			n.next = temp.next;
			n.prev = temp;
			temp.next = n;

		}
	}

	/*
	 * PURPOSE:
	 *	Return the number of elements in the list
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {7,13,22} l.size() returns 3
	 *	If l is {} l.size() returns 0
	 */
	public int size()
	{
		return count;
	}

	/*
	 * PURPOSE:
	 *   Return the element at position pos in the list.
	 *
	 * PRECONDITIONS:
	 *	pos >= 0 and pos < l.size()
	 *
	 * Examples:
	 *	If l is {67,12,13} then l.get(0) returns 67
	 *	If l is	{67,12,13} then l.get(2) returns 13
	 *	If l is {92} then the result of l.get(2) is undefined.
	 *
	 */
	public int  get (int pos) throws IndexOutOfBoundsException
	{
		if (pos < 0 || pos >= size()) {
			throw new IndexOutOfBoundsException();
		}
	
		IntegerNode temp = head;
		int current = 0;
		
		while (current != pos) {
			current++;
			temp = temp.next;
		}
		return temp.value;
	}

	/*
	 * PURPOSE:
	 *   Remove all elements from the list.  After calling this
	 *   method on a list l, l.size() will return 0
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {67,12,13} then after l.clear(), l is {}
	 *	If l is {} then after l.clear(), l is {}
	 *
	 */
	public void clear()
	{
		head = null;
		tail = null;
		count = 0;
	}

	/*
	 * PURPOSE:
	 *   Remove all instances of value from the list.
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {67,12,13,12} then after l.remove(12), l is {67,13}
	 *	If l is {1,2,3} then after l.remove(2), l is {1,3}
	 *	If l is {1,2,3} then after l.remove(99), l is {1,2,3}
	 */
	public void remove (int value)
	{
		IntegerNode temp = head;
		IntegerNode temptemp = null;

		while (temp.next != null && temp != null) {
			if (temp.value == value) {
				if (temp.prev != null) {
					temp.prev.next = temp.next;
					temp.next.prev = temp.prev;
				} else {
					temp.next.prev = null;
					head = temp.next;
				}

				temptemp = temp.next;
				count--;
				temp = temptemp;
			} else {
				temp = temp.next;
			}
		}

		if (temp.next == null && temp.value == value) {
			if (temp.prev == null) {
				temp = null;
				head = null;
				tail = null;
			} else {
				temp.prev.next = null;
				tail = temp.prev;
			}
			count--;
		}
	
	}

	/*
	 * PURPOSE:
	 *   Remove the element at position pos in the list.
	 *
	 * Note:
	 *   In a list with 3 elements, the valid positions for removeAt are
	 *   0, 1, 2.
	 *
	 * PRECONDITIONS:
	 *   pos >= 0 and pos < l.size()
	 *
	 * Examples:
	 *
	 * If l is {1} and l.removeAt(0) returns, then l is {}.
	 * If l is {1,2,3} and l.removeAt(1) returns, then l is {1,3}
	 * If l is {1,2,3} and l.removeAt(2) returns, then l is {1,2}
	 */
	public void removeAt (int pos)
	{
	IntegerNode temp = head;
		IntegerNode tail = new IntegerNode();
		
		if (count == 1) {
			head = null;
			tail = null;
			count--;
		} else if (pos == 0) {
			if (count > 1) {
				head = head.next;
				count--;
			} else {
				head = null;
			}
		} else if (pos + 1 == count) {

			while (temp.next != null) {
				temp = temp.next;
			}
			tail = temp.prev;
			temp = null;
			tail.next = null;
			count--;
		} else {
			int i = 1;
			while (temp.next != null && i < pos) {
				temp = temp.next;
				i++;
			}
			temp.next = temp.next.next;
			temp.next.next.prev = null;
			count--;
		}
	}

	/*
	 * PURPOSE:
	 *	Return a string representation of the list
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {1,2,3,4} then l.toString() returns "{1,2,3,4}"
	 *	If l is {} then l.toString() returns "{}"
	 *
	 */
	public String toString()
	{
		if (head == null) {
			return "{}";
		} else {
			String s = "{" + head.value;
			IntegerNode traverse = head.next;
			
			while (traverse != null) {
				s += "," + traverse.value;
				traverse = traverse.next;
			}
			s += "}";
			return s;
		}
	}
}
