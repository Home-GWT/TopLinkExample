package com.voituk.jpaexample;

public class MyTest2 {
	
	public static void main(String[] args){
		String sentence = "This is time!";
		char ch = 't';
		int count = 0;
		
		for (int i=0; i<sentence.length(); i++){
			if(sentence.charAt(i) == ch){
				count++;
			}
		}
		
		switch (count) {
		case 0:
			System.out.println("Nothing found");
			break;

		default:
			System.out.println("count="+count);
			break;
		}
	}

}
