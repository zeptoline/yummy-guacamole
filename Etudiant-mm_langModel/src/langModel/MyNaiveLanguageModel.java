package langModel;


/**
 * Class MyNaiveLanguageModel: class implementing the interface LanguageModel by creating a naive language model,
 * i.e. a n-gram language model with no smoothing.
 * 
 * @author ... (2015)
 *
 */
public class MyNaiveLanguageModel implements LanguageModel {
	/**
	 * The NgramCounts corresponding to the language model.
	 */
	protected NgramCounts ngramCounts;
	
	/**
	 * The vocabulary of the language model.
	 */
	protected Vocabulary vocabulary;
	
	
	/**
	 * Constructor.
	 */
	public MyNaiveLanguageModel(){
		ngramCounts = new MyNgramCounts();
		vocabulary = new MyVocabulary();
	}
	

	@Override
	public void setNgramCounts(NgramCounts ngramCounts) {
		this.ngramCounts = ngramCounts;
		vocabulary.scanNgramSet(ngramCounts.getNgrams());
	}

	@Override
	public int getLMOrder() {
		return ngramCounts.getMaximalOrder();
	}

	@Override
	public int getVocabularySize() {
		return vocabulary.getSize();
	}

	@Override
	public Double getNgramProb(String ngram) {
		double nepnep = 0.0;
		
		
		
		return nepnep;
	}

	@Override
	public Double getSentenceProb(String sentence) {
		double nepnep = 0.0;
		
		
		
		return nepnep;
	}

}
