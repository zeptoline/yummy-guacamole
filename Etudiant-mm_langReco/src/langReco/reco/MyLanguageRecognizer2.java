package langReco.reco;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import langModel.MyLaplaceLanguageModel;
import langModel.MyNgramCounts;

public class MyLanguageRecognizer2 extends LanguageRecognizer{
	HashMap<String, MyLaplaceLanguageModel> lms = new HashMap<String, MyLaplaceLanguageModel>(); 
	
	public MyLanguageRecognizer2(String nGramPath){
		super();
		this.loadNgramCountPath4Lang(nGramPath);
		
		
		for (String l : this.getLang()) {
			MyLaplaceLanguageModel lm = new MyLaplaceLanguageModel();
			MyNgramCounts ngCounts = new MyNgramCounts();
			
			Collection<String> nGram = this.getNgramCountPath(l);
			//System.out.println("l : " + l + ", " +  nGram);
			
			for (String path :  nGram) {
				ngCounts.readNgramCountsFile(path);
			} //Y faudrait faire au cas où y a plusieurs modèles par langues
			lm.setNgramCounts(ngCounts);
			lms.put(l, lm);
		}
	}
	
	@Override
	public String recognizeSentenceLanguage(String sentence) {
		
		Set<String> lmsKeys = lms.keySet();
		//System.out.println("test : " + lms.keySet());
		double resProb = 0, minProb = 1;
		String resLang = null;
		for (String l : lmsKeys) {
			//System.out.println("lang : " + l + ", prob : " + lms.get(l).getSentenceProb(sentence));
			if (lms.get(l).getSentenceProb(sentence) > resProb) {
				resProb = lms.get(l).getSentenceProb(sentence);
				resLang = l;
			}
			if (lms.get(l).getSentenceProb(sentence) < minProb) {
				minProb = lms.get(l).getSentenceProb(sentence);
			}
		}
		
		double seuil = 0.0000001;
		System.out.println(resProb / minProb);
		//resProb / minProb doit être petit pour unknown
		//Attention, plus la phrase est grande, plus resProb / minProb est grand
		
		return resLang;
	}
	
}
