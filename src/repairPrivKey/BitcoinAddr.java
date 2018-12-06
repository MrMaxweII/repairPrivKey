package repairPrivKey;
import java.util.Arrays;



	/************************************************************************************************************
	 *																											*
	 *		Nicht statische Klasse die eine Bitcoin-Adresse erstellt.											*
	 *		Mit dem Konstruktor muss zuerst ein Bitcoin-Adress-Object erzeugt werden.							*
	 *		Es sind 3 Konstruktoren implementiert.																*
	 *		-	Hash160 Formalt																					*
	 *		-	String: "hexa" oder "base58" (Muss angegeben werden)											*
	 *		-	String: "h160" als String  oder "base58"  (Wird selbst erkannt)									*
	 *	 																										*
	 *		magig "0b110907" = TestNet																			*
	 *		magig "f9beb4d9" = Main-Net																			*
	 *																											*
	 ************************************************************************************************************/




public class BitcoinAddr 
{
			
													/** Die Bitcoin Adress VersionsNr. "00" = MainNet und "6f" = TestNet, kann hier neu gesetzt werden. */
	private String addresVersionsNr; 				
													/** Die Bitcoin Adresse als 25Byte Array */
	private byte[] bitcoinAddress;											
	
	
	
	
	
	

// ---------------------------------------------------- Konstruktoren -------------------------------------------------------------	
	
/**	Dem Konstruktor wird die BitcoinAdresse im Hash160 Format übergeben.	
*	Der Magig-String beschreibt welches Bitcoin-Netzwerk verwendet wird. ("0b110907" ist TestNet3)  */
public BitcoinAddr(Hash160 h160, String magig) 
{	
	if(magig.equals("0b110907")) 	addresVersionsNr	= "6f";										// Auswahl TestNet3
	else							addresVersionsNr	= "00";										// Auswahl MeinNet	
	
	String adr = addresVersionsNr + h160.toString(); 
	String h   = Calc.getHashSHA256_from_HexString(Calc.getHashSHA256_from_HexString(adr));	// 2 x SHA256
	adr = adr +  h.substring(0,8);																													
	bitcoinAddress =  Convert.hexStringToByteArray(adr);	
}





/**	Dem Konstruktor wird die BitcoinAdresse als String (verschiedene String-Typen) übergeben.	
*	Der "String-Typ im 2. Argument beschreibt in welchem Format die Bitcoin-Adresse interpretiert werden soll.
*	stringTyp: "hex":  		Die Bitcoin-Adresse wird als 50-Zeichen Hex-String interpretiert, mit der angehängten Checksumme. 
*	stringTyp: "base58":	Die Bitcoin-Adresse wird als Base58 String interpretiert.	
*	Der Magig-String beschreibt welches Bitcoin-Netzwerk verwendet wird.
*	Löst IllegalArgumentException aus, falls die BitcoinAdresse nicht richtig ist.  */
public BitcoinAddr(String address, String stringTyp, String magig) throws IllegalArgumentException
{
	if(magig.equals("0b110907")) 	addresVersionsNr	= "6f";										// Auswahl TestNet3
	else							addresVersionsNr	= "00";										// Auswahl MeinNet	
	
	if(stringTyp.equals("hex"))
	{
		if(address.length()==50 && address.matches("[0-9a-fA-F]+")) 
		{
			if(address.substring(0,2).equals(addresVersionsNr))
			{
				String addr = address.substring(0,42);
				String h   = Calc.getHashSHA256_from_HexString(Calc.getHashSHA256_from_HexString(addr));	// 2 x SHA256				
				h=h.substring(0, 8);
				if(h.equals(address.substring(42, 50))) bitcoinAddress =  Convert.hexStringToByteArray(address);
				else throw new IllegalArgumentException("Error in \"BitcoinAddr\" : False Address-Hash!"); 
				return;
			}
			else throw new IllegalArgumentException("Error in \"BitcoinAddr\" : False Network-Version!");
		}
		else throw new IllegalArgumentException("Error in \"BitcoinAddr\" : Hex-String length is not 50 char! ");
	}
	
	if(stringTyp.equals("base58"))
	{
		address = Convert.Base58ToHexString(address, 50);		
		if(address.substring(0,2).equals(addresVersionsNr))
		{
			String addr = address.substring(0,42);
			String h   = Calc.getHashSHA256_from_HexString(Calc.getHashSHA256_from_HexString(addr));	// 2 x SHA256				
			h=h.substring(0, 8);
			if(h.equals(address.substring(42, 50))) bitcoinAddress =  Convert.hexStringToByteArray(address);
			else throw new IllegalArgumentException("Error in \"BitcoinAddr\" : False Address-Hash!"); 
			return;
		}
		else throw new IllegalArgumentException("Error in \"BitcoinAddr\" : False Network-Version!");
	}
		
	throw new IllegalArgumentException("Error in \"BitcoinAddr\" : String type is not supported!");
}






/**	Dem Konstruktor wird die BitcoinAdresse als String übergeben.	
*	Dabei kann es sich wahlweise um einen "base58" String handeln oder um einen "hash160" String.	
*	Der "magig" String beschreibt welches Bitcoin-Netzwerk verwendet wird.
*	Löst IllegalArgumentException aus, falls die BitcoinAdresse oder der Hash160 nicht richtig ist.  */
public BitcoinAddr(String addr, String magig) throws IllegalArgumentException
{
	if(magig.equals("0b110907")) 	addresVersionsNr	= "6f";											// Auswahl TestNet3
	else							addresVersionsNr	= "00";											// Auswahl MeinNet	
		
	if(addr.length()==40 && addr.matches("[0-9a-fA-F]+"))
	{		
		String adr = addresVersionsNr + addr; 
		String h   = Calc.getHashSHA256_from_HexString(Calc.getHashSHA256_from_HexString(adr));			// 2 x SHA256
		adr = adr +  h.substring(0,8);																													
		bitcoinAddress =  Convert.hexStringToByteArray(adr);
		return;
	}
	
	if(addr.matches("[123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz]+"))
	{
		String address = Convert.Base58ToHexString(addr, 50);	
		if(address.substring(0,2).equals(addresVersionsNr))
		{
			String m = address.substring(0,42);
			String h   = Calc.getHashSHA256_from_HexString(Calc.getHashSHA256_from_HexString(m));	// 2 x SHA256				
			h=h.substring(0, 8);
			if(h.equals(address.substring(42, 50))) bitcoinAddress =  Convert.hexStringToByteArray(address);
			else throw new IllegalArgumentException("Error in \"BitcoinAddr\" : False Address-Hash!"); 
			return;
		}
		else throw new IllegalArgumentException("Error in \"BitcoinAddr\" : False Network-Version!");
	}
	throw new IllegalArgumentException("Error in \"BitcoinAddr\" : False address format!");
}










// ----------------------------------------------------------  Bitcoin-Adress Methoden --------------------------------

/**	Gibt den Hash160 der Bitcoin-Adresse  als 20Byte-Array zurück. */
public byte[] getHash160_byte()
{
	return Arrays.copyOfRange(bitcoinAddress, 1, 21);
}	



/**	Gibt den Hash160 der Bitcoin-Adresse  als Hex-String. */
public String getHash160_String()
{
	byte[] h = Arrays.copyOfRange(bitcoinAddress, 1, 21);
	return Convert.byteArrayToHexString(h);
}



/**	Gibt den Hash160 der Bitcoin-Adresse  als Hash160 zurück. */
public Hash160 getHash160()
{
	byte[] h = Arrays.copyOfRange(bitcoinAddress, 1, 21);
	return new Hash160(h);
}



/**	Gibt die Bitcoin-Adresse als 25Byte-Array zurück. */
public byte[] getAddress_byte()
{
	return bitcoinAddress;
}	



/**	Gibt die Bitcoin-Adresse als Base58 String zurück. */
public String getBase58Address()
{																												
	return Convert.hexStringToBase58(toString());	
}



/** Gibt die Bitcoin-Adresse als Hex-String zurück. 
*	 Einschlißlich Versions-Byte und Checksumme */
public String toString()
{
	return Convert.byteArrayToHexString(bitcoinAddress);
}
}
