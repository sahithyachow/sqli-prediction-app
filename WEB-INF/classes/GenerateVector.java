package com;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
public class GenerateVector{
	
	static ArrayList<String> list = new ArrayList<String>();
	static int length = 0;
	public static String vector_data; 

public static String getVector(String path,String query) {
	StringBuilder sb = new StringBuilder();
	try{
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = null;
		while((line = br.readLine()) != null){
			line = line.trim();
			if(line.length() > 0) {
				sb.append(line+System.getProperty("line.separator"));
			}
		}
		br.close();
		for(int j=0;j<length;j++){
			if(j < query.length()){
				int code = (int)query.charAt(j);
				sb.append(code+",");
			} else {
				sb.append("0,");
			}
		}
		sb.append("?"+System.getProperty("line.separator"));
		

	}catch(Exception e){
		e.printStackTrace();
	}
	return sb.toString();
}

public static void generateVector(String file) {
	try{
		list.clear();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();
		StringBuilder sb = new StringBuilder();
		while((line = br.readLine()) != null){
			line = line.trim();
			if(line.length() > 0) {
				list.add(line);
				String arr[] = line.split("@");
				if(arr[0].length() > length)
					length = arr[0].length();
			}
		}
		br.close();
		for(int i=0;i<length;i++){
			sb.append("F"+i+",");
		}
		sb.append("class"+System.getProperty("line.separator"));
		for(int i=0;i<list.size();i++){
			String arr[] = list.get(i).split("@");
			for(int j=0;j<length;j++){
				if(j < arr[0].length()){
					int code = (int)arr[0].charAt(j);
					sb.append(code+",");
				} else {
					sb.append("0,");
				}
			}
			sb.append(arr[1]+System.getProperty("line.separator"));
		}
		FileWriter fw = new FileWriter(file);
		fw.write(sb.toString());
		fw.close();
		vector_data = sb.toString();
	}catch(Exception e){
		e.printStackTrace();
	}
}
/*public static void main(String args[]){
	generateVector("dataset.txt");
	String arr[] = {"select * from emp where name='a' and dept='economy'","select * from emp","select * from temp where emp=2009","1') where 2330=2330 and (select * from (select(sleep(5)))fzno)","1)) and 2006=2006"};
	SVM.train("vector.arff");
	for(int i=0;i<arr.length;i++){
		getVector(arr[i]);
		SVM.test("test.txt");
	}
}*/
}