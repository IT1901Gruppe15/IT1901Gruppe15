package klasser;

public class Utstyr {
		int utstyrsID;
		int koieID;
		String type;
		String innkjøpsdato;
		boolean status;
		
		private Utstyr(int utstyrsID, int koieID, String type, String innkjøpsdato, boolean status){
			this.utstyrsID = utstyrsID;
			this.koieID = koieID;
			this.type = type;
			this.innkjøpsdato = innkjøpsdato;
			this.status = status;
		}
		
		public int getUtstyrsID(){
			return this.utstyrsID;
		}
		
		public void setUtstyrsID(int utstyrsID){
			this.utstyrsID = utstyrsID;
		}
		
		public int getKoieID(){
			return this.koieID;
		}
		
		public void setKoieID(int koieID){
			this.koieID = koieID;
		}
		
		public String getType(){
			return this.type;
		}
		
		public void setType(String type){
			this.type = type;
		}
		
		public String getInnkjøpsdato(){
			return this.innkjøpsdato;
		}
		
		public void setInnkjøpsdato(String innkjøpsdato){
			this.innkjøpsdato = innkjøpsdato;
		}
		
		public boolean getStatus(){
			return this.status;
		}
		
		public void setStatus(boolean status){
			this.status = status;
		}
		

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
