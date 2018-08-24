package validation;

import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import annotations.Regexp;

public class Validation {

	/**
	 * Nous indique si il y a oui ou non des erreurs dans les formulaires
	 */
	boolean valide = true;

	/**
	 * La valeur des champs des formulaires sont enregistrés
	 */
	Hashtable<String, String> valeurs = new Hashtable<String, String>();

	/**
	 * Les erreurs des champs des formulaires sont enregistrés
	 */
	Hashtable<String, String> erreurs = new Hashtable<String, String>();

	/**
	 * Cette methode verifier si les champs sont valides, elle met a jour la map
	 * valeurs, et la map erreurs si le champ n'est pa valide.
	 * 
	 * @param c
	 *            permet de recupere la valeur de l'anotation Regexp
	 * @param param
	 *            nom d'un champ du formulaire
	 * @param val
	 *            valeur d'un champ du formulaire
	 * @return vrai si le champ est valide false sinon
	 */
	public boolean regexp(Class<?> c, String param, String val) {
		boolean res = false;
		try {
			Field f = c.getDeclaredField(param);
			Regexp ann = f.getAnnotation(Regexp.class);
			Pattern pattern = Pattern.compile(ann.expr());
			Matcher matcher = pattern.matcher(val);
			if (!matcher.matches()) {
				erreurs.put(param, ann.value());
				valide = false;
			}
			valeurs.put(param, val);
		} catch (Exception e) {
			valide = false;
			System.out.println("Erreur Validation.regexp " + e.getMessage());
		}
		return res;
	}

	public boolean isValide() {
		return valide;
	}

	public void setValide(boolean valide) {
		this.valide = valide;
	}

	public Hashtable<String, String> getValeurs() {
		return valeurs;
	}

	public void setValeurs(Hashtable<String, String> valeurs) {
		this.valeurs = valeurs;
	}

	public Hashtable<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(Hashtable<String, String> erreurs) {
		this.erreurs = erreurs;
	}
}
