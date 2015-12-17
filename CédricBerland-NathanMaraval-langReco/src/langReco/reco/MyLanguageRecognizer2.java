package langReco.reco;

import java.util.HashMap;
import java.util.Set;

import langModel.MyLaplaceLanguageModel;
import langModel.MyNgramCounts;
import langModel.NgramUtil;

public class MyLanguageRecognizer2 extends LanguageRecognizer{
	HashMap<String, MyLaplaceLanguageModel> lms = new HashMap<String, MyLaplaceLanguageModel>(); 
	
	
	/**
	 * Constructeur de MyLanguageRecognizer2
	 * Initialise lms avec les langues en clé, et des languages de modéles prets en valeurs
	 * @param nGramPath qui indique le chemin vers le fichier de config
	 */
	public MyLanguageRecognizer2(String nGramPath){
		super();
		//charger le fichier de config pour avoir le chemin de chaque langue
		this.loadNgramCountPath4Lang(nGramPath);
		
		//boucle où l'on associe chaque language à un modèle de langage dans lms
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
	
	/**
	 * Méthode qui donne la langue estimé pour une phrase donnée
	 * @param sentence qui est la phrase dont on cherche la langue
	 * @return la langue qui obtient la plus haute probabilité, ou unk si on estime que la langue est inconnue
	 */
	@Override
	public String recognizeSentenceLanguage(String sentence) {

		//liste de toutes les langues à tester
		Set<String> lmsKeys = lms.keySet();
		
		//On considére de base que la langue est inconnue
		String lang = "unk";
		double probaMax = 0;
		double proba = 0;
		int length = NgramUtil.getSequenceSize(sentence);

		//On teste pour toutes les langues
		for (String l : lmsKeys) {

			proba = lms.get(l).getSentenceProb(sentence);
			double sizeVoca = lms.get(l).getVocabularySize();
			
			//seuil à dépasser pour considérer que la langue n'est pas inconnue
			double seuil = Math.pow((1/sizeVoca), length)*10000;

			if(proba >= seuil){
				if(probaMax < proba){
					probaMax = proba;
					lang = l;
				}
			}
		}
		return lang;
	}

}
