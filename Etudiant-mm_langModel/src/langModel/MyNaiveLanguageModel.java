package langModel;


/**
 * Class MyNaiveLanguageModel: class implementing the interface LanguageModel by creating a naive language model,
 * i.e. a n-gram language model with no smoothing.
 * 
 * @author Cédric Berland & Nathan Maraval (2015)
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
	
	/**
	 * Setter for the ngram count
	 * Make the vocabulary read the new ngram set
	 * @param ngramCounts The new Ngram Count
	 */
	@Override
	public void setNgramCounts(NgramCounts ngramCounts) {
		this.ngramCounts = ngramCounts;
		vocabulary.scanNgramSet(ngramCounts.getNgrams());
	}

	/**
	 * Give the Maximal Order of the N-gram Count
	 * @return the Maximal Order
	 */
	@Override
	public int getLMOrder() {
		return ngramCounts.getMaximalOrder();
	}

	/**
	 * Give the number of every different words
	 * @return the size of the vocabulary
	 */
	@Override
	public int getVocabularySize() {
		return vocabulary.getSize();
	}

	/**
	 * Calculate the probability of a single n-gram
	 * @param ngram The n-gram to consider
	 * @return A float giving the probability of the ngram
	 */
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

	/**
	 * Cut the sentence in ngram and calculate the probability for each, and then multiply all of them
	 * @param sentence The sentence to consider
	 * @return the probability of the whole sentence
	 */
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