/**
 * Class that displays the contents of triangle
 * @author Harry Longwell
 *
 */
public class Output implements Runnable{

	Triangle monitor;
	int rows = 0;
	
	/**
	 * Constructor method for the class
	 * @param monitor - Thread Monitor object
	 */
	public Output( Triangle monitor ){
		
		this.monitor = monitor;
		this.rows = monitor.rows();
		
	}

	/**
	 * Loops through the triangle displaying each row
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for( int i = 1; i < rows; i++ ){
			
			for( int j = 1; j <= i; j++ ){
				
				//check if the current position is the last of a row
				if(i == j){
					
					try {
						System.out.print( monitor.getValue( i, j ) );
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.err.println("Thread was interuppted during excecution");
						System.exit(1);
					}
					
				}
				else{
					
					try {
						System.out.print(monitor.getValue( i, j ) + " ");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.err.println("Thread was interuppted during excecution");
						System.exit(1);
					}
					
				}
				
			}
			
			System.out.println();
			
		}
		
	}
	
}
