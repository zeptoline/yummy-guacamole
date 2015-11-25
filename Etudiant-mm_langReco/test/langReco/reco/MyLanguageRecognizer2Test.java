package langReco.reco;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class MyLanguageRecognizer2Test {

	public MyLanguageRecognizer2Test() {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRecognizeSentenceLanguage() {
		MyLanguageRecognizer2 lr = new MyLanguageRecognizer2();
		lr.loadNgramCountPath4Lang("lm/fichConfig_bigram-100.txt");
		System.out.println(lr.getNgramCountNames("es"));
		assertEquals(lr.recognizeSentenceLanguage("<s> in this european parliament , we should particularly regret the jailing of nine of the party 's elected mayors and the banning of two of its mps . </s>"), "en");
	}

}
