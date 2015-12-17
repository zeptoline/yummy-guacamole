package langReco.reco;

import langModel.MyNgramCounts;

public class generateLangModel {

	public static void creerNgram(String data, String lg, int order){
		String destination = "";
		switch (order) {
		case 1:
			destination = "lm/unigram/unigram-"+lg+".lm";
			break;
		case 2:
			destination = "lm/bigram/bigram-"+lg+".lm";
			break;
		case 3:
			destination = "lm/trigram/trigram-"+lg+".lm";
			break;
		case 4:
			destination = "lm/quadrigram/quadrigram-"+lg+".lm";
		}
		
		MyNgramCounts ngram = new MyNgramCounts();
		ngram.scanTextFile(data, order);
		ngram.writeNgramCountFile(destination);
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
		 * Generation des 
		 */
		for (int i = 0; i < lg.length; i++) {
			creerNgram(data[i], lg[i], 1);
			creerNgram(data[i], lg[i], 2);
			creerNgram(data[i], lg[i], 3);
			creerNgram(data[i], lg[i], 4);
		}
	}
}
