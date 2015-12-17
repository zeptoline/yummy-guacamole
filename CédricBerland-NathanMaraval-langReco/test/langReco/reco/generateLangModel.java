package langReco.reco;

import langModel.MiscUtil;
import langModel.MyNgramCounts;

public class generateLangModel {

	public static void creerNgram(String data, String lg, int order){
		String destination = "";
		String ngramS = "";
		switch (order) {
		case 1:
			ngramS = "uni";
			destination = "lm/unigram/unigram-"+lg+".lm";
			break;
		case 2:
			ngramS = "bi";
			destination = "lm/bigram/bigram-"+lg+".lm";
			break;
		case 3:
			ngramS = "tri";
			destination = "lm/trigram/trigram-"+lg+".lm";
			break;
		case 4:
			ngramS = "quadri";
			destination = "lm/quadrigram/quadrigram-"+lg+".lm";
			break;
		}
		
		MyNgramCounts ngram = new MyNgramCounts();
		ngram.scanTextFile(data, order);
		ngram.writeNgramCountFile(destination);
		
		MiscUtil.writeFile(lg+" "+lg+"_"+ngramS+" lm/"+ngramS+"gram/"+ngramS+"gram-"+lg+".lm\n", "lm/myFishConfig_"+ngramS+"gram-100.txt", true);
	}


	public static void main(String[] args) {
		String dataEn = "data/train/train-en.txt";
		String dataDe = "data/train/train-de.txt";
		String dataIt = "data/train/train-it.txt";
		String dataEs = "data/train/train-es.txt";
		String dataEt = "data/train/train-et.txt";
		String dataCs = "data/train/train-cs.txt";
		String dataLv = "data/train/train-lv.txt";
		String dataLn = "data/train/train-nl.txt";
		String dataPt = "data/train/train-pt.txt";
		String dataSk = "data/train/train-sk.txt";

		String[] data = {dataEn, dataDe, dataIt, dataEs, dataEt, dataCs, dataLv, dataLn, dataPt, dataSk};
		String[] lg = {"en", "de", "it", "es", "et", "cs", "lv", "nl", "pt", "sk"};

		/*
		 * Generation des bigrammes à quadrigramme pour chaque langue
		 */
		

		MiscUtil.writeFile("", "lm/myFishConfig_unigram-100.txt", false);
		MiscUtil.writeFile("", "lm/myFishConfig_bigram-100.txt", false);
		MiscUtil.writeFile("", "lm/myFishConfig_trigram-100.txt", false);
		MiscUtil.writeFile("", "lm/myFishConfig_quadrigram-100.txt", false);
		
		
		for (int i = 0; i < lg.length; i++) {
			System.out.println(i + " " + (lg.length - 1));
			creerNgram(data[i], lg[i], 1);
			creerNgram(data[i], lg[i], 2);
			creerNgram(data[i], lg[i], 3);
			creerNgram(data[i], lg[i], 4);
		}
	}
}
