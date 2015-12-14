package langModel;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * Class MyVocabulary: class implementing the interface Vocabulary.
 * 
 * @author Cédric Berland & Nathan Maraval (2015)
 *
 */
public class MyVocabulary implements Vocabulary {
	/**
	 * The set of words corresponding to the vocabulary.
	 */
	protected Set<String> vocabulary;

	
	/**
	 * Constructor.
	 */
	public MyVocabulary(){
		vocabulary = new TreeSet<String>();
		
	}
	
	/**
	 * Getter for the size
	 * @return Return the size of the vocabulary
	 */
	@Override
	public int getSize() {
		return vocabulary.size();
	}

	/**
	 * Getter for all the words in the vocabulary
	 * @return All the words presents in the vocabulary
	 */
	@Override
	public Set<String> getWords() {
		return (Set<String>) vocabulary;
	}

	/**
	 * Search if a word is contained in the vocabulary
	 * @param word The word to search
	 * @return Boolean, true if the word is present, false otherwise
	 */
	@Override
	public boolean contains(String word) {
		boolean ret = (vocabulary.contains(word)) ? true : false;
		return ret;
	}

	/**
	 * Add a word to the vocabulary
	 * @param The word to add
	 */
	@Override
	public void addWord(String word) {
		vocabulary.add(word);		
	}

	/**
	 * Remove a word to the vocabulary
	 * @param word The word to remove
	 */
	@Override
	public void removeWord(String word) {
		vocabulary.remove(word);		
	}

	/**
	 * Add each words of an n-gram
	 * @param ngramSet The n-grams to read
	 */
	@Override
	public void scanNgramSet(Set<String> ngramSet) {
		for (String string : ngramSet) {
			String[] sep = string.split("\\s+");
			for (String string2 : sep) {
				addWord(string2);
			}
		}
		
	}

	/**
	 * Read a file to get the words
	 * @param filePath The path of the file. It can contains multiple values, separated by a linebreak
	 */
	@Override
	public void readVocabularyFile(String filePath) {
		List<String> nep = MiscUtil.readTextFileAsStringList(filePath);
		for (String string : nep) {
			addWord(string);
		}
		
	}

	/**
	 * Write the vocabulary to a file
	 * @param filePath The path to the file to write.
	 */
	@Override
	public void writeVocabularyFile(String filePath) {
		MiscUtil.writeFile("", filePath, false);
		for (String string : vocabulary) {
			MiscUtil.writeFile(string, filePath, true);
			
		}
		
	}

}