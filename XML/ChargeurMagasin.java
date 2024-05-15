package XML;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import donnees.CD;
import donnees.Magasin;

/**
 * classe destinee a charger un magasin a patrir d'un nom de repertoire
 * 
 * @author vthomas
 * 
 */
public class ChargeurMagasin {

	/**
	 * la localisation du repertoire contenant les fichiers XML decrivant les
	 * CDs
	 */
	private String repertoireMagasin;

	/**
	 * constructeur qui sepcifie ou aller chercher les fichiers pour la
	 * construction du magasin
	 * 
	 * @param repertoire
	 */
	public ChargeurMagasin(String repertoire) {
		this.repertoireMagasin = repertoire;
	}

	/**
	 * methode qui permet de charger un magasin en lisant les fichiers XMl
	 * decrivant les CDs possede dans ce magasin.</p>
	 * 
	 * la localisation des fichiers est precisee a la construction du chargeur
	 * 
	 * @return le nouveau magasin construit contenant les CDs
	 * 
	 * @throws IOException
	 *             en cas d erreur de lecture
	 */
	public Magasin chargerMagasin() throws FileNotFoundException {
		Magasin magasin = new Magasin();
		File repertoire = new File(this.repertoireMagasin);
		// si repertoire inexistant, leve exception
		if (!repertoire.exists())
			throw new FileNotFoundException(this.repertoireMagasin
					+ " inexistant");
		// sinon parcours le contenu du repertoire
		File fichiers[] = repertoire.listFiles();
		for (File fichier : fichiers) {
			CD lecture = lireCd(fichier);
			if (lecture != null) {
				magasin.ajouteCd(lecture);
			}
		}
		return (magasin);
	}

	/**
	 * methode qui permet de demander la lecture et l'ajout d'un CD
	 * 
	 * @param fichier
	 *            fichier XML decrivant le CD
	 * @return CD correspondant
	 */
	private CD lireCd(File fichier) {
		try {
			ChargeurCD chargement = new ChargeurCD(fichier.getCanonicalPath());
			CD cd = chargement.chargerCD();
			return cd;
		} catch (IOException e) {
			// si le fichier n'est pas lisible
			System.out.println("Fichier " + fichier.getName() + " non lisible");
		} 
		return (null);
	}

}
