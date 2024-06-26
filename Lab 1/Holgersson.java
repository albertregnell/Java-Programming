package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		
		long t0 = System.nanoTime();
		
		ArrayList<TextProcessor> words = new ArrayList<TextProcessor>();
		
		words.add(new SingleWordCounter("nils"));
		words.add(new SingleWordCounter("norge"));
		
		MultiWordCounter mw = new MultiWordCounter(REGIONS);
		
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopWords = new HashSet<String>();
		
		while (scan.hasNext()) {
			stopWords.add(scan.next().toLowerCase());
			}
		scan.close();
		
		TextProcessor gwc = new GeneralWordCounter(stopWords); 
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			for(TextProcessor n: words) {
				n.process(word);
			}
			mw.process(word);
			gwc.process(word);
		}

		s.close();
		
		for(TextProcessor n: words) {
			n.report();
		}
		mw.report();
		gwc.report();
		
		long t1 = System.nanoTime();
        System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");
	}
}