import java.io.*;
import java.util.ArrayList;
import java.io.IOException;

public class SongObject {
	public final String []   lines;
	public final String [][] lemmasArr;
	public final String filename;
	
	public SongObject(String path) {
		ArrayList<String>   linesTemp  = new ArrayList<String>();
		ArrayList<String[]> lemmasTemp = new ArrayList<String[]>();
		String [] elms = path.split("/"); 
		filename = elms[elms.length - 1];
		try {
			FileReader fr     = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null) {
				StringBuilder     tokens = new StringBuilder();
				ArrayList<String> lemmas = new ArrayList<String>();
				String [] wordsAndLemmas = line.split(" ");
				for (String s : wordsAndLemmas) {
					String [] pair = s.split("\\|");
					tokens.append(pair[0] + " ");
					lemmas.add(pair[1]);
				}
				linesTemp.add(tokens.toString());
				lemmasTemp.add(lemmas.toArray(new String [lemmas.size()]));
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		lines     = linesTemp.toArray(new String [linesTemp.size()]);
		lemmasArr = lemmasTemp.toArray(new String [lemmasTemp.size()][]);
	}
}
