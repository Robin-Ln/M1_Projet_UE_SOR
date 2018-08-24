package serveur;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Date;
import java.util.Calendar;

import beans.meteo.Meteo;
import beans.meteo.Temps;
import interfaceRmi.ServeurRmi;

public class ClientRmi {

	public static void main(String[] args) {

		int port = 3000;

		try {
			Registry registry = LocateRegistry.getRegistry(port);

			ServeurRmi serveur = (ServeurRmi) registry.lookup("serveurRmi");

			// creation de l'obket meteo
			Meteo meteo1 = new Meteo();
			meteo1.setLieu("test");
			meteo1.setTemps(Temps.valueOf("NUAGE"));
			meteo1.setDate("12/10/2018");

			meteo1.setMin(Double.parseDouble("12.0"));
			meteo1.setMax(Double.parseDouble("12.0"));
			meteo1.setMoy(Double.parseDouble("12.0"));

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2020);
			calendar.set(Calendar.MONTH, 12 - 1);
			calendar.set(Calendar.DAY_OF_MONTH, 12);
			Date sqlDate = new Date(calendar.getTimeInMillis());

			// ajout a la bdd
			serveur.ouvrir();
			serveur.ajouterMeteos(meteo1, sqlDate);
			serveur.fermer();

		} catch (Exception e) {
			System.out.println("Erreur client RMI");
		}
	}
}
