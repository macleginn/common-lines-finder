import java.io.File;
import java.io.FileFilter;

public class TxtFF implements FileFilter {
	public boolean accept(File pathname) {
		return pathname.getAbsolutePath().endsWith(".txt");
	}
}