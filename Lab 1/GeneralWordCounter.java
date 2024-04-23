package textproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.List;

public class GeneralWordCounter implements TextProcessor {
	private Set<String> stopWords;
	private Map<String, Integer> m;
	
	public GeneralWordCounter(Set<String> s) {
		stopWords = s;
		m = new TreeMap<String, Integer>();
	}
	public void process(String w) {
		if(!stopWords.contains(w)) {
			if(m.containsKey(w)) {
				m.put(w, m.get(w) + 1);
			}else {
				m.put(w, 1);
			}
		
		}
	}
	
	public void report() {
        //for (String key : m.keySet()) {
        //	if(m.get(key) >= 200) {
        //		System.out.println(key + ": " + m.get(key));
        //	}
		
		Set<Map.Entry<String, Integer>> wordSet = m.entrySet();
	    List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
	    wordList.sort(new WordCountComparator());
	    
	    int n = 20;
	    if(wordList.size()< 20) {
	    	n = wordList.size();
	    }
	    for(int i=0; i<n; i++) {
	    	System.out.println(wordList.get(i));
	    }

	}
	
	public List< Map.Entry<String, Integer>> getWordList() {
		
        return new ArrayList<>(m.entrySet());
}
	
}
