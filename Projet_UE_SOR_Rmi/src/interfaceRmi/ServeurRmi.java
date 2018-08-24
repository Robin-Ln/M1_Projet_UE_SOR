package interfaceRmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.List;

import beans.meteo.Image;
import beans.meteo.Meteo;
import beans.utilisateur.Utilisateur;

public interface ServeurRmi extends Remote {

	/**
	 * Initialise la connexion a la bdd
	 * 
	 * @return true si la connexion est etablie
	 * @throws RemoteException
	 */
	public boolean ouvrir() throws RemoteException;

	/**
	 * Ferme la connexion
	 * 
	 * @throws RemoteException
	 */
	public void fermer() throws RemoteException;

	/**
	 * Permet l'identification d'un utilisateur
	 * 
	 * @param utilisateur
	 * @return true si l'identification a reussi false sinon
	 * @throws RemoteException
	 */
	public boolean identification(Utilisateur utilisateur) throws RemoteException;

	/**
	 * Permet de verifier si un utilisateur est deja inscri
	 * 
	 * @param utilisateur
	 * @return true si l'utilisateur est deja inscrit a reussi false sinon
	 * @throws RemoteException
	 */
	public boolean dejaInscrit(Utilisateur utilisateur) throws RemoteException;

	/**
	 * Enregistre un utilisateur dans la base de donnée
	 * 
	 * @param utilisateur
	 * @throws RemoteException
	 */
	public void inscription(Utilisateur utilisateur) throws RemoteException;

	/**
	 * Permet de recupérer toutes les données meteos
	 * 
	 * @return une liste de sonnées meteos
	 * @throws RemoteException
	 */
	public List<Meteo> getMeteo() throws RemoteException;

	/**
	 * ajoute une données meteo dans la bdd
	 * 
	 * @param meteo
	 * @return l'id de la données meteo
	 * @throws RemoteException
	 */
	public int ajouterMeteo(Meteo meteo) throws RemoteException;

	/**
	 * suppime une donnée meteo de la bdd
	 * 
	 * @param id
	 *            id de la donnée meteo a supprimer
	 * @throws RemoteException
	 */
	public void supprimerMeteo(int id) throws RemoteException;

	/**
	 * Permet de verifier si une donnée meteo existe deja
	 * 
	 * @param meteo
	 * @return l'id de la donnée meteo, -1 si elle n'existe pas
	 * @throws RemoteException
	 */
	public int meteoExiste(Meteo meteo) throws RemoteException;

	/**
	 * Met a jour une donné meteo
	 * 
	 * @param meteo
	 * @param idMeteo
	 * @throws RemoteException
	 */
	public void majMeteo(Meteo meteo, int idMeteo) throws RemoteException;

	/**
	 * ajoute pluisieur donnée méteos
	 * 
	 * @param meteo
	 * @param dateFin
	 * @throws RemoteException
	 */
	public void ajouterMeteos(Meteo meteo, Date dateFin) throws RemoteException;

	/**
	 * Ajoute une image a une donnée meteo
	 * 
	 * @param idMeteo
	 *            id de la donnée meteo
	 * @param image
	 * @throws RemoteException
	 */
	public void ajouterImage(int idMeteo, Image image) throws RemoteException;

	/**
	 * supprime une image
	 * 
	 * @param idImage
	 * @return
	 * @throws RemoteException
	 */
	public int supprimerImage(int idImage) throws RemoteException;

	/**
	 * retoure la liste des images assossier a une donnée meteo
	 * 
	 * @param id
	 *            id de la donnée meteo
	 * @return
	 * @throws RemoteException
	 */
	public List<Image> getlisteImage(int id) throws RemoteException;

	/**
	 * Ajoute une liste de données meteos a la bdd
	 * 
	 * @param listeMeteo
	 * @throws RemoteException
	 */
	public void ajouterListeMeteo(List<Meteo> listeMeteo) throws RemoteException;

	/**
	 * Recuperer une donnée meteo en fonction d'un id
	 * 
	 * @param idMeteo
	 * @return
	 * @throws RemoteException
	 */
	public Meteo getMeteo(int idMeteo) throws RemoteException;

	/**
	 * Recuperer le pdf generer
	 * 
	 * @param idMeteos
	 *            id des données meteo contenues par le pdf
	 * @return
	 * @throws RemoteException
	 */
	public byte[] generationPdf(int[] idMeteos) throws RemoteException;

}
