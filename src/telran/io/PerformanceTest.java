package telran.io;

public abstract class PerformanceTest {
	
	private int fileSize;
	private int bufferLength;
		
	public PerformanceTest(int bufferLength) {
		//this.fileSize = fileSize;
		this.bufferLength = bufferLength;
	}
	
	protected abstract int runTest() throws Exception;
	
	public void run() throws Exception {
		
		long start = System.currentTimeMillis();
		fileSize = runTest();
		displayInfo(start, System.currentTimeMillis());
	}

	private void displayInfo(long start, long finish) {
		System.out.printf("\nFile size: %d; Buffer length: %d; Running time: %d", 
				fileSize, bufferLength,  finish - start);
		
	}
	
}