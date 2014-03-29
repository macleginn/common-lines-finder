import java.util.concurrent.ArrayBlockingQueue;

public class WorkHorseThread implements Runnable {
	
	private ArrayBlockingQueue<String> queue;
	private final SongObject basic;
	private final SongObject [] comparanda;
	
	public WorkHorseThread(ArrayBlockingQueue<String> q, SongObject basic, SongObject[] comparanda) {
		queue = q;
		this.basic = basic;
		this.comparanda = comparanda;
	}
	
	public void run() {
		SongComparator workHorse = new SongComparator(basic);
		try {
			for (SongObject comparandum : comparanda) {
				System.out.println(basic.filename + " — " + comparandum.filename);
				String result = workHorse.getCommonLines(comparandum);
				if (result != null) {
					String header = basic.filename + " — " + comparandum.filename;
					queue.put(header + "\n\n" + result);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
