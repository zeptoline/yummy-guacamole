package langModel;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * Class MyVocabulary: class implementing the interface Vocabulary.
 * 
 * @author ... (2015)
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
	
	
	@Override
	public int getSize() {
		return vocabulary.size();
	}

	@Override
	public Set<String> getWords() {
		return (Set<String>) vocabulary;
	}

	@Override
	public boolean contains(String word) {
		boolean ret = (vocabulary.contains(word)) ? true : false;
		return ret;
	}

	@Override
	public void addWord(String word) {
		vocabulary.add(word);		
	}

	@Override
	public void removeWord(String word) {
		vocabulary.remove(word);		
	}

	@Override
	public void scanNgramSet(Set<String> ngramSet) {
		for (String string : ngramSet) {
			String[] sep = string.split("\\s+");
			for (String string2 : sep) {
				addWord(string2);
			}
		}
		
	}

	@Override
	public void readVocabularyFile(String filePath) {
		List<String> nep = MiscUtil.readTextFileAsStringList(filePath);
		for (String string : nep) {
			addWord(string);
		}
		
	}

	@Override
	public void writeVocabularyFile(String filePath) {
		MiscUtil.writeFile("", filePath, false);
		for (String string : vocabulary) {
			MiscUtil.writeFile(string, filePath, true);
			
		}
		
	}

}