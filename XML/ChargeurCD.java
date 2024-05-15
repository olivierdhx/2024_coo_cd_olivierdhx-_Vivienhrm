package XML;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import donnees.CD;
import donnees.InfoPiste;

/**
 * 
 * classe pour charger un CD a partir d'un fichier XML musicbrainz
 * 
 */
public class ChargeurCD {

	/**
	 * nom du fichier contnant l'information
	 */
	private String nom;

	/**
	 * constructeur chargeurCD
	 * 
	 * @param nomFichier
	 *            le nom du fichier a charger
	 */
	public ChargeurCD(String nomFichier) {
		this.nom = nomFichier;
	}

	/**
	 * methode qui charge le fichier
	 * 
	 * @return le CD qui a ete charge
	 * 
	 * @throws IOException
	 *             quand probleme de lecture du fichier
	 */
	public CD chargerCD() throws IOException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		Document doc;
		
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			//en cas de probleme de configuration parser
			e.printStackTrace();
			return(null);
		}
		
		
		try {
			doc = db.parse(new File(this.nom));
		} catch (SAXException e) {
			//en cas de probleme de parsing
			e.printStackTrace();
			return(null);
		}

		NodeList titles = doc.getElementsByTagName("title");
		// le titre du CD
		String titreCd = titles.item(0).getTextContent();

		// le nom de l'auteur
		NodeList names = doc.getElementsByTagName("name");
		// le titre du CD
		String nomArtiste = names.item(0).getTextContent();

		CD cd = new CD(nomArtiste, titreCd);

		// le titre de chaque track
		NodeList tracks = doc.getElementsByTagName("track");
		for (int i = 0; i < tracks.getLength(); i++) {
			Node track = tracks.item(i);
			InfoPiste resultat = extrairePiste(track);
			cd.ajouterPiste(resultat);
		}

		return (cd);

	}

	/**
	 * methode privee qui permet d'extreire une piste du fichier XML
	 * 
	 * @param track
	 *            le noeud contenant le descriptif de la piste
	 * @return un objet contenant le descriptif de la piste
	 */
	private InfoPiste extrairePiste(Node track) {

		String nomPiste = "";
		int duree = 0;

		// cherchenode de nom
		NodeList liste = track.getChildNodes();
		for (int j = 0; j < liste.getLength(); j++) {
			Node itemJ = liste.item(j);

			// si c'est une duree
			if (itemJ.getNodeName().equals("length")) {
				duree = Integer
						.parseInt(itemJ.getFirstChild().getTextContent());
			}

			// si c'est le nom de piste
			if (itemJ.getNodeName().equals("recording")) {
				nomPiste = itemJ.getFirstChild().getTextContent();
			}
		}
		InfoPiste resultat = new InfoPiste(nomPiste, duree);
		return resultat;
	}

}
