package GUI;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import repairPrivKey.CoinParameter;
import repairPrivKey.Run;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JCheckBox;



/****************************************************************************************
*											*
*	repairPrivKey repariert einen beschädigten Priv-Key wenn wenige Zeichen fehlen.	*
*											*
*****************************************************************************************/



public class GUI extends JFrame 
{

public static final String	progName	= "repairPrivKey";
public static final String	version 	= "V1.3.0";
public static final String	autor 		= "Mr. Maxwell";
public static final String	eMail		= "Maxwell-KSP@gmx.de";
public static final String	myBitcoinAddr 	= "12zeCvN7zbAi3JDQhC8tU3DBm35kDEUNiB";
public static 	JFrame		hauptFenster;			// Hauptframe der GUI
public static 	JPanel 		pnl_Main;			// Das Hauptpanel der Gui
public static	JLabel 		lbl_beschreibung;		// Die Beschreibung der Software
public static 	JTextField 	txt_PrivateKey;			// Eingabefeld Priv.Key
public static	JLabel 		lbl_PrivKey;			// Label Priv.Key
public static 	JTextField 	txt_BitcoinAdr;			// Eingabe Bitcoin Adresse
public static	JLabel 		lbl_BitcoinAdr;
public static 	JTextPane 	txt_Ausgabe;			// Das Ausgabe Feld aller Meldungen und Ausgaben
public static	JProgressBar 	progressBar;			// Die animierte Warte-Anzeige
public static	JButton 	btn_start;			// Der Start/Stop Button
public static 	JTextPane 	lbl_Info;			// Das Infofenster mit den Laufzeitinformationen
public static 	JTextField 	lbl_Demo;
public static 	JCheckBox 	testnet = new JCheckBox("Testnet");
public static 	CoinParameter	coinParameter;


public static void main(String[] args) 
{
	try 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					GUI frame = new GUI();
					frame.setVisible(true);
					{	
					}
				} 
				catch (Exception e) {e.printStackTrace();}
			}
		});
			
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() 	// Nach dem Ende des Programms
		{
			public void run() 
			{	// Hier alles einfügen was nach dem Beenden des Programms noch getan werden muss!
				Run.stop = true;
				System.out.println("Programm wurde beendet.");
			}
		}));			
	}
	catch (Exception e1) {System.err.println("Programm ist bereits geöffnet!");}	
}


	
	
	
	
public GUI() throws Exception 
{
	pnl_Main = new JPanel();
	pnl_Main.setLayout(null);
	hauptFenster = this;
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setTitle(progName+"               "+version);
	setBounds(100, 100, 650, 420);
	//setMaximizedBounds(new java.awt.Rectangle(0, 0, 1300, 800));
	setMaximumSize(new java.awt.Dimension(650, 1200));
	setMinimumSize(new java.awt.Dimension(650, 400));
	setPreferredSize(new java.awt.Dimension(650, 400));
	setContentPane(pnl_Main);
			
	lbl_Demo = new JTextField();
	lbl_Demo.setBorder(null);
	lbl_Demo.setEditable(false);
	lbl_Demo.setForeground(SystemColor.activeCaption);
	lbl_Demo.setHorizontalAlignment(SwingConstants.RIGHT);
	lbl_Demo.setFont(new Font("Tahoma", Font.PLAIN, 12));
	lbl_Demo.setText("please donate:  12zeCvN7zbAi3JDQhC8tU3DBm35kDEUNiB");
	lbl_Demo.setBounds(227, 352, 383, 29);
	pnl_Main.add(lbl_Demo);
	
// Label Programm beschreibung
	lbl_beschreibung = new JLabel("Hier kann ein Bitcoin Priv. Key mit einigen wenigen fehlenden Zeichen wiederhergestellt werden.");
	lbl_beschreibung.setHorizontalAlignment(SwingConstants.LEFT);
	lbl_beschreibung.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lbl_beschreibung.setForeground(new Color(0, 153, 255));
	lbl_beschreibung.setBounds(10, 11, 624, 37);
	pnl_Main.add(lbl_beschreibung);
		
// Eingabefeld Priv.Key
	txt_PrivateKey = new JTextField();
	txt_PrivateKey.setFont(new Font("Courier New", Font.PLAIN, 20));
	txt_PrivateKey.setBounds(10, 107, 618, 29);
	getContentPane().add(txt_PrivateKey);
	lbl_PrivKey = new JLabel("Eingabe Priv.Key (base58)     fehlende Zeichen durch _ ersetzen ");
	lbl_PrivKey.setFont(new Font("Tahoma", Font.PLAIN, 12));
	lbl_PrivKey.setBounds(10, 86, 453, 20);
	pnl_Main.add(lbl_PrivKey);
		
// Eingabefeld Bitcoin Adresse
	txt_BitcoinAdr = new JTextField();
	txt_BitcoinAdr.setFont(new Font("Courier New", Font.PLAIN, 16));
	txt_BitcoinAdr.setBounds(10, 192, 415, 23);
	pnl_Main.add(txt_BitcoinAdr);
	txt_BitcoinAdr.setColumns(10);
	lbl_BitcoinAdr = new JLabel("Eingabe der zugehörigen Bitcoin Adresse");
	lbl_BitcoinAdr.setFont(new Font("Tahoma", Font.PLAIN, 12));
	lbl_BitcoinAdr.setBounds(10, 171, 369, 20);
	pnl_Main.add(lbl_BitcoinAdr);
		
// Start Button
	btn_start = new JButton("Suche starten");
	btn_start.addActionListener(new ActionListener() 
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			Run.go();				// startet den Suchvorgang
		}
	});
	btn_start.setFont(new Font("Tahoma", Font.PLAIN, 18));
	btn_start.setForeground(new Color(47, 79, 79));
	btn_start.setBounds(483, 183, 145, 37);
	pnl_Main.add(btn_start);
		
// Ausgabefeld
	txt_Ausgabe = new JTextPane();
	txt_Ausgabe.setBackground(SystemColor.menu);
	txt_Ausgabe.setFont(new Font("Courier New", Font.PLAIN, 15));
	txt_Ausgabe.setEditable(false);
	txt_Ausgabe.setBounds(10, 293, 624, 61);
	pnl_Main.add(txt_Ausgabe);
		
// Die animierte Warte-Anzeige
	progressBar = new JProgressBar();
	progressBar.setEnabled(false);
	progressBar.setIndeterminate(true);
	progressBar.setForeground(Color.GREEN);
	progressBar.setBounds(10, 226, 618, 8);
	progressBar.setBorder(new EmptyBorder(0, 0, 0, 0));
	progressBar.setVisible(false);
	pnl_Main.add(progressBar);
		
// Das Infofenster mit den Laufzeitinformationen
	lbl_Info = new JTextPane();
	lbl_Info.setBackground(SystemColor.control);
	lbl_Info.setEditable(false);
	lbl_Info.setFont(new Font("Tahoma", Font.PLAIN, 11));
	lbl_Info.setBounds(10, 245, 624, 47);
	pnl_Main.add(lbl_Info);	
	testnet.setBounds(10, 44, 97, 23);
	pnl_Main.add(testnet);	
	coinParameter = CoinParameter.getFromSymbol("BTC");
	
	testnet.addActionListener(new ActionListener() 
	{
		public void actionPerformed(ActionEvent e) 
		{
			if	(testnet.isSelected()) 	coinParameter = CoinParameter.getFromSymbol("BTC-T");
			else 	 			coinParameter = CoinParameter.getFromSymbol("BTC");
		}
	});
}
}
