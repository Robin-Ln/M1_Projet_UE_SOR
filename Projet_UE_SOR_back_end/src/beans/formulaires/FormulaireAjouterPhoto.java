package beans.formulaires;

import annotations.Regexp;
import validation.Validation;

public class FormulaireAjouterPhoto {

	@Regexp(expr = "(\\d+)", value = "Il faut selectionner un element")
	private String idMeteo;

	@Regexp(expr = "(.*)", value = "Il faut une image jpeg")
	private String imageName;
	

	public String getIdMeteo() {
		return idMeteo;
	}



	public void setIdMeteo(String idMeteo) {
		this.idMeteo = idMeteo;
	}



	public String getImageName() {
		return imageName;
	}



	public void setImageName(String imageName) {
		this.imageName = imageName;
	}


	/**
	 * Permet de verifier les champs du formulaire d'ajoue de photo
	 * 
	 * @return un objet validation
	 */
	public Validation getValidation() {
		Validation validation = new Validation();

		validation.regexp(FormulaireAjouterPhoto.class, "idMeteo", this.getIdMeteo());
		validation.regexp(FormulaireAjouterPhoto.class, "imageName", this.getImageName());

		return validation;
	}
}
