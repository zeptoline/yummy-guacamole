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
		MyLanguageRecognizer2 lr = new MyLanguageRecognizer2("lm/fichConfig_bigram-100.txt");
		lr.loadNgramCountPath4Lang("lm/fichConfig_bigram-100.txt");
		assertEquals(lr.recognizeSentenceLanguage("<s> turklāt , lai vienkāršotu procesu , priekšlikumā ir noteikts tikai viens jauns pārbaudes veids un nekādā veidā nav pieprasīts dalībvalstīm mainīt to administratīvās sistēmas . </s>"), "lv");
	}

}
