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
		MyLanguageRecognizer2 lr = new MyLanguageRecognizer2("lm/bigram-100-train-en.lm");
		lr.loadNgramCountPath4Lang("lm/fichConfig_bigram-100.txt");
		assertEquals(lr.recognizeSentenceLanguage("<s> wir erwarten aber von allen staaten der</s>"), "de");
	}

}

//wir erwarten aber von allen staaten der internationalen
/*1 = 4.4702291589949865E-5*/
/*2 = 2.422315211945266E-7*/ //- 4.446006007E-5
/*3 = 5.699565204577096E-10*/
/*4 = 1.3332316268016599E-12*/
/*5 = -15*/
/*6 = -18*/
/*7 = 1.698829233838048E-20*/
/*8 = 3.733690623819886E-23*/

//