package langModel;


/**
 * Class MyLaplaceLanguageModel: class inheriting the class MyNaiveLanguageModel by creating 
 * a n-gram language model using a Laplace smoothing.
 * 
 * @author Cédric Berland & Nathan Maraval (2015)
 *
 */
public class MyLaplaceLanguageModel extends MyNaiveLanguageModel {

	/**
	 * Override the method from MyNaiveLangage to adapt it to a Laplace methode
	 * Give a weight to every ngram, to not get a zero probability
	 * @param ngram The ngram to consider
	 * @return The probability of the ngram, weighted.
	 */
	@Override
	public Double getNgramProb(String ngram) {

		Double nepnep = 0.0;
		
		double occu = ngramCounts.getCounts(ngram) + 1;
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
		
		somme = somme + this.getVocabularySize();
		
		nepnep = Double.valueOf((occu /somme));

		return nepnep;
	}
}
