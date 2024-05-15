package donnees;


/**
 * la classe InfoPiste modelise les donnees d'une piste de CD a savoir
 * <ul>
 * <li>le nom de la piste
 * <li>sa duree en millisecondes
 * </ul>
 * 
 * Cette classe est utilisee dans la construction de CD qui stocke comme donnee
 * complementaire le nom de l'artiste, le nom du CD et le numero de la piste.
 * 
 */
public class InfoPiste {

	/**
	 * attributs de la piste du CD
	 */
	private String nomPiste;
	private int dureeMillisecondes;

	/**
	 * constructeur simple
	 * 
	 * @param nom
	 *            de la piste
	 * @param duree
	 *            de la piste en milliseconde
	 */
	public InfoPiste(String nom, int duree) {
		this.nomPiste = nom;
		this.dureeMillisecondes = duree;
	}

	/**
	 * retourne le descriptif de la piste a savoir le nom et le descriptif en
	 * minutes
	 */
	public String toString() {
		String r = "";
		String dureeMinute = dureeEnMinutes(this.dureeMillisecondes);
		r += nomPiste + " (" + dureeMinute + ")";
		return (r);
	}

	/**
	 * methode privee qui calcule la duree en minutes d'une piste a partir d une
	 * duree en milliseconde
	 * 
	 * @param millisecondes
	 *            la duree de la piste en milliseceondes
	 * @return une chaine correspondant a la duree en minute et secondes
	 */
	private String dureeEnMinutes(int millisecondes) {
		int secondes = millisecondes / 1000;
		int min = secondes / 60;
		secondes = secondes % 60;

		String sSecondes = "" + secondes;
		if (secondes < 10)
			sSecondes = "0" + secondes;
		return ("" + min + ":" + sSecondes);
	}
}
