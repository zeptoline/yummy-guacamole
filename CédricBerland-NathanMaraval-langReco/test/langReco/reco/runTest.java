package langReco.reco;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import langReco.eval.Performance;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

@SuppressWarnings("unused")
public class runTest {

	public runTest() {
	}

	@Test
	public void testBaselineLanguageRecognizer() {
		String goldSentPath = "data/gold/test-sent.txt";
		String hypLangFilePath1 = "runs/test-lang-hyp1.txt";
		String hypLangFilePath2 = "runs/test-lang-hyp2.txt";

		
		MyLanguageRecognizer2 baseline1 = new MyLanguageRecognizer2();
		MyLanguageRecognizer3 baseline2 = new MyLanguageRecognizer3();

		
		baseline1.recognizeFileLanguage(goldSentPath, hypLangFilePath1);
		baseline2.recognizeFileLanguage(goldSentPath, hypLangFilePath2);
		
		
	
	}


	@Rule
	public TestName name = new TestName();

	
	@Before
	public void printSeparator()
	{
		System.out.println("\n=== " + name.getMethodName() + " =====================");
	}
}
