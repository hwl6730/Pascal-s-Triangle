import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main class that takes in command line arguments and creates threads that will
 *  compute the numbers within the generalized Pascal's Triangle
 * @author Harry Longwell
 *
 */
public class GenPasTri {
	
	/**
	 * Main method of the class, creates the threads and calls all the necessary objects
	 * @param args - command line arguments, used to build triangle
	 */
	public static void main( String args[] ){
		
		//Check to make sure the correct number of arguments were given
		if( args.length != 4 ){
			
			System.err.println("Usage: java GenPasTri <N> <A> <B> <S>");
			System.exit(1);
			
		}
		
		int rows = 0;
		int A = 0;
		int B = 0;
		int S = 0;
		
		//Parses the input, giving appropriate error messages when the input is invalid
		try{
			
			if( Integer.parseInt( args[0] ) >= 0 ){

				rows = Integer.parseInt(args[0]) + 1;

			}
			else{
				
				System.err.println("Invalid input for <N>, must be number zero or greater");;
				System.exit(1);
				
			}

			if( Integer.parseInt( args[1] ) >= 0 ){
				
				A = Integer.parseInt(args[1]);
			
			}
			else{
				
				System.err.println("Invalid input for <A>, must be number zero or greater");
				System.exit(1);
				
			}
			
			if( Integer.parseInt( args[2] ) >= 0 ){
				
				B = Integer.parseInt(args[2]);
			
			}
			else{
				
				System.err.println("Invalid input for <B>, must be number zero or greater");
				System.exit(1);
				
			}
			
			if( Integer.parseInt( args[3] ) >= 0 ){
				
				S = Integer.parseInt(args[3]);
			
			}
			else{
				
				System.err.println("Invalid input for <S>, must be number zero or greater");
				System.exit(1);
				
			}
			
		}catch( NumberFormatException e ){
			
			System.err.println("Inputs must be valid numbers zero or greater");
			System.exit(1);
			
		}
		
		//Create an instance of the monitor object
		Triangle monitor = new Triangle( rows );
		
		//Run the thread which will print out contents of the triangle
		Thread thread = new Thread( new Output( monitor ) );
		thread.start();
		
		int columns = rows;
		
		//loops through and creates a thread to compute all the values of the triangle
		//starting with the last value
		for( int i = rows - 1; i > 0; i-- ){
			
			for( int j = columns - 1; j > 0; j-- ){
				
				thread = new Thread( new Compute( j, i, A, B, S, monitor ) );
				thread.start();
				
			}
			
			columns--;
			
		}
		
	}

}
