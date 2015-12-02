package langReco.reco;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class MyLanguageRecognizer1Test {

	public MyLanguageRecognizer1Test() {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRecognizeSentenceLanguage() {
		MyLanguageRecognizer1 lr = new MyLanguageRecognizer1("lm/fichConfig_bigram-100.txt");
		lr.loadNgramCountPath4Lang("lm/fichConfig_bigram-100.txt");
		assertEquals(lr.recognizeSentenceLanguage("<s> the vote in accordance </s>"), "en");
	}

}
