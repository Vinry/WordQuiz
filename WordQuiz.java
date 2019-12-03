package UPG4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class WordQuiz extends Dictionary{
	Dictionary dict;
	

	private Scanner scan = new Scanner(System.in);
	public WordQuiz(Dictionary dictionary) {
		dict = dictionary;

		//kod här
	}
	
	public void runQuiz() throws IOException, FileNotFoundException {

		//kod här
		
		System.out.println("Hej! Välkommen till Wordquiz!");
		//System.out.println("Tryck 1 om du vill lägga till ord i ordlistan och 2 för att spela WordQuiz");
		int result = 0;
		while (!(result == 2 || result == 3)) {
			result  = showMenu();
			switch (result) {
			case 1:
				System.out.println("Skriv ord:word");
				OutputStream os = new FileOutputStream ("/home/martin/eclipse-workspace/Labb2/src/UPG4/ordlista.txt");
				String line = scan.nextLine();
				String[] words = line.split(":");
				this.dict.add(words[0],words[1]);
				dict.save(os);				
				break;
				
			case 2:
				System.out.println("Nu kör vi!");
				InputStream is = new FileInputStream("/home/martin/eclipse-workspace/Labb2/src/UPG4/ordlista.txt");
				dict.load(is);
				break;
				
			case 3:
				System.out.println("du klickade 3");
				//InputStream is2 = new FileInputStream("/home/martin/eclipse-workspace/Labb2/src/UPG4/ordlista.txt");
				//dict.load(is2);
				WordQuiz wq2 = new WordQuiz(dict.inverse());
				//wq2.runQuiz();
				dict = dict.inverse();
				
				
				break;
				
			default:
				System.out.println("Använd 1, 2 eller 3");
			}
		}
		int points = 0;
		int felsvar = 0;
		List<Word> toRemove = new ArrayList<>();
		
		while(!dict.done()) {
			
		for(Word term : dict.terms()) {
			//System.out.println("Översätt: ");
			System.out.println(term.toString() + " =");
			Word input = new Word(scan.nextLine());
			//System.out.println("The Value is: " + term); 
			//System.out.println("The Key is: " + dict.lookup(term)); 
			for (Word term2 : dict.lookup(term)) {
				//System.out.println("Du skrev: " + input);
				if (input.equals(term2)) {
					System.out.println("rätt");
					points++;
					//dict.map.remove(term);
					//System.out.println(dict.map);
					toRemove.add(term);
				}else {
					System.out.println("fel");
					felsvar++;
				}
			}
			

			
			
		}
		for (Word t: toRemove) {
			dict.remove(t);
			}
		
		System.out.println("du hade " + points + " rätt och " + felsvar + " fel.");
		System.out.println("Vi kör igen!");
		//runQuiz();
		}
		runQuiz();
		
	}
	private static int showMenu() {
		
		//felhantring. Vi ger användaren möjlighet att försöka om. 
		boolean cont = true;
		int choice = -1;
		while(cont) {
			System.out.println("1. Lägg till ord i ordlistan");
			System.out.println("2. Spela WordQuiz!");
			System.out.println("3. Spela inverse");

			try {
			    Scanner input = new Scanner(System.in);
			    choice = input.nextInt();
			    cont = false;
			
			}
			catch (InputMismatchException ex) {
				System.out.println("Please enter a valid choice!");
			}
		}
		return choice;

	}
	public static void main(String[] args) throws IOException, FileNotFoundException {
		//använder /home/martin/eclipse-workspace/Labb2/src/UPG4/ordlista.txt
		System.out.println("Laddar ordlista.txt ...");
		Scanner scan = new Scanner(System.in);
		Dictionary sweng = new Dictionary();
		//sweng.load(new FileInputStream("hej.txt"));
		WordQuiz wq = new WordQuiz(sweng);
		wq.runQuiz();
		
		
		// Skapa en tom ordlista på ngt sätt och fyll den med ord. Dictionary
	//	Dictionary = ...;
		/*Dictionary sweng = new Dictionary();
		sweng.add("hej", "hello");
		sweng.add("hej", "hi");
		sweng.add("hej", "hey");
		sweng.add("godnatt", "good night");
		sweng.add("nattinatti", "good night");
		sweng.add("fågel", "bird");
		sweng.add("hund", "dog");
		sweng.add("katt", "cat");
		WordQuiz wq = new WordQuiz(sweng);
		wq.runQuiz();*/
	}

}

