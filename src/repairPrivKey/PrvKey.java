package repairPrivKey;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Base64;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.math.ec.ECPoint;





	/********************************************************************************************************
	 *													*
	 *	Nicht statische Klasse die ein Private-Key Object erstellt.					*
	 *	Es sind 3 Konstrukor´s implementiert								*
	 *	-	einer für ByteArray									*
	 *	-	Hex-String 64Zeichen									*
	 *	-	Text-String (2xSHA256)									*
	 *	Dem Konstruktor wird der Priv.Key übergeben.							*
	 *	Dann können alle Key´s in allen möglichen Formaten abgerufen werden.				*
	 *													*
	 *	magig "0b110907" = TestNet									*
	 *	magig "f9beb4d9" = Main-Net									*
	 *													*
	 ********************************************************************************************************/







public class PrvKey 
{
		
	private byte[] privKey;				// Der Priv.Key als 32Byte-Array
	private static String PrivKeyVersionsNr;	// Die Private Key    VersionsNr. "80" = MainNet und "ef" = TestNet
	private String magig;				// TestNet oder MainNet
	
	
	
	
	
	
// ---------------------------------------------------- Konstruktoren ----------------------------------------------------
	
/**	Dem Konstruktor wird der Priv.Key 32Byte übergeben.
*	magig "0b110907" = TestNet	, magig "f9beb4d9" = Main-Net
*	Löst Exception bei falscher Länge aus! */
public PrvKey(byte[] privKey, String magig) throws IllegalArgumentException
{
	this.magig = magig;
	if(magig.equals("0b110907"))PrivKeyVersionsNr	= "ef";						// Auswahl TestNet3
	else			PrivKeyVersionsNr	= "80";						// Auswahl Main-Net

	if(privKey.length != 32)									
	throw new IllegalArgumentException("Error in \"PrvKey\": Byte-length is not 32Byte!");
	this.privKey = privKey;	
}



/**	Dem Konstruktor wird der Priv.Key als String übergeben, das richtige Format wird selbst erkannt.
*	Es sind folgene Formate möglich: Hex, Base58, Base58-Compressed, Base64, Base6.	
*	magig "0b110907" = TestNet	, magig "f9beb4d9" = Main-Net
*	Löst IllegalArgumentException aus wenn der Private-Key nicht erkantn wurde.	*/
public PrvKey(String privKey, String magig) throws IllegalArgumentException
{
	this.magig = magig;
	if(magig.equals("0b110907"))PrivKeyVersionsNr	= "ef";						// Auswahl TestNet3
	else						PrivKeyVersionsNr	= "80";			// Auswahl Main-Net
	
	String prvKey = txtToHexPrivKey(privKey);
	is_PrivKey_Valid(prvKey);
	
	if(prvKey.length()==64 && prvKey.matches("[0-9a-fA-F]+")) this.privKey = Convert.hexStringToByteArray(prvKey);
	else throw new IllegalArgumentException("Error in \"PrvKey\": False format!");	
}



/**	Dem Konstruktor wird ein beliebiger Text-String übergeben, 
*	der Priv.Key entsteht dann durch den Hash von 1xSHA256
*	Das zweite Argument wird nicht benutzt, und kann true oder false sein.	
* 	@throws UnsupportedEncodingException */
public PrvKey(String privKey, String magig, boolean TRUE) throws UnsupportedEncodingException
{
	this.magig = magig;
	if(magig.equals("0b110907"))PrivKeyVersionsNr	= "ef";						// Auswahl TestNet3
	else						PrivKeyVersionsNr	= "80";			// Auswahl Main-Net
	this.privKey = 	Calc.getHashSHA256((privKey).getBytes("UTF-8"));
}









// ------------------------------ Konstruktor Hilfsmethoden --------------------------------------------- 



// Hier wird geprüft ob der Private-Key(Hex) inerhalb des erlaubten Bereiches liegt
private boolean is_PrivKey_Valid(String str) throws IllegalArgumentException
{
	if(str.equals("")) 			throw new IllegalArgumentException("Error in \"PrvKey\": Private Key is NULL!");
	BigInteger min = new BigInteger("0",16);					
	BigInteger max = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364140",16);	
	BigInteger key = new BigInteger(str,16);
	if(key.compareTo(min) <= 0) 		throw new IllegalArgumentException("Error in \"PrvKey\": Private Key is <= 0!");
	if(key.compareTo(max) >  0) 		throw new IllegalArgumentException("Error in \"PrvKey\": Private Key is > Max!");
	return true;
}



// Erkennt den String als private-Key in den 5 möglichen Formaten, und gibt ihn als Hexa-PrivateKey zurück
// Hexa, Base58, Base58 Compressed, Base64, Base6
// Je nach Auswahl Main-Net / Test-Net, unterschiedlich
private String txtToHexPrivKey(String str) throws IllegalArgumentException
{
	str = str.trim();
	int s;
	if(PrivKeyVersionsNr.equals("ef")) 		s = getFormat_TestNet(str);
	else 						s = getFormat_MainNet(str);
	switch(s)
	{
		case-1: throw new IllegalArgumentException("Error in \"PrvKey\": false format");//-1 = Format Fehler
		case 0: throw new IllegalArgumentException("Error in \"PrvKey\": Null-String");	// 0 = Null String
		case 1: return str;  								// 1 = Hexa       
		case 2: return base58_PrivateKey_to_HexPrivateKey(str);				// 2 = Base58
		case 3: return base58compressed_PrivateKey_to_HexPrivateKey(str);		// 3 = Base58 compressed
		case 4: return base64_PrivateKey_to_HexPrivateKey(str);  			// 4 = Base64   
		case 5: return base6_PrivateKey_to_HexPrivateKey(str);  			// 5 = Base6 
		default:	break;
	}
	return null;
}



// gibt das Format des Strings zurück: (Nur für Main-Net)
//-1 = Fehler kein richtiges Format erkannt
// 0 = Null String
// 1 = Hexa
// 2 = Base58
// 3 = Base58 compressed
// 4 = Base64
// 5 = Base6
private int getFormat_MainNet(String str)
{
 if(str.equals(""))   																return 0;
 if(str.length()==64 && str.matches("[0-9a-fA-F]+")) 												return 1;
 if(str.length()==51 && str.matches("[123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz]+") && str.charAt(0)=='5') 			return 2;
 if(str.length()==52 && str.matches("[123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz]+") && (str.charAt(0)=='L' || str.charAt(0)=='K'))return 3;
 if(str.length()==44 && str.matches("[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=]+") &&  str.charAt(43)=='=') 		return 4;
 if(str.length()==100 && str.matches("[123456-]+")) 												return 5;
 return -1;
}




// gibt das Format des Strings zurück (Nur für Test-Net)
//-1 = Fehler kein richtiges Format erkannt
// 0 = Null String
// 1 = Hexa
// 2 = Base58
// 3 = Base58 compressed
// 4 = Base64
// 5 = Base6
private int getFormat_TestNet(String str)
{
 if(str.equals(""))   														return 0;// prüfen ob leer String
 if(str.length()==64 && str.matches("[0-9a-fA-F]+")) 										return 1;// prüfen auf Hexa
 if(str.length()==51 && str.matches("[123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz]+") && str.charAt(0)=='9') 	return 2;	
 if(str.length()==52 && str.matches("[123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz]+") && (str.charAt(0)=='c' || str.charAt(0)=='C'))return 3;
 if(str.length()==44 && str.matches("[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=]+") &&  str.charAt(43)=='=') return 4;
 if(str.length()==100 && str.matches("[123456-]+")) 										return 5;// prüfen auf Base6
 return -1;
}



// Diese spizielle Methode konvertiert den Base58 Private Key in den rohen Hexa Private Key, 
// dabei wird das erste Byte und die letzten 8 Byte (Hash) entfernt
private String base58_PrivateKey_to_HexPrivateKey(String str) throws IllegalArgumentException
{
	if(is_PrvKey_Base58_Valid(str) == false) throw new IllegalArgumentException("Error in \"PrvKey\": Base58 is not valide!");
	String erg = Convert.Base58ToHexString(str,74);					// wird nach Hex konvertiert  (74 Zeichen lang)
	return erg.substring(2,66);							// Das erste Byte und die letzten 8 Zeichen werden entfernt
}	  


// Diese spizielle Methode konvertiert den Base58-compressed Private Key in den rohen Hexa Private Key, 
// dabei wird das erste Byte und die letzten 8 Byte (Hash) entfernt
private String base58compressed_PrivateKey_to_HexPrivateKey(String str) throws IllegalArgumentException
{
	if(is_PrvKey_Base58compressed_Valid(str) == false) throw new IllegalArgumentException("Error in \"PrvKey\": Base58 compressed is not valide!");	
	String erg = Convert.Base58ToHexString(str,76);				// wird nach Hex konvertiert  (76 Zeichen Byte lang)
	return erg.substring(2,66);						// Das erste Byte und die letzten 10 Zeichen werden entfernt
}



// konvertiert den Base64 Private Key in den rohen Hexa Private Key
private String base64_PrivateKey_to_HexPrivateKey(String str) throws IllegalArgumentException
{
	if(is_PrvKey_Base64_Valid(str) == false)  throw new IllegalArgumentException("Error in \"PrvKey\": Base64 compressed is not valide!");
	byte[] erg = Base64.getDecoder().decode(str);
	return Convert.byteArrayToHexString(erg);
}



// Hier werden die Würfelzeichen in HEX konviertiert
// 1=1, 2=2, 3=3 4=4, 5=5, 6=0
// Mamimaler Würfelwerd (Mod) = 1621416542-2615626236-3462631235-4363525141-6636261141-4266313436-1546433233-1342224233-3313325535-4344331641
private String base6_PrivateKey_to_HexPrivateKey(String str)                  
{
	BigInteger mod = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141",16);	// Modulo des Privatkey / überlauf
	str = str.replaceAll("-","");				// Platzhalter Zeichen "-" wird aus dem String entfernt
	str = str.replaceAll("6","0");				// Die Ziffer 6 wird mit 0 ersetzt, nach String     zur Basis 16
	BigInteger dec = new BigInteger(str,6);			// nach BigInteger zur Bases  6
	dec = dec.mod(mod);					// Private Key Modulo FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141 
	String erg = dec.toString(16);  			// nach String     zur Bases 16 
	while(erg.length() < 64) erg="0"+erg;			// String wird vorne mit nullen aufgef�llt
	return erg;
}



// prüft ob der in Base58 eingegebene Private Key den richtigen Hash (SHA256) am Ende hat
private boolean is_PrvKey_Base58_Valid(String str)
{
	if(str.equals("") || str.length() != 51) 		return false;           // Format prüfung
	String roh = Convert.Base58ToHexString(str,74);					// wird nach Hex konvertiert  (74 Zeichen lang)
	String a = roh.substring(0,66);							// die ersten 66 Zeichen
	String b = roh.substring(66,74).toLowerCase();// die letzten 8 Zeichen die als Hash(SHA256) angeh�ngt sind werden in gro�buchstaben konvertiert
	String h = null;								// der neue Hash, zum vergleich
	try
	{
		h = Calc.getHashSHA256_from_HexString(a);				// 2x SHA256 vom ersten Teil
		h = Calc.getHashSHA256_from_HexString(h);
	} 
	catch (Exception e)	{e.printStackTrace();}					
	h=h.substring(0,8); 								// die ersten 8 Zeichen des neuen Hash
	if(b.equals(h)) return true;							// die Hashs werden verglichen
	return false;
}



// prüft ob der in Base58-compressed eingegebene Private Key den richtigen Hash (SHA256) am Ende hat
private boolean is_PrvKey_Base58compressed_Valid(String str)
{
	if(str.equals("") || str.length() != 52) 		return false;           	// Format prüfung
	String roh = Convert.Base58ToHexString(str,76);						// wird nach Hex konvertiert  (76 Zeichen lang)
	String a = roh.substring(0,68);								// die ersten 68 Zeichen
	String b = roh.substring(68,76).toLowerCase();	// die letzten 8 Zeichen die als Hash(SHA256) angeh�ngt sind werden in gro�buchstaben konvertiert
	String h = null;									// der neue Hash, zum vergleich
	try
	{
		h = Calc.getHashSHA256_from_HexString(a);					// 2x SHA256 vom ersten Teil
		h = Calc.getHashSHA256_from_HexString(h);
	} 
	catch (Exception e)	{e.printStackTrace();}										
	h=h.substring(0,8); 									// die ersten 8 Zeichen des neuen Hash
	if(b.equals(h)) return true;								// die Hashs werden verglichen
	return false;
}


// prüft ob der in Base64 eingegebene Private-Key das richtige Format hat
private boolean is_PrvKey_Base64_Valid(String str)
{
	if(str.length()==44 && str.matches("[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=]+") &&  str.charAt(43)=='=') return true;
	return false;										
}







// ------------------------------------------- Private-Key Methoden ------------------------------------------


/**	Gibt den Priv.Key als Hex-String zurück 64Zeichen.	*/
public String getPrivKey_HexString()
{
	return Convert.byteArrayToHexString(privKey);
}


/**	Gibt den Priv.Key als Hex-String zurück 64Zeichen.	*/
public String toString()
{
	return Convert.byteArrayToHexString(privKey);
}



/** Gibt den Priv.Key als Base58 String zurück.  */
public String getBase58PrivKey()
{
	String str = Convert.byteArrayToHexString(privKey);
	String h = Calc.getHashSHA256_from_HexString(Calc.getHashSHA256_from_HexString(PrivKeyVersionsNr+str));
	h = h.substring(0,8);
   	return Convert.hexStringToBase58(PrivKeyVersionsNr + str + h);
}



/** Gibt den Priv.Key als Base58 Compresset String zurück.  */
public String getBase58CompressPrivKey()
{
	String str = Convert.byteArrayToHexString(privKey);
	String com = "01";                         						// Compressed Bit  01
 	String h = Calc.getHashSHA256_from_HexString(Calc.getHashSHA256_from_HexString(PrivKeyVersionsNr+str+com));
 	h = h.substring(0,8);								
	return Convert.hexStringToBase58(PrivKeyVersionsNr + str + com + h);        			
}



/** Gibt den Priv.Key als Base64 String zurück.  */
public String getBase64PrivKey()
{
	return Base64.getEncoder().encodeToString(privKey);
}







//-------------------------------------------------- Public-Key Methoden --------------------------------------


/**	Gibt den gesamten Public Key als ByteArray zurück. 04 + X + Y  Koordinaten		*/
public byte[] getPubKey() 
{
	ECNamedCurveParameterSpec spec = ECNamedCurveTable.getParameterSpec("secp256k1"); 
	ECPoint pointQ = spec.getG().multiply(new BigInteger(1, privKey)); 
	return pointQ.getEncoded(false); 
} 







// ------------------------------------------------  Bitcoin-Adress Methoden --------------------------------

/**	Gibt den Hash160 der Bitcoin-Adresse zurück. */
public Hash160 getHash160()
{
	byte[] pub = getPubKey();
	byte[] h = Calc.getHashSHA256(pub);
	return new Hash160(Calc.getHashRIPEMD160(h));
}	


public BitcoinAddr getBitcoinAddr()
{
	return new BitcoinAddr(getHash160(), magig);
}


}
