package langReco.reco;

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
			for (String path : getNgramCountPath(l)) {
				
				ngCounts.readNgramCountsFile(path);
			}
			
			lm.setNgramCounts(ngCounts);
			lms.put(l, lm);
		}
	}
	
	@Override
	public String recognizeSentenceLanguage(String sentence) {
		/*
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
		
		//double seuil = 0.0000001;
		//System.out.println("ResProb : " + maxProb );
		//resProb / minProb doit être petit pour unknown
		//Attention, plus la phrase est grande, plus resProb / minProb est grand
		//if ((minProb * 100) / maxProb < 20)
		//	resLang = "unk";
		
		if(!resLang.equals("unk")) {
			System.out.println("LMOrder : " + lms.get(resLang).getLMOrder());
			System.out.println("NgramProb : " + lms.get(resLang).getNgramProb(sentence));
		}
		
		return resLang;*/
		
		 Set<String> lmsKeys = lms.keySet();
	        
	        String lang = "unk";
	        double probaMax = 0;
	        double proba = 0;
	        int sentLen = sentence.split("\\s+").length;
	        double pow3 = Math.pow(10, 3);

	        for (String l : lmsKeys) {
	            
	            proba = lms.get(l).getSentenceProb(sentence);
	            double vocaS = lms.get(l).getVocabularySize();
	            double borneU = Math.pow((1/vocaS), sentLen)*pow3;
	            double borneD = Math.pow((1/vocaS), sentLen);

	            if(!(borneD <= proba && proba <= borneU)){
	                if(probaMax < proba){
	                    probaMax = proba;
	                    lang = l;
	                }
	            }
	        }

	        System.out.println("phrase : "+sentence);
	        System.out.println("langue : "+lang);
	        System.out.println("res : "+proba);
	        
	        return lang;
	}
	
}
