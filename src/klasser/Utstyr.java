package klasser;

public class Utstyr {
		int utstyrsID;
		int koieID;
		String type;
		String innkj�psdato;
		boolean status;
		
		private Utstyr(int utstyrsID, int koieID, String type, String innkj�psdato, boolean status){
			this.utstyrsID = utstyrsID;
			this.koieID = koieID;
			this.type = type;
			this.innkj�psdato = innkj�psdato;
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
		
		public String getInnkj�psdato(){
			return this.innkj�psdato;
		}
		
		public void setInnkj�psdato(String innkj�psdato){
			this.innkj�psdato = innkj�psdato;
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
