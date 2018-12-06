package repairPrivKey;
import java.math.BigInteger;
import java.util.Arrays;


/****************************************************************************************
*											*
*	 Hauptklasse die das Programm verwaltet						*
*											*
*****************************************************************************************/



public class Run 
{

	final static  char[] 	base58 = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();
	static        byte[] 	hash;					// der Hash160 der Bitcoin Adresse
	public static String 	ausgabe = "";				// der Ausgabestring 
	static	      boolean 	fund;					// wird gesetzt bei einer Übereinstimmung
	public static boolean	stop;					// wird gesetzt wenn der Suchvorgang abgebrochen wird
	public static boolean 	threadIsRun;				// wird gesetzt wenn der Thread läuft
	
	
	
	

	
// Der Suchvorgang wird in einem eigenem Thrad gestartet	
public static void go()
{
 Runnable runnable = new Runnable() 
 {
	public void run() 
	{
		threadIsRun = true;
		GUI.GUI.progressBar.setVisible(true);
		GUI.GUI.btn_start.setText("Abbrechen");			
		try 
		{
			BitcoinAddr bAdr = new BitcoinAddr(GUI.GUI.txt_BitcoinAdr.getText(), "f9beb4d9");
			hash = bAdr.getHash160_byte();
			ausgabe = "";	
			String priv_e = GUI.GUI.txt_PrivateKey.getText();			
			if(priv_e.length()==51)						
			{
			 char[] prv = priv_e.toCharArray();				
			 int[] indexAllSearch = allIndexOf(priv_e);			
			 int len = indexAllSearch.length;					
			 calcComputingTime(len);					
			 for(int i=0;i<len;i++)  prv[indexAllSearch[i]] = base58[0];	
			 fund = false;
			 increment(prv,indexAllSearch,0);
			 if(fund==false) ausgabe = "Keine Übereinstimmung gefunden.";
			}	
			else ausgabe = "Der Priv.Key muss genau 51 Zeichen lang sein! (base58 Format)";
		}
		catch (IllegalArgumentException e)
		{
		 ausgabe = "Fehler im Eingabefeld Bitcoin Adresse! Es wurde keine gültige Bitcoin Adresse erkannt.\n"+e.getMessage();
		}
		GUI.GUI.lbl_Info.setText("");	
		GUI.GUI.txt_Ausgabe.setText(ausgabe);
		GUI.GUI.btn_start.setText("Suche starten");
		GUI.GUI.progressBar.setVisible(false);
		stop = false;
		threadIsRun = false;  
	};
 };
 Thread thread = new Thread(runnable);
 if(stop==false && threadIsRun==false) thread.start(); 
 else stop=true;	
}
	


// Berechnet die Laufzeit und gibt sie im Infofenster aus
private static void calcComputingTime(int len)
{
	final BigInteger SIXTY = new BigInteger("60");
	final BigInteger time_sec = new BigInteger("18000");			// Die geschätzte Laufzeit/sec
	String einheit = " sec.";
	BigInteger base = new BigInteger("58");	
	BigInteger count 	= base.pow(len);				// Anzahl der Permutationen
	BigInteger timeG 	= count.divide(time_sec);			// Die geschätzte Gesamtlaufzeit
	if(timeG.compareTo(SIXTY) > 0) 
	{
		timeG = timeG.divide(SIXTY); einheit = " min.";  
		if(timeG.compareTo(SIXTY) > 0) 
		{ 
			timeG = timeG.divide(SIXTY); einheit = " h.";
			if(timeG.compareTo(new BigInteger("24")) > 0) 
			{ 
				timeG = timeG.divide(new BigInteger("24")); einheit = " Tage";  
				if(timeG.compareTo(new BigInteger("365")) > 0) 
				{ 
					timeG = timeG.divide(new BigInteger("365")); einheit = " Jahre";
				}
			}
		}
	}	
	GUI.GUI.lbl_Info.setText("Anzahl Permutationen: "+count+"\nGeschätzte Laufzeit: "+timeG + einheit);
}



// Incrementiert alle Base58 Zeichen an den stellen "pos" des Char-Array durch.
// Rekursive Methode
private static void increment(char[] c, int[] indexAllSearch, int pos)
{
	if(indexAllSearch.length==0) {fund = true; return;}
	if(pos==1000) return;
	if(stop==true) return;
	if(fund) return;	
	for(int i=0;i<base58.length;i++)
	{
		c[indexAllSearch[pos]] = base58[i];
		if(pos<indexAllSearch.length-1) increment(c,indexAllSearch,pos+1);
		else
		{		
			if(System.currentTimeMillis() % 50 == 0)  GUI.GUI.txt_Ausgabe.setText(String.valueOf(c));
			try
			{				
				PrvKey p = new PrvKey(String.valueOf(c),"f9beb4d9");
				if(Arrays.equals(hash, p.getHash160().get()))
				{	ausgabe = (p.getBase58PrivKey());
					pos = 1000;
					fund = true;
					return;
				}	
			}
			catch(IllegalArgumentException e) {}	
		}
	}
}



// Gibt alle Positionen zurück, an denen das fehlende Zeichen als "_" eingegeben wurde.
private static int[] allIndexOf(String in)
{
	int z=0;			
	for(int i=0;i<in.length();i++)	if(in.charAt(i)=='_') z++;
	int[] erg = new int[z];			
	z=0;
	for(int i=0;i<in.length();i++)
	{
		if(in.charAt(i)=='_') {erg[z]=i; z++;}
	}
	return erg;
}	
}
