package main;

import java.io.IOException;

import donnees.CD;
import XML.ChargeurCD;

/**
 * un main permettant de charger un CD
 */
public class MainChargeurCD {

	/**
	 * methode principale qui charge un cd
	 * 
	 * @param args
	 *            inutilise
	 * @throws IOException
	 *             si fichier inexistant
	 */
	public static void main(String args[]) throws IOException {
		String nomFichier = "musicbrainz/0117b967-d066-4ce1-bf7f-f2b34c063ed3.xml";
		ChargeurCD charge = new ChargeurCD(nomFichier);
		CD c = charge.chargerCD();
		System.out.println(c);
	}

}
