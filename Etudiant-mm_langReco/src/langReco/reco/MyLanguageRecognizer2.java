package langReco.reco;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import langModel.MyLaplaceLanguageModel;
import langModel.MyNgramCounts;

public class MyLanguageRecognizer2 extends LanguageRecognizer{
	HashMap<String, MyLaplaceLanguageModel> lms = new HashMap<String, MyLaplaceLanguageModel>(); 
	
	public MyLanguageRecognizer2(){
		super();
		
		MyLaplaceLanguageModel lm = new MyLaplaceLanguageModel();
		for (String l : this.getLang()) {
			System.out.println(l);
			MyNgramCounts ngCounts = new MyNgramCounts();
			Collection<String> nGram = this.getNgramCountPath(l);
			
			for (String path :  nGram) {
				ngCounts.readNgramCountsFile(path);
			} //Y faudrait faire au cas où y a plusieurs modèles par langues
			lm.setNgramCounts(ngCounts);
			lms.put(l, lm);
		}
	}
	
	@Override
	public String recognizeSentenceLanguage(String sentence) {
		
		HashMap<String, Double> res = new HashMap<String, Double>();
		Set<String> lmsKeys = lms.keySet();
		System.out.println("test : " + lms.keySet());
		double resProb = 0;
		String resLang = null;
		for (String l : lmsKeys) {
			if (lms.get(l).getSentenceProb(sentence) > resProb) {
				resProb = res.get(l);
				resLang = l;
			}
		}
		
		/*double seuil = 0.0000001;
		if (resProb < seuil) 
			resLang = "unk";
		*/
		return resLang;
	}
	
}
