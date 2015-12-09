package langReco.reco;


import java.util.List;
import java.util.Random;


/**
 * Class BaselineLanguageRecognizer: a baseline language recognition system that "recognize" the language of a sentence
 * by randomly choosing one language recognized by the recognition system.
 * 
 * @author N. Hernandez and S. Quiniou (2015)
 *
 */
public class BaselineLanguageRecognizer extends LanguageRecognizer {

	/**
	 * Constructor.
	 */
	public BaselineLanguageRecognizer(List<String> lang) {
		super();
		//getLang().clear();
		//getLang().addAll(lang);
	}

	
	@Override
	/**
	 * Method recognizing and returning the language of the given sentence by choosing randomly one
	 * of the languages of the recognition system (the unknown language can also be picked up).
	 * 
	 * @param sentence: the sentence whose language is to recognize.
	 * @return the language of the sentence as recognized by the recognition system.
	 */
	public String recognizeSentenceLanguage(String sentence) {	
		Random rand = null;
		rand = new Random(System.currentTimeMillis());
		return lang.get(rand.nextInt(lang.size()));
	}

}
