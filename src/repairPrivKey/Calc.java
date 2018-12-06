package repairPrivKey;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.math.ec.ECPoint;



	/************************************************************************
	 *									*
	 *	Hier werden alle möglichen Berechnungen durchgeführt.		*
	 *									*
	 ************************************************************************/



public class Calc
{
	

	

	
	
// ------------------------------------------------- Secp256k1 ------------------------------------------------------//	
	
	
// Berechnet den PublicKey X-Koordinate, Eingabe Hex-String, Rückgabe Hex-String
public static String getPublicKeyX(String str)
{
  	return getPublicKey(str).substring(0,66); 
}
	  
	  
//Berechnet den PublicKey X und Y -Koordinate, Eingabe Hex-String, Rückgabe Hex-String	  
public static String getPublicKey(String str) 
{ 
   	byte[] b = getPublicKey(Convert.hexStringToByteArray(str));
   	return Convert.byteArrayToHexString(b);
}	
	
		
// berechnet den Public Key aus dem Private Key in Byte Array
public static byte[] getPublicKey(byte[] privateKey) 
{
 	ECNamedCurveParameterSpec spec = ECNamedCurveTable.getParameterSpec("secp256k1"); 
 	ECPoint pointQ = spec.getG().multiply(new BigInteger(1, privateKey)); 
 	return pointQ.getEncoded(false); 
} 
	
		
	
	
// ------------------------------------------------- Hash SHA256 ------------------------------------------------------//

	
/** Berechnet den Hash(SHA256) aus einem Hex-String und gibt ihn als Hex-String zurück.   */	
public static String getHashSHA256_from_HexString(String str)
{
	  byte[] b = getHashSHA256(Convert.hexStringToByteArray(str));
	  return Convert.byteArrayToHexString(b);
}
	

/** Berechnet den Hash(SHA256) aus einem normalen Text und gibt ihn als Hex-String zurück.  */	
public static String getHashSHA256(String str) 
{
	try 
	{
		byte[] b = getHashSHA256((str).getBytes("UTF-8"));
		return Convert.byteArrayToHexString(b);
	} 
		catch (UnsupportedEncodingException e) 
		{
			System.out.println("Fehler in getHashSHA256()");
			System.out.println(e.getMessage());
			return "-1";
		}
	 
}
		

public static byte[] getHashSHA256(byte[] b) 
{
	MessageDigest sha;
	try 
	{
		sha = MessageDigest.getInstance("SHA-256");
		return sha.digest(b);
	} 
	catch (NoSuchAlgorithmException e) 
	{
		System.out.println("Fehler in getHashSHA256()");
		System.out.println(e.getMessage());
		return null;
	}
}
		
	



		
// ------------------------------------------------- Hash RIPEMD-160 ----------------------------------------------------//
			
public static String getHashRIPEMD160_from_HexString(String str)
{
	  byte[] b = getHashRIPEMD160(Convert.hexStringToByteArray(str));
	  return Convert.byteArrayToHexString(b);
}
		
		
public static byte[] getHashRIPEMD160(byte[] b)
{
	RIPEMD160Digest ripemd = new RIPEMD160Digest();
	ripemd.update (b, 0, b.length);
	byte[] hash160 = new byte[ripemd.getDigestSize()];
   	ripemd.doFinal (hash160, 0);
	return hash160;	
}	
	
	



// ------------------------------------------------- SHA1 ----------------------------------------------------------------//

//Gibt den SHA1 Hash in Base64 zurück
public static String getHashSHA1(String in) 
{ 
	try 
	{
		MessageDigest crypt = MessageDigest.getInstance("SHA-1");
		crypt.update(in.getBytes("UTF-8"));
		return DatatypeConverter.printBase64Binary(crypt.digest());
	} 
	catch (NoSuchAlgorithmException | UnsupportedEncodingException e) 
	{
		System.out.println("Fehler in getHashSHA1()");
		System.out.println(e.getMessage());
		return null;
	}
}
}

