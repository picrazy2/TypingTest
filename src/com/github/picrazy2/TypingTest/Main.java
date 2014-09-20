package com.github.picrazy2.TypingTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Main{

	static final int TIME_IN_MILLIS = 60000;
	static long startTime = 0;
	static long currentTime = 0;
	static Random random = new Random();


	static ArrayList<String> cont = new ArrayList<String>();
	static ArrayList<String> fullCont = new ArrayList<String>();

	static ArrayList<String> order = new ArrayList<String>();

	public static void main(String[] args){


		// TODO Auto-generated method stub
		String line = null;
		int correctCounter = 0;

		File file = new File("bank.txt");
		BufferedReader reader = null;

		try{
			reader = new BufferedReader(new FileReader(file));
			line = reader.readLine();
			while(line!=null){
				cont.add(line);
				fullCont.add(line);
				line = reader.readLine();
			}
		}
		catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(reader!=null){
				try{
					reader.close();
				}
				catch(IOException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println(TIME_IN_MILLIS/1000+ " seconds starts after pressing 1");
		BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));
		boolean start = true;
		int counter = 0;

		while(true){
			try{
				String input = systemIn.readLine();
				if(input.equals("quit")){
					break;
				}
				else if(input.equals("1") && start){
					makeWords();
					start = false;
					startTime = System.currentTimeMillis();
					for(int i = counter; i<counter+5; i++){
						System.out.print(order.get(i%order.size()) + " ");
					}
					System.out.println();
				}else if(!start){
					currentTime = System.currentTimeMillis();
					if(currentTime-startTime>=TIME_IN_MILLIS){
						System.out.println("Time up! Score: "+correctCounter);
						System.out.println("Wrong: "+(counter-correctCounter));
						break;

					}
					else{
						if(input.equals(order.get(counter%order.size()))){
							correctCounter++;
						}
						counter++;
						for(int i = counter; i<counter+5; i++){
							System.out.print(order.get(i%order.size()) + " ");
						}
						System.out.println();

					}
					System.out.print("");
				}
			}
			catch(IOException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}



	}

	public static void makeWords(){
		while(cont.size()!=0){
			int temp = random.nextInt(cont.size());
			order.add(cont.get(temp));
			cont.remove(temp);
		}
	}

}
