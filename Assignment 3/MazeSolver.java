/*
Chris Holland
V00876844
March 12, 2017
MazeSolver.java
CSC 115 assignment #3
*/

/*
 * MazeSolver.java
 *
 * UVic CSC 115, Spring 2017
 *
 * Purpose:
 *   class that contains a single public static method called
 *   "findPath". To involve the method one must have already created
 *   an instance of Maze. The method must return a path through the
 *   maze (if it exists) in the format shown within the Assignment #3
 *   description.
 *
 * Note: You are free to add to this class whatever other methods will
 * help you in writing a solution to A#3 part 2.
 */

public class MazeSolver {
    public static String findPath(Maze maze) {
        String result = "";
		
		boolean[][] pathArray = new boolean[maze.getNumRows()][maze.getNumCols()];
		
		StackRefBased<MazeLocation> pathStack = new StackRefBased<MazeLocation>();
		
		pathStack.push(maze.getEntry());
		
	try {
		MazeLocation current = pathStack.peek();
		pathArray[current.getRow()][current.getCol()] = true;
		
		while (!pathStack.isEmpty() && !pathStack.peek().equals(maze.getExit())) {
			current = pathStack.peek();
			pathArray[current.getRow()][current.getCol()] = true;
			
			if (current.getRow() != maze.getNumRows()-1 && 
				pathArray[current.getRow()+1][current.getCol()] == false && 
				!maze.isWall(current.getRow()+1, current.getCol())) {
				
				MazeLocation right1 = new MazeLocation(current.getRow()+1, current.getCol());
				pathStack.push(right1);
			} 
			else if (current.getRow() != 0 && 
				pathArray[current.getRow()-1][current.getCol()] == false && 
				!maze.isWall(current.getRow()-1, current.getCol())) {
				
				MazeLocation left1 = new MazeLocation(current.getRow()-1, current.getCol());
				pathStack.push(left1);
			} 
			else if (current.getCol() != maze.getNumCols()-1 && 
				pathArray[current.getRow()][current.getCol()+1] == false && 
				!maze.isWall(current.getRow(), current.getCol()+1)) {
				
				MazeLocation down1 = new MazeLocation(current.getRow(), current.getCol()+1);
				pathStack.push(down1);			
			} 
			else if (current.getCol() != 1 && 
				pathArray[current.getRow()][current.getCol()-1] == false && 
				!maze.isWall(current.getRow(), current.getCol()-1)) {
				
				MazeLocation up1 = new MazeLocation(current.getRow(), current.getCol()-1);
				pathStack.push(up1);		
			} else {
				current = pathStack.peek();
				pathStack.pop();
			}
		}
		} 
	catch (StackEmptyException e) {
			e.printStackTrace();
		}
		
		//Pushing the pathStack into a new stack in the correct order
		
		StackRefBased<MazeLocation> pathResult = new StackRefBased<MazeLocation>();
		
	try {
			while (!pathStack.isEmpty()) {
				pathResult.push(pathStack.pop());		
			}
		}
	catch (StackEmptyException e) {
			e.printStackTrace();
		}
		
		
		//Returning the stackResult as a string
		if (pathResult.isEmpty()) {
			return "";
		}
		try {
			result = pathResult.pop().toString() + " ";
				while (!pathResult.isEmpty()) {
					result += pathResult.pop().toString();
					if (pathResult.peek() != null) {
					result += " ";
					}
				}
		} catch (StackEmptyException e) {
			e.printStackTrace();
		}
		return result;
}
}

