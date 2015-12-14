package langModel;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Class MyNgramCounts: class implementing the interface NgramCounts. 
 * 
 * @author Cédric Berland & Nathan Maraval (2015)
 *
 */
public class MyNgramCounts implements NgramCounts {
	/**
	 * The maximal order of the n-gram counts.
	 */
	protected int order;

	/**
	 * The map containing the counts of each n-gram.
	 */
	protected Map<String,Integer> ngramCounts;

	/**
	 * The total number of words in the corpus.
	 * In practice, the total number of words will be increased when parsing a corpus 
	 * or when parsing a NgramCounts file (only if the ngram encountered is a unigram one).
	 */
	protected int nbWordsTotal;
	
	
	/**
	 * Constructor.
	 */
	public MyNgramCounts(){
		order = -1;
		ngramCounts = new HashMap<String,Integer>();
		nbWordsTotal = 0;
	}


	/**
	 * Setter of the maximal order of the ngrams considered.
	 * 
	 * In practice, the method will be called when parsing the training corpus, 
	 * or when parsing the NgramCounts file (using the maximal n-gram length encountered).
	 * 
	 * @param order the maximal order of n-grams considered.
	 */
	private void setMaximalOrder (int order) {
		this.order = order;
	}

	/**
	 * Getter of the Maximal Order of the ngrams
	 * 
	 * @return the maximal order of n-grams
	 */
	@Override
	public int getMaximalOrder() {
		return this.order;
	}

	/**
	 * Give the numbers of n-grams
	 * 
	 * @return the numbers of n-grams
	 */
	@Override
	public int getNgramCounterSize() {
		return ngramCounts.size();
	}

	/**
	 * Give the total number of words, in all n-grams
	 * 
	 * @return the total number of words in all n-grams
	 */
	@Override
	public int getTotalWordNumber(){
		return nbWordsTotal;
	}
	
	/**
	 * Returns all the n-grams
	 * 
	 * @return Returns all the n-grams
	 */
	@Override
	public Set<String> getNgrams() {
		return ngramCounts.keySet();
	}

	/**
	 * Return the occurence of the n-gram
	 * 
	 * @param ngram The n-gram to be counted
	 * @return the counts of the n-gram
	 */
	@Override
	public int getCounts(String ngram) {
		if (ngramCounts.containsKey(ngram)) 
			return ngramCounts.get(ngram);
		return 0;
	}
	
	/**
	 * Add a new n-gram, or add to the number of occurence 
	 * 
	 * @param ngram The n-gram to add
	 */
	@Override
	public void incCounts(String ngram) {
		ngramCounts.put(ngram, new Integer(getCounts(ngram)+1));
		if(NgramUtil.getSequenceSize(ngram) == 1)
			nbWordsTotal ++;
	}

	/**
	 * Change the count/occurence of a given n-gram
	 * 
	 * @param ngram The n-gram to be changed
	 * @param counts The new counts of the n-gram
	 */
	@Override
	public void setCounts(String ngram, int counts) {
		ngramCounts.put(ngram, new Integer(counts));
		if(NgramUtil.getSequenceSize(ngram) == 1)
			nbWordsTotal += counts;
	}

	/**
	 * Read a String and add it's value to the Map of n-gram
	 * 
	 * @param text The String to be read (can have multiple n-gram seperated by a line-break)
	 * @param maximalOrder The new maximal Order of the n-gram
	 */
	@Override
	public void scanTextString(String text, int maximalOrder) {
		if(maximalOrder > getMaximalOrder())
			setMaximalOrder(maximalOrder);
		String[] sentences = text.split("\\n");
		for (String sentence : sentences) {
			List<String> ngramList = NgramUtil.generateNgrams(sentence, 1, getMaximalOrder());
			for (String ngram : ngramList) 
				incCounts(ngram);
		}
	}

	/**
	 * Read a file and add it's value to the Map of n-gram
	 * 
	 * @param filePath The file to be read (can have multiple n-gram seperated by a line-break)
	 * @param maximalOrder The new maximal Order of the n-gram
	 */
	@Override
	public void scanTextFile(String filePath, int maximalOrder) {
		if(maximalOrder > getMaximalOrder())
			setMaximalOrder(maximalOrder);
		List<String> sentences = MiscUtil.readTextFileAsStringList(filePath);
		for (String sentence : sentences) {
			List<String> ngramList = NgramUtil.generateNgrams(sentence, 1, getMaximalOrder());
			for (String ngram : ngramList) 
				incCounts(ngram);
		}
	}

	/**
	 * Write the HashMap of n-gram to a file
	 * @param filePath the path to the file to be written
	 */
	@Override
	public void writeNgramCountFile(String filePath) {
		StringBuffer ngramCountStringBuffer = new StringBuffer();
		
		for (String ngram : getNgrams()) {
			ngramCountStringBuffer.append(ngram); 

			ngramCountStringBuffer.append("\t");

			ngramCountStringBuffer.append(getCounts(ngram)); 

			ngramCountStringBuffer.append("\n");
		}
		
		MiscUtil.writeFile(ngramCountStringBuffer.toString(), filePath, false);
	}
	

	/**
	 * Read a file and add it's value to the Map of n-gram. The maximal order is not given, so a condition is made to get it.
	 * 
	 * @param filePath The file to be read (can have multiple n-gram seperated by a line-break)
	 */
	@Override
	public void readNgramCountsFile(String filePath) {
		List<String> ngramCountList = MiscUtil.readTextFileAsStringList(filePath);
		for (String ngramCount : ngramCountList) {
			String [] ngramCountArray = ngramCount.split("\\t");
			setCounts(ngramCountArray[0], Integer.parseInt(ngramCountArray[1]));
			int currentNgramSize = NgramUtil.getSequenceSize(ngramCountArray[0]);
			if (getMaximalOrder() < currentNgramSize) 
				setMaximalOrder(currentNgramSize);
		}
	}

}
