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
public class MyLanguageRecognizer1TestPerformance {

	public MyLanguageRecognizer1TestPerformance() {
	}

	@Test
	public void testBaselineLanguageRecognizer() {
		String goldSentPath = "data/gold/gold-sent2.txt";
		String goldLangPath = "data/gold/gold-lang2.txt";

		List<String> lang = new ArrayList<String>();
		/*lang.add("fr");
		lang.add("en");*/
		
		
		
		MyLanguageRecognizer1 baseline = new MyLanguageRecognizer1("lm/fichConfig_bigram-100.txt");
		//baseline.loadNgramCountPath4Lang("lm/fichConfig_bigram-100.txt");
		// or use the following if you want to consider all the languages
		// LanguageRecognizer baseline = new BaselineLanguageRecognizer();

		String hypLangFilePath = "data/test/hyp";
		baseline.recognizeFileLanguage(goldSentPath, hypLangFilePath);
		System.out.printf("System performance = %f\n", Performance.evaluate(goldLangPath, hypLangFilePath));
	}


	@Rule
	public TestName name = new TestName();

	
	@Before
	public void printSeparator()
	{
		System.out.println("\n=== " + name.getMethodName() + " =====================");
	}
}
