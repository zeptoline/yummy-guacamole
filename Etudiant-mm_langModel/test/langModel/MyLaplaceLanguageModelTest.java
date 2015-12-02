package langModel;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyLaplaceLanguageModelTest {

	@Test
	public void testGetNgramProb() {
		MyLaplaceLanguageModel lm = new MyLaplaceLanguageModel();
		MyNgramCounts nc = new MyNgramCounts();
		nc.readNgramCountsFile("lm/bigram-100-train-en.lm");
		lm.setNgramCounts(nc);
		Double res = 0.008519701810436636;
		assertEquals(res,lm.getNgramProb("the vote"));
	}


	@Test
	public void testGetSentenceProb() {
		MyLaplaceLanguageModel lm = new MyLaplaceLanguageModel();
		MyNgramCounts nc = new MyNgramCounts();
		nc.readNgramCountsFile("lm/bigram-100-train-en.lm");
		lm.setNgramCounts(nc);
		
		Double res = 7.3122511714225256E-9;
		assertEquals(res,lm.getSentenceProb("the vote in accordance"));
	}
}
