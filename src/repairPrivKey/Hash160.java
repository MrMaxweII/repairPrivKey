package repairPrivKey;


/************************************************************************************************************
 *																											*
 *		Nicht statische Klasse die den Hash160 ale Datentyp erstellt.										*
 *		Mit dem Konstruktor muss zuerst ein Hash160-Object erzeugt werden.									*
 *		Konstruktor: 	ByteArray (20Bytes)																	*
 *																											*
 ************************************************************************************************************/






public class Hash160 
{

	
	private byte[] h160;			
	
	 
	 
	 /**	Dem Konstruktor wird der Hash160 als Byte-Array der länge 20Byte übergeben.	
	  *		Löst Exception bei falscher Länge aus! */ 
	public Hash160(byte[] h160) throws IllegalArgumentException
	{
		if(h160.length != 20)									
		throw new IllegalArgumentException("Error in \"Hash160\": length ist not 20Byte!");
		else this.h160 = h160;
	}
	
	
	
	/**	Gibt den Hash160 als Byte-Array zurück. */
	public byte[] get()
	{
		return h160;
	}
	
	
	
	/**	Gibt den Hash160 als Hex-String zurück. */
	public String toString()
	{
		return Convert.byteArrayToHexString(h160);		
	}	
}
