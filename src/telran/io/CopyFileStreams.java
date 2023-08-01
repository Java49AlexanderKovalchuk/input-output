package telran.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CopyFileStreams implements CopyFile{
	private int bufferLength;
	public CopyFileStreams(int bufferLength) {
		this.bufferLength = bufferLength;
	}
	
	@Override
	public void copy(String pathToSource, String pathToDestination) throws Exception {
		try (FileInputStream inputStream = new FileInputStream(pathToSource);
			 FileOutputStream outputStream = new FileOutputStream(pathToDestination)) {
			
			byte[] buffer = new byte[bufferLength];
			int bytes = 0;
			 
			while((bytes = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, bytes);
			}
		}
	}
}
