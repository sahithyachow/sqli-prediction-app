package com;
import java.io.FileReader;
import weka.core.Instances;
import weka.classifiers.Evaluation;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import weka.core.Utils;
import weka.classifiers.functions.Logistic;
public class LogisticAlgorithm {
	static Instances train;
	static int lastIndex;
	static Evaluation eval;
	static Logistic nn;
	static double acc;
public static void main(String args[])throws Exception{
	System.out.println(train("dataset/dataset.arff"));
	test("dataset/test.txt");
}
public static String train(String trainFile){
	StringBuilder sb = new StringBuilder();
	try{
		FileReader reader = new FileReader(trainFile); 
		train = new Instances(reader);
		lastIndex = train.numAttributes() - 1;
		train.setClassIndex(lastIndex);

		nn = new Logistic();
		//nn.setOptions(Utils.splitOptions("-S 0 -K 2 -D 3 -G 0.0 -R 0.0 -N 0.5 -M 40.0 -C 1.0 -E 0.001 -P 0.1 -seed 1"));
	 
		nn.buildClassifier(train);
		eval = new Evaluation(train);
		eval.crossValidateModel(nn, train,10, new Random(1));
		sb.append(eval.toSummaryString("\nResults\n======\n", true)+"\n");
		sb.append(eval.toClassDetailsString()+"\n");
		sb.append("\n"+eval.toMatrixString()+"\n");
		String results = nn.toString().trim();
		sb.append(results+"\n");
		acc = eval.pctCorrect();
		sb.append("Logistic Regression Accuracy : "+acc+"\n\n");
	}catch(Exception e){
		e.printStackTrace();
	}
	return sb.toString();
}
public static void updateFile(String test){
	try{
		BufferedReader br = new BufferedReader(new FileReader(test));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line = br.readLine())!=null){
			line = line.trim();
			if(line.length() > 0){
				if(line.indexOf("label") != -1){
					String arr[] = line.split("\\s+");
					sb.append(arr[0]+" "+arr[1]+" {norm,anom}"+System.getProperty("line.separator"));
				} else {
					sb.append(line+System.getProperty("line.separator"));
				}
			}
		}
		br.close();
		FileWriter fw = new FileWriter(test);
		fw.write(sb.toString());
		fw.close();
	}catch(Exception e){
		e.printStackTrace();
	}
}
public static String test(String testFile){
	StringBuilder sb = new StringBuilder();
	try{
		updateFile(testFile);
		FileReader reader = new FileReader(testFile); 
		Instances test = new Instances(reader);
		test.setClassIndex(test.numAttributes() - 1);
		for(int i=0; i<test.numInstances(); i++) {
			double index = nn.classifyInstance(test.instance(i));
			String className = train.attribute(lastIndex).value((int)index);
			sb.append(test.instance(i).toString()+" Predicted as "+className);
			System.out.println(className);
			
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return sb.toString();
}
}