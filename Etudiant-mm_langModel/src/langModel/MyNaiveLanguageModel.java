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
		Double nepnep = 0.0;
		
		double occu = ngramCounts.getCounts(ngram);
		int hist_size = getLMOrder();
		double somme;
		
		if (NgramUtil.getSequenceSize(ngram) == 1) {
			somme = this.getVocabularySize();
		} else{
			String histo = NgramUtil.getHistory(ngram, hist_size);
			 somme = ngramCounts.getCounts(histo);
			for (int i = hist_size-1; i > 2 ; i--) {
				histo = NgramUtil.getHistory(histo, i);
				somme = somme + ngramCounts.getCounts(histo);
			}
		}

		nepnep = Double.valueOf((occu /somme));

		
		return nepnep;
	}

	@Override
	public Double getSentenceProb(String sentence) {
		double mult = 1;
		int lmo = getLMOrder();
		for (String nep : NgramUtil.decomposeIntoNgrams(sentence, lmo)) {
			mult = mult * this.getNgramProb(nep);
		}

		return mult;
	}

}
