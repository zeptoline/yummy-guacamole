package langModel;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyNaiveLanguageModelTest {

	@Test
	public void testMyNaiveLanguageModel() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetNgramCounts() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLMOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetVocabularySize() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNgramProb() {
		MyNaiveLanguageModel lm = new MyNaiveLanguageModel();
		MyNgramCounts nc = new MyNgramCounts();
		nc.readNgramCountsFile("lm/bigram-100-train-en.lm");
		lm.setNgramCounts(nc);
		Double res = 0.0472972972972973;
		assertEquals(res,lm.getNgramProb("the vote"));
	}

	@Test
	public void testGetSentenceProb() {
		MyNaiveLanguageModel lm = new MyNaiveLanguageModel();
		MyNgramCounts nc = new MyNgramCounts();
		nc.readNgramCountsFile("lm/bigram-100-train-en.lm");
		lm.setNgramCounts(nc);
		
		Double res = 5.7093919497573504E-5;
		assertEquals(res,lm.getSentenceProb("the vote in accordance"));
	}

}
