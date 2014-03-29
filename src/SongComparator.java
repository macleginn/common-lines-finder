import java.util.ArrayList;

public class SongComparator {
	private final int so1len;
	private int so2len;
	private int index1;
	private int index2;
	private final String[][] lemmas1;
	private String[][] lemmas2;
	private final String[] lines1;
	private String[] lines2;
	
	private static int editDistance(String [] phrase1, String [] phrase2) {
		int [][] dm = new int [phrase1.length + 1][phrase2.length + 1];
		for (int i = 0; i <= phrase1.length; i++)
			dm[i][0] = i;
		for (int i = 1; i <= phrase2.length; i++)
			dm[0][i] = i;
		for (int i = 1; i <= phrase1.length; i++) {
			for (int j = 1; j <= phrase2.length; j++) {
				if (phrase1[i - 1].equals(phrase2[j - 1]))
					dm[i][j] = dm[i - 1][j - 1];
				else
					dm[i][j] = Math.min(1 + dm[i][j - 1], Math.min(1 + dm[i - 1][j], 2 + dm[i - 1][j - 1]));
			}
		}
		return dm[phrase1.length][phrase2.length];
	}
	
	SongComparator(SongObject so1) {
		lemmas1 = so1.lemmasArr;
		lines1  = so1.lines;
		so1len = so1.lines.length;
	}
	
	public String getCommonLines(SongObject so2) {
		lemmas2 = so2.lemmasArr;
		lines2  = so2.lines;
		so2len = so2.lines.length;
		index1 = 0;
		index2 = 0;
		boolean madeAJump;
		ArrayList<String> temp 		  = new ArrayList<String>();
		StringBuilder commonLinesTemp = new StringBuilder();
		while (index1 < so1len && index2 < so2len) {
			madeAJump = false;
			if (temp.size() > 1) {
				for (String s : temp)
					commonLinesTemp.append(s + "\n");
				commonLinesTemp.append("\n");
				temp.clear();
			}
			else
				temp.clear();
			for (int i = index2; i < so2len; i++) {
				int ed = editDistance(lemmas1[index1], lemmas2[i]);
				if (ed < 3) {
					temp.add(lines1[index1] + " | " + lines2[i]);
					index1++;
					index2 = i + 1;
					checkNext(temp);
					madeAJump = true;
					break;
				}
			}
			if (!madeAJump)
				index1++;
		}
		if (temp.size() > 1) {
			for (String s : temp)
				commonLinesTemp.append(s + "\n");
			commonLinesTemp.append("\n");
		}
		if (commonLinesTemp.length() > 0)
			return commonLinesTemp.toString();
		else return null;
	}
	
	private void checkNext(ArrayList<String> temp) {
		if (index1 < so1len && index2 < so2len && editDistance(lemmas1[index1], lemmas2[index2]) < 3) {
			temp.add(lines1[index1] + " | " + lines2[index2]);
			index1++;
			index2++;
			checkNext(temp);
		}
	}
}
