package telran.io;
import telran.io.*;

public class CopyFilePerformanceAppl {

	public static void main (String args[]) throws Exception {
		
		String pathToSource = "bigFile";
		String pathToDestination = "copyBigFile";
		
		
		int[] buffers = {10_000, 100_000, 1_000_000, 100_000_000};	
		
		for(int b: buffers) {
			CopyFileStreams copyFile = new CopyFileStreams(b);
			CopyPerformanceTest copyPerformance = 
					new CopyPerformanceTest(b, pathToSource, pathToDestination, copyFile);
			copyPerformance.run();
		}
				
	}
	
}
