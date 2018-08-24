package beans.formulaires;

import annotations.Regexp;
import validation.Validation;

public class FormulaireAjouterArchive {

	@Regexp(expr = "(.+)", value = "Il faut une archive")
	private String archiveName;

	public String getArchiveName() {
		return archiveName;
	}

	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}

	/**
	 * Permet de verifier les champs du formulaire d'ajoue d'archive meteo
	 * 
	 * @return un objet valisation
	 */
	public Validation getValidation() {
		Validation validation = new Validation();

		validation.regexp(FormulaireAjouterArchive.class, "archiveName", this.getArchiveName());

		return validation;
	}
}
