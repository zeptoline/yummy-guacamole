package langReco.reco;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import langModel.MyLaplaceLanguageModel;
import langModel.MyNaiveLanguageModel;
import langModel.MyNgramCounts;

public class MyLanguageRecognizer1 extends LanguageRecognizer{

	@Override
	public String recognizeSentenceLanguage(String sentence) {
		
		HashMap<String, Double> res = new HashMap<String, Double>();     
			
		for (String l : this.getLanguages()) {
			MyNaiveLanguageModel lm = new MyNaiveLanguageModel();
			MyNgramCounts ngCounts = new MyNgramCounts();
			Collection<String> nGram = this.getNgramCountPath(l);
			lm.setNgramCounts(ngCounts);
			
			for (String path :  nGram) {
				ngCounts.readNgramCountsFile(path);
			} //Y faudrait faire au cas où y a plusieurs modèles par langues
			lm.setNgramCounts(ngCounts);
			res.put(l, lm.getSentenceProb(sentence));
		}
		
		Set<String> langs = res.keySet();
		double resProb = 0;
		String resLang = null;
		for (String key : langs) {
			System.out.println(resProb);
			if (res.get(key) > resProb) { 
				resProb = res.get(key);
				resLang = key;
				
			}
		}
		
		return resLang;
	}
	
}
