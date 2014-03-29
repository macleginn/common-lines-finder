import java.util.concurrent.ArrayBlockingQueue;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class OutputWriter implements Runnable {
	
	private ArrayBlockingQueue<String> queue;
	private final String pathTofile;
	
	public OutputWriter(ArrayBlockingQueue<String> q, String path) {
		queue = q;
		this.pathTofile = path;
	}
	
	public void run() {
		try {
			String s = queue.take();
			while (s != "all done") {
				File file = new File(pathTofile);
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(s + "\n");
				bw.close();
				fw.close();
				s = queue.take();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
