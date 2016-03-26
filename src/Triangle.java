/**
 * Monitor object class
 * @author Harry Longwell
 *
 */
public class Triangle {

	int rows_total = 0;
	int[][] triangle;
	boolean[][] check;
	
	/**
	 * Constructor method
	 * @param rows
	 */
	public Triangle( int rows ){
		
		this.rows_total = rows;	
		this.triangle = new int[rows][rows];
		this.check = new boolean[rows][rows];
		
	}
	
	/**
	 * Returns the number of rows in the triangle
	 * @return - number of rows
	 */
	public synchronized int rows(){
			
		return rows_total;
			
	}
	
	/**
	 * Inserts the given value into the given position of the triangle,
	 * Notifies all waiting threads once a value is placed
	 * @param r - row coordinate
	 * @param c - column coordinate
	 * @param value - value to be placed into position
	 */
	public synchronized void putValue( int r, int c, int value ){
		
		if(this.check[r][c] == true){
			System.err.println("Tried to insert a value in a position that already has a value");
			System.exit(1);
		}
		this.triangle[r][c] = value;
		this.check[r][c] = true;
		notifyAll();
		
	}
	
	/**
	 * Retrieves the value in the triangle from the given position
	 * Waits until the position has had a value placed into it
	 * @param r - row coordinate
	 * @param c - column coordinate
	 * @return - the value from the position
	 * @throws InterruptedException
	 */
	public synchronized int getValue( int r, int c )throws InterruptedException{
		
		while(!check[r][c]){
			
			wait();
			
		}
		
		return this.triangle[r][c];
		
	}
	
}
