package GUI;

import java.awt.Font;

	/****************************************************************************************
	 *																							*
	 *		Automatische Größenanpassung aller Elemente in der GUI bei der Größenanpassung 		*
	 *		des Hauptfensters mit der Maus.														*
	 *																							*
	 *																							*
	 *****************************************************************************************/







public class AutoSize 
{


	
	
	
	// Die größe der Elemente wird durch den Rahmen ermittelt und angepasst.
	public static void size(double w, double h)
	{
			 if((w<692 && true)   || (h<300)) 		setSize_0();
		else if((w>=692 && w<735) || (h<300)) 		setSize_1();
		else if((w>=735 && w<785) || (h<375))		setSize_2();
		else if((w>=785 && w<835) || (h<410))		setSize_3();
		else if((w>=835 && w<890) || (h<440))		setSize_4();
		else if((w>=890 && w<945) || (h<480))		setSize_5();
		else if((w>=945 && w<997) || (h<485))		setSize_6();
		else if((w>=997 && w<1052)|| (h<510))		setSize_7();
		else if((w>=1052&& w<1107)|| (h<545))		setSize_8();
		else if((w>=1107&& w<1157)|| (h<580))		setSize_9();
		else if((w>=1157&& true)  || (false))		setSize_10();
	}
	
	
	
	
	

	// Alle Fenster und Schriftgrößen werden auf Standart eingestellt
	public static void setSize_0()
	{
		GUI.lbl_beschreibung.setBounds(10, 11, 624, 37);
		GUI.lbl_PrivKey.setBounds(10, 86, 453, 20);
		GUI.txt_PrivateKey.setBounds(10, 107, 618, 29);
		GUI.lbl_BitcoinAdr.setBounds(10, 171, 369, 20);
		GUI.txt_BitcoinAdr.setBounds(10, 192, 415, 23);			
		GUI.btn_start.setBounds(483, 183, 145, 37);
		GUI.progressBar.setBounds(10, 226, 618, 8);
		GUI.lbl_Info.setBounds(10, 245, 624, 47);
		GUI.txt_Ausgabe.setBounds(10, 293, 624, 67);
		GUI.lbl_Demo.setBounds(10, 245, 618, 115);

		GUI.lbl_beschreibung.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GUI.lbl_PrivKey.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GUI.txt_PrivateKey.setFont(new Font("Courier New", Font.PLAIN, 20));
		GUI.lbl_BitcoinAdr.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GUI.txt_BitcoinAdr.setFont(new Font("Courier New", Font.PLAIN, 16));
		GUI.btn_start.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GUI.lbl_Info.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GUI.txt_Ausgabe.setFont(new Font("Courier New", Font.PLAIN, 15));
		GUI.lbl_Demo.setFont(new Font("Tahoma", Font.PLAIN, 12));	
	}
	
	
	
	// Alle Fenster und Schriftgrößen werden auf Größe 1 eingestellt
	public static void setSize_1()
	{
		GUI.lbl_beschreibung.setBounds(10, 11, 690, 37);
		GUI.lbl_PrivKey.setBounds(10, 86, 502, 20);
		GUI.txt_PrivateKey.setBounds(10, 107, 670, 29);
		GUI.lbl_BitcoinAdr.setBounds(10, 171, 405, 20);
		GUI.txt_BitcoinAdr.setBounds(10, 192, 430, 23);			
		GUI.btn_start.setBounds(520, 183, 160, 37);
		GUI.progressBar.setBounds(10, 226, 672, 8);
		GUI.lbl_Info.setBounds(10, 245, 730, 47);
		GUI.txt_Ausgabe.setBounds(10, 293, 686, 67);	
		GUI.lbl_Demo.setBounds(10, 245, 674, 115);

		GUI.lbl_beschreibung.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GUI.lbl_PrivKey.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GUI.txt_PrivateKey.setFont(new Font("Courier New", Font.PLAIN, 21));
		GUI.lbl_BitcoinAdr.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GUI.txt_BitcoinAdr.setFont(new Font("Courier New", Font.PLAIN, 18));
		GUI.btn_start.setFont(new Font("Tahoma", Font.PLAIN, 19));
		GUI.lbl_Info.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GUI.txt_Ausgabe.setFont(new Font("Courier New", Font.PLAIN, 16));
		GUI.lbl_Demo.setFont(new Font("Tahoma", Font.PLAIN, 13));
	}
	
	
	
	// Alle Fenster und Schriftgrößen werden auf Größe 2 eingestellt
	public static void setSize_2()
	{
		GUI.lbl_beschreibung.setBounds(10, 11, 760, 37);
		GUI.lbl_PrivKey.setBounds(10, 86, 502, 20);
		GUI.txt_PrivateKey.setBounds(10, 107, 722, 29);
		GUI.lbl_BitcoinAdr.setBounds(10, 171, 405, 20);
		GUI.txt_BitcoinAdr.setBounds(10, 192, 465, 25);			
		GUI.btn_start.setBounds(570, 183, 160, 37);
		GUI.progressBar.setBounds(10, 226, 720, 8);
		GUI.lbl_Info.setBounds(10, 245, 750, 50);
		GUI.txt_Ausgabe.setBounds(10, 300, 750, 67);	
		GUI.lbl_Demo.setBounds(10, 245, 722, 115);

		GUI.lbl_beschreibung.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GUI.lbl_PrivKey.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GUI.txt_PrivateKey.setFont(new Font("Courier New", Font.PLAIN, 23));
		GUI.lbl_BitcoinAdr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GUI.txt_BitcoinAdr.setFont(new Font("Courier New", Font.PLAIN, 20));
		GUI.btn_start.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GUI.lbl_Info.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GUI.txt_Ausgabe.setFont(new Font("Courier New", Font.PLAIN, 18));
		GUI.lbl_Demo.setFont(new Font("Tahoma", Font.PLAIN, 14));
	}
	
	
	
	// Alle Fenster und Schriftgrößen werden auf Größe 3 eingestellt
	public static void setSize_3()
	{
		GUI.lbl_beschreibung.setBounds(10, 11, 760, 37);
		GUI.lbl_PrivKey.setBounds(10, 86, 502, 20);
		GUI.txt_PrivateKey.setBounds(10, 107, 773, 33);
		GUI.lbl_BitcoinAdr.setBounds(10, 171, 405, 20);
		GUI.txt_BitcoinAdr.setBounds(10, 192, 480, 25);			
		GUI.btn_start.setBounds(612, 183, 170, 37);
		GUI.progressBar.setBounds(10, 224, 773, 8);
		GUI.lbl_Info.setBounds(10, 245, 810, 54);
		GUI.txt_Ausgabe.setBounds(10, 310, 790, 67);	
		GUI.lbl_Demo.setBounds(10, 245, 772, 130);

		GUI.lbl_beschreibung.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GUI.lbl_PrivKey.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GUI.txt_PrivateKey.setFont(new Font("Courier New", Font.PLAIN, 25));
		GUI.lbl_BitcoinAdr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GUI.txt_BitcoinAdr.setFont(new Font("Courier New", Font.PLAIN, 21));
		GUI.btn_start.setFont(new Font("Tahoma", Font.PLAIN, 21));
		GUI.lbl_Info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GUI.txt_Ausgabe.setFont(new Font("Courier New", Font.PLAIN, 19));
		GUI.lbl_Demo.setFont(new Font("Tahoma", Font.PLAIN, 15));
	}
	

	
	
	// Alle Fenster und Schriftgrößen werden auf Größe 4 eingestellt
	public static void setSize_4()
	{
		GUI.lbl_beschreibung.setBounds(10, 11, 870, 37);
		GUI.lbl_PrivKey.setBounds(10, 86, 560, 20);
		GUI.txt_PrivateKey.setBounds(10, 110, 825, 35);
		GUI.lbl_BitcoinAdr.setBounds(10, 171, 405, 20);
		GUI.txt_BitcoinAdr.setBounds(10, 195, 520, 30);			
		GUI.btn_start.setBounds(649, 186, 185, 40);
		GUI.progressBar.setBounds(10, 240, 822, 8);
		GUI.lbl_Info.setBounds(10, 255, 830, 70);
		GUI.txt_Ausgabe.setBounds(10, 340, 830, 75);	
		GUI.lbl_Demo.setBounds(10, 260, 822, 145);

		GUI.lbl_beschreibung.setFont(new Font("Tahoma", Font.PLAIN, 19));
		GUI.lbl_PrivKey.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GUI.txt_PrivateKey.setFont(new Font("Courier New", Font.PLAIN, 27));
		GUI.lbl_BitcoinAdr.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GUI.txt_BitcoinAdr.setFont(new Font("Courier New", Font.PLAIN, 23));
		GUI.btn_start.setFont(new Font("Tahoma", Font.PLAIN, 23));
		GUI.lbl_Info.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GUI.txt_Ausgabe.setFont(new Font("Courier New", Font.PLAIN, 20));
		GUI.lbl_Demo.setFont(new Font("Tahoma", Font.PLAIN, 17));
	}
	
	
	
	
	// Alle Fenster und Schriftgrößen werden auf Größe 5 eingestellt
	public static void setSize_5()
	{
		GUI.lbl_beschreibung.setBounds(15, 11, 870, 37);
		GUI.lbl_PrivKey.setBounds(15, 86, 560, 25);
		GUI.txt_PrivateKey.setBounds(15, 115, 873, 37);
		GUI.lbl_BitcoinAdr.setBounds(15, 180, 405, 25);
		GUI.txt_BitcoinAdr.setBounds(15, 208, 550, 30);			
		GUI.btn_start.setBounds(702, 195, 185, 40);
		GUI.progressBar.setBounds(15, 255, 870, 10);
		GUI.lbl_Info.setBounds(15, 275, 910, 70);
		GUI.txt_Ausgabe.setBounds(15, 360, 890, 75);	
		GUI.lbl_Demo.setBounds(15, 270, 873, 159);

		GUI.lbl_beschreibung.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GUI.lbl_PrivKey.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GUI.txt_PrivateKey.setFont(new Font("Courier New", Font.PLAIN, 28));
		GUI.lbl_BitcoinAdr.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GUI.txt_BitcoinAdr.setFont(new Font("Courier New", Font.PLAIN, 24));
		GUI.btn_start.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GUI.lbl_Info.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GUI.txt_Ausgabe.setFont(new Font("Courier New", Font.PLAIN, 21));
		GUI.lbl_Demo.setFont(new Font("Tahoma", Font.PLAIN, 18));
	}
	
	
	
	
	// Alle Fenster und Schriftgrößen werden auf Größe 6 eingestellt
	public static void setSize_6()
	{
		GUI.lbl_beschreibung.setBounds(17, 20, 1000, 37);
		GUI.lbl_PrivKey.setBounds(17, 96, 560, 25);
		GUI.txt_PrivateKey.setBounds(17, 125, 927, 38);
		GUI.lbl_BitcoinAdr.setBounds(17, 200, 405, 25);
		GUI.txt_BitcoinAdr.setBounds(17, 228, 610, 32);			
		GUI.btn_start.setBounds(753, 220, 190, 40);
		GUI.progressBar.setBounds(17, 277, 925, 10);
		GUI.lbl_Info.setBounds(17, 310, 980, 100);
		GUI.txt_Ausgabe.setBounds(17, 400, 935, 85);	
		GUI.lbl_Demo.setBounds(17, 305, 926, 165);

		GUI.lbl_beschreibung.setFont(new Font("Tahoma", Font.PLAIN, 21));
		GUI.lbl_PrivKey.setFont(new Font("Tahoma", Font.PLAIN, 19));
		GUI.txt_PrivateKey.setFont(new Font("Courier New", Font.PLAIN, 30));
		GUI.lbl_BitcoinAdr.setFont(new Font("Tahoma", Font.PLAIN, 19));
		GUI.txt_BitcoinAdr.setFont(new Font("Courier New", Font.PLAIN, 26));
		GUI.btn_start.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GUI.lbl_Info.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GUI.txt_Ausgabe.setFont(new Font("Courier New", Font.PLAIN, 23));
		GUI.lbl_Demo.setFont(new Font("Tahoma", Font.PLAIN, 19));
	}
	
	
	
	// Alle Fenster und Schriftgrößen werden auf Größe 7 eingestellt
	public static void setSize_7()
	{
		GUI.lbl_beschreibung.setBounds(20, 20, 1000, 37);
		GUI.lbl_PrivKey.setBounds(20, 96, 700, 25);
		GUI.txt_PrivateKey.setBounds(20, 125, 977, 38);
		GUI.lbl_BitcoinAdr.setBounds(20, 200, 700, 25);
		GUI.txt_BitcoinAdr.setBounds(20, 228, 615, 32);			
		GUI.btn_start.setBounds(796, 220, 200, 40);
		GUI.progressBar.setBounds(20, 277, 976, 10);
		GUI.lbl_Info.setBounds(20, 310, 1010, 100);
		GUI.txt_Ausgabe.setBounds(20, 400, 980, 85);	
		GUI.lbl_Demo.setBounds(20, 310, 976, 165);

		GUI.lbl_beschreibung.setFont(new Font("Tahoma", Font.PLAIN, 22));
		GUI.lbl_PrivKey.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GUI.txt_PrivateKey.setFont(new Font("Courier New", Font.PLAIN, 31));
		GUI.lbl_BitcoinAdr.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GUI.txt_BitcoinAdr.setFont(new Font("Courier New", Font.PLAIN, 27));
		GUI.btn_start.setFont(new Font("Tahoma", Font.PLAIN, 26));
		GUI.lbl_Info.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GUI.txt_Ausgabe.setFont(new Font("Courier New", Font.PLAIN, 24));
		GUI.lbl_Demo.setFont(new Font("Tahoma", Font.PLAIN, 19));
	}
	
	
	
	// Alle Fenster und Schriftgrößen werden auf Größe 8 eingestellt
		public static void setSize_8()
		{
			GUI.lbl_beschreibung.setBounds(22, 25, 1000, 37);
			GUI.lbl_PrivKey.setBounds(22, 101, 700, 25);
			GUI.txt_PrivateKey.setBounds(22, 135, 1030, 42);
			GUI.lbl_BitcoinAdr.setBounds(22, 215, 700, 25);
			GUI.txt_BitcoinAdr.setBounds(22, 247, 640, 34);			
			GUI.btn_start.setBounds(841, 235, 210, 44);
			GUI.progressBar.setBounds(22, 300, 1029, 10);
			GUI.lbl_Info.setBounds(22, 330, 1040, 100);
			GUI.txt_Ausgabe.setBounds(22, 425, 1040, 85);	
			GUI.lbl_Demo.setBounds(22, 330, 1029, 175);

			GUI.lbl_beschreibung.setFont(new Font("Tahoma", Font.PLAIN, 23));
			GUI.lbl_PrivKey.setFont(new Font("Tahoma", Font.PLAIN, 21));
			GUI.txt_PrivateKey.setFont(new Font("Courier New", Font.PLAIN, 33));
			GUI.lbl_BitcoinAdr.setFont(new Font("Tahoma", Font.PLAIN, 21));
			GUI.txt_BitcoinAdr.setFont(new Font("Courier New", Font.PLAIN, 28));
			GUI.btn_start.setFont(new Font("Tahoma", Font.PLAIN, 27));
			GUI.lbl_Info.setFont(new Font("Tahoma", Font.PLAIN, 19));
			GUI.txt_Ausgabe.setFont(new Font("Courier New", Font.PLAIN, 25));
			GUI.lbl_Demo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
	
	
	
	
		// Alle Fenster und Schriftgrößen werden auf Größe 9 eingestellt
		public static void setSize_9()
		{
			GUI.lbl_beschreibung.setBounds(24, 26, 1200, 37);
			GUI.lbl_PrivKey.setBounds(24, 105, 700, 25);
			GUI.txt_PrivateKey.setBounds(24, 140, 1082, 45);
			GUI.lbl_BitcoinAdr.setBounds(24, 233, 700, 25);
			GUI.txt_BitcoinAdr.setBounds(24, 265, 690, 36);			
			GUI.btn_start.setBounds(880, 255, 225, 46);
			GUI.progressBar.setBounds(24, 330, 1080, 10);
			GUI.lbl_Info.setBounds(24, 360, 1120, 100);
			GUI.txt_Ausgabe.setBounds(24, 460, 1090, 85);	
			GUI.lbl_Demo.setBounds(24, 345, 1080, 185);

			GUI.lbl_beschreibung.setFont(new Font("Tahoma", Font.PLAIN, 25));
			GUI.lbl_PrivKey.setFont(new Font("Tahoma", Font.PLAIN, 22));
			GUI.txt_PrivateKey.setFont(new Font("Courier New", Font.PLAIN, 35));
			GUI.lbl_BitcoinAdr.setFont(new Font("Tahoma", Font.PLAIN, 22));
			GUI.txt_BitcoinAdr.setFont(new Font("Courier New", Font.PLAIN, 30));
			GUI.btn_start.setFont(new Font("Tahoma", Font.PLAIN, 28));
			GUI.lbl_Info.setFont(new Font("Tahoma", Font.PLAIN, 20));
			GUI.txt_Ausgabe.setFont(new Font("Courier New", Font.PLAIN, 26));
			GUI.lbl_Demo.setFont(new Font("Tahoma", Font.PLAIN, 21));
		}
	
	
	
	
		// Alle Fenster und Schriftgrößen werden auf Größe 9 eingestellt
		public static void setSize_10()
		{
			GUI.lbl_beschreibung.setBounds(26, 28, 1200, 37);
			GUI.lbl_PrivKey.setBounds(26, 110, 700, 30);
			GUI.txt_PrivateKey.setBounds(26, 145, 1130, 47);
			GUI.lbl_BitcoinAdr.setBounds(26, 245, 700, 30);
			GUI.txt_BitcoinAdr.setBounds(26, 280, 730, 38);			
			GUI.btn_start.setBounds(925, 267, 230, 50);
			GUI.progressBar.setBounds(26, 345, 1130, 10);
			GUI.lbl_Info.setBounds(26, 380, 1160, 100);
			GUI.txt_Ausgabe.setBounds(26, 485, 1130, 95);	
			GUI.lbl_Demo.setBounds(26, 375, 1129, 195);

			GUI.lbl_beschreibung.setFont(new Font("Tahoma", Font.PLAIN, 26));
			GUI.lbl_PrivKey.setFont(new Font("Tahoma", Font.PLAIN, 23));
			GUI.txt_PrivateKey.setFont(new Font("Courier New", Font.PLAIN, 36));
			GUI.lbl_BitcoinAdr.setFont(new Font("Tahoma", Font.PLAIN, 23));
			GUI.txt_BitcoinAdr.setFont(new Font("Courier New", Font.PLAIN, 31));
			GUI.btn_start.setFont(new Font("Tahoma", Font.PLAIN, 29));
			GUI.lbl_Info.setFont(new Font("Tahoma", Font.PLAIN, 21));
			GUI.txt_Ausgabe.setFont(new Font("Courier New", Font.PLAIN, 28));
			GUI.lbl_Demo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		}
	
	
	

}
