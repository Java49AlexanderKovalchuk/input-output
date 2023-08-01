package telran.io;

import java.nio.file.Files;
import java.nio.file.Path;

public class CopyPerformanceTest extends PerformanceTest {
	private  String pathToSource;
	private String pathToDestination;
	private CopyFile copyFile;
	
	public CopyPerformanceTest(int bufferLength, String pathToSource, 
			String pathToDestination, CopyFile copyFile) {
		super(bufferLength);
		this.pathToSource = pathToSource;
		this.pathToDestination = pathToDestination;
		this.copyFile = copyFile;
		
	}

	@Override
	protected int runTest() throws Exception {
		copyFile.copy(pathToSource, pathToDestination);
		return (int) Files.size(Path.of(pathToSource));
	}

}
