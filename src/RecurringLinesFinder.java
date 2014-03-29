import java.util.concurrent.ArrayBlockingQueue;
import java.util.ArrayList;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RecurringLinesFinder {
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		String reportFileName = "report.txt";
		String textDirName = args[0];
		File textDir 	   = new File(textDirName); 
		File file = new File(reportFileName);
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Listing all the files ending with ".txt"
		File [] listOfFiles = textDir.listFiles(new TxtFF());
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
		OutputWriter    ow = new OutputWriter(queue, "report.txt");
		new Thread(ow).start();
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < listOfFiles.length - 1; i++) {
			SongObject so1 = new SongObject(listOfFiles[i].getAbsolutePath());
			ArrayList<SongObject> compList = new ArrayList<SongObject>();
			for (int j = i + 1; j < listOfFiles.length; j++) {
				compList.add(new SongObject(listOfFiles[j].getAbsolutePath()));
			}
			SongObject [] comp = compList.toArray(new SongObject [compList.size()]);
			executor.execute(new WorkHorseThread(queue, so1, comp));
		}
		executor.shutdown();
        while (!executor.isTerminated()) {
        	;
        }
        queue.add("all done");
        System.out.println("All done in " + (System.currentTimeMillis() - startTime) / 1000 + " seconds.");
	}
}
