package UPG4;
/**
* Denna klass representerar ett ord, vilket består av en teckensekvens kallad
text.
*/

public class Word {
	private String text;
	/**
	* Skapar ett nytt ord med den givna texten.
	*/
		public Word(String text) {
			this.text = text;
			
		}
		/**
		* Jämför detta ord med det specificerade objektet. Resultatet
		* true om och endast om obj också är ett ord (Word) och har
		* samma text.
		*/
		public boolean equals(Object obj) {
			if(!(obj instanceof Word)) {
				return false;
			}
			Word objWord = (Word) obj;
			return objWord.toString().contentEquals(this.toString());
			//return objWord.hashCode() == this.hashCode();
		}
		/**
		* Returnerar hashkoden för detta ord beräknat på ordets text.
		*/
		public int hashCode() {
			return text.hashCode();
			
		}
		/**
		* Returnerar texten för detta ord.
		*/
		public String toString() {
			return text;
			
		}
}
