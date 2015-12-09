package langReco.reco;

import java.util.ArrayList;
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
			
			//LinkedList<String> nGram = new LinkedList<String>(this.getNgramCountPath(l));
			//System.out.println("l : " + l + ", " +  nGram);
			
			ArrayList<String>nGram = new ArrayList<String>();
			nGram.add("cs");
			nGram.add("de");
			nGram.add("en");
			nGram.add("es");
			nGram.add("et");
			nGram.add("it");
			nGram.add("lv");
			nGram.add("nl");
			nGram.add("pt");
			nGram.add("sk");
			nGram.add("unk");
			
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
		double maxProb = 0, minProb = 1;
		String resLang = null;
		for (String l : lmsKeys) {
			//System.out.println("lang : " + l + ", prob : " + lms.get(l).getSentenceProb(sentence));
			if (lms.get(l).getSentenceProb(sentence) > maxProb) {
				maxProb = lms.get(l).getSentenceProb(sentence);
				resLang = l;
			}
			if (lms.get(l).getSentenceProb(sentence) < minProb) {
				minProb = lms.get(l).getSentenceProb(sentence);
			}
		}
		
		double seuil = 0.0000001;
		System.out.println("ResProb : " + maxProb );
		//resProb / minProb doit être petit pour unknown
		//Attention, plus la phrase est grande, plus resProb / minProb est grand
		if ((minProb * 100) / maxProb < 20)
			resLang = "unk";
		
		
		System.out.println("LMOrder : " + lms.get(resLang).getLMOrder());
		System.out.println("NgramProb : " + lms.get(resLang).getNgramProb(sentence));
		
		return resLang;
	}
	
}
