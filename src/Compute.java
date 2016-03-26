
/**
 * Class that computes the values for the positions of the triangle
 * @author Harry Longwell
 *
 */
public class Compute implements Runnable {
	
	int c = 0;
	int r = 0;
	int A = 0;
	int B = 0;
	int S = 0;
	public Triangle monitor;
	
	/**
	 * Constructor method of the class
	 * @param c - columns
	 * @param r - rows
	 * @param A - command line argument
	 * @param B - command line argument
	 * @param S - command line argument
	 * @param monitor - thread Monitor object
	 */
	public Compute( int c, int r, int A, int B, int S, Triangle monitor ){
		
		this.c = c;
		this.r = r;
		this.A = A;
		this.B = B;
		this.S = S;
		this.monitor = monitor;
		
	}

	/**
	 * Performs the appropriate calculation based on the column and row value
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		int value = 0;
		
		if( r == 1 && c == 1 ){
			
			value = S;
			
		}
		else if( c == 1 ){
			
			try {
				value = A + monitor.getValue( r - 1, c );
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if( c == r ){
			
			try {
				value = monitor.getValue( r - 1, c - 1 ) + B;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else{
			
			try {
				value = monitor.getValue( r - 1, c - 1 ) + monitor.getValue( r - 1, c );
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		monitor.putValue( r, c, value );
		
	}

}
