package UPG4;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
/**
* Denna klass modellerar en ordlista (Dictionary). En ordlista
* associerar termer med betydelser. En term kan mappas till flera betydelser.
* Både term och betydelse representeras med klassen Word.
*/
public class Dictionary {
	
	Map<Word,Set<Word>> map;
	Map<Word,Set<Word>> inverseMap;
	public Dictionary() {
		map= new HashMap<Word, Set<Word>>();
	}
		
/**
* Lägger till termen t till ordlistan med betydelsen m. Om termen
* redan är tillagd med angiven betydelse händer ingenting.
*/
public void add(Word t, Word m){
	if(!map.containsKey(t)) {
		Set<Word> newSet = new HashSet<>();
		newSet.add(m);
		map.put(t,  newSet);
		return;
	}
	Set<Word> existing = map.get(t);
	existing.add(m);
	
	//add wt och mt i set<dictionary>
	//kolla om wt finns och ge random hash om inte
}
/**
* Bekvämare sätt att anropa add för 2 strängar
* add(Word, Word).
*/
public void add(String t, String m){
	Word wt = new Word(t);
	Word mt = new Word(m);
	add(wt,mt);
}

public boolean done() {
	return map.keySet().size() == 0;
}
public void remove(Word t) {
	map.remove(t);
}


/**
* Returnerar en icke-null mängd med ordlistans alla termer.
*/

public Set<Word> terms(){
	return map.keySet();
}

/**
* Slår upp och returnerar en mängd av betydelser till t, eller
* null om t inte finns i ordlistan.
*/

public Set<Word> lookup(Word wt){
	return map.get(wt);
}

/**
* Skapar och returnerar en ny ordlista på det motsatta språket, dvs, alla
* betydelser blir termer och alla termer blir betydelser. T.ex. en
* svensk-engelsk ordlista blir efter invertering engelsk-svensk.
*/



public Dictionary inverse() {
	Dictionary inv = new Dictionary();
	for(Word key : map.keySet()) {
		for(Word term : map.get(key)) {
			inv.add(term,  key);
		}
	}
	return inv;
}
	

/**
* Läser in orden från den givna strömmen och lägger dessa i ordlistan.
 * @throws FileNotFoundException 
 * @throws IOException 
*/

public void load(InputStream is) {
	//is = new FileInputStream("/home/martin/eclipse-workspace/Labb2/src/UPG4/ordlista.txt");
	try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
        while (reader.ready()) {
            String line = reader.readLine();
            String[] words = line.split(":");
            add(words[0],words[1]);
            //System.out.println(line);
           
        }

        
    }catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
	
}

/**
* Lagrar ordlistan på den givna strömmen.
 * @throws IOException 
*/

public void save(OutputStream os) throws IOException{
	OutputStreamWriter osw = new OutputStreamWriter(os);
	for(Word key : map.keySet()) {
			for(Word value : map.get(key)) {
				osw.write(key + ":" + value + "\n");
				
			}
	}

	osw.close();
}
}











