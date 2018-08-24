package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.meteo.Meteo;
import interfaceRmi.ServeurRmi;
import manager.Manager;

/**
 * Servlet implementation class consulterGraph
 */
@WebServlet("/ServletStatistique")
public class ServletStatistique extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletStatistique() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServeurRmi serveur = Manager.creer(request).getServeur();
		List<Meteo> listeMeteo = new ArrayList<Meteo>();
		serveur.ouvrir();
		listeMeteo = serveur.getMeteo();
		serveur.fermer();

		Map<String, Integer> annee = new HashMap<String, Integer>();

		for (int i = 0; i < listeMeteo.size(); i++) {
			String[] date = listeMeteo.get(i).getDate().toString().split("-");
			annee.put(date[0], 0);
		}

		Set<String> cles = annee.keySet();
		Iterator<String> it = cles.iterator();
		List<String> listeAnnee = new ArrayList<String>();

		while (it.hasNext()) {
			String cle = (String) it.next();
			listeAnnee.add(cle);
		}
		System.out.println(listeAnnee.toString());
		request.setAttribute("annees", listeAnnee);

		request.setAttribute("titre", "Statistique");
		request.setAttribute("contenu", "/WEB-INF/statistique/Statistique.jsp");
		request.getServletContext().getRequestDispatcher("/WEB-INF/models/model.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServeurRmi serveur = Manager.creer(request).getServeur();
		List<Meteo> listeMeteo = new ArrayList<Meteo>();
		serveur.ouvrir();
		listeMeteo = serveur.getMeteo();
		serveur.fermer();

		Map<String, Integer> annee = new HashMap<String, Integer>();

		for (int i = 0; i < listeMeteo.size(); i++) {
			String[] date = listeMeteo.get(i).getDate().toString().split("-");
			annee.put(date[0], 0);
		}

		Set<String> cles = annee.keySet();
		Iterator<String> it = cles.iterator();
		List<String> listeAnnee = new ArrayList<String>();

		while (it.hasNext()) {
			String cle = (String) it.next();
			listeAnnee.add(cle);
		}
		System.out.println(listeAnnee.toString());
		request.setAttribute("annees", listeAnnee);

		if (request.getParameter("actualiserStat") != null) {
			try {

				List<Meteo> listeMeteoBis = new ArrayList<>();
				for (int i = 0; i < listeMeteo.size(); i++) {
					String[] date = listeMeteo.get(i).getDate().toString().split("-");
					if (date[0].equals(request.getParameter("annee"))) {
						listeMeteoBis.add(listeMeteo.get(i));
					}
				}

				String[] donnee = calculStat(listeMeteoBis);
				System.out.println(donnee[0].toString());

				request.setAttribute("max", donnee[0]);
				request.setAttribute("min", donnee[1]);
				request.setAttribute("moy", donnee[2]);
				request.setAttribute("titre", "Statistique");
				request.setAttribute("contenu", "/WEB-INF/statistique/Statistique.jsp");
				request.getServletContext().getRequestDispatcher("/WEB-INF/models/model.jsp").forward(request,
						response);

			} catch (Exception e) {
				System.out.println("Erreur client RMI");
			}

		}
	}

	// erreur cast 1 vers 01 ...
	private String[] calculStat(List<Meteo> liste) {
		String[] resultat = { "", "", "" };
		Map<String, List<Double>> donneMax = new HashMap<String, List<Double>>();
		Map<String, List<Double>> donneMin = new HashMap<String, List<Double>>();
		Map<String, List<Double>> donneMoy = new HashMap<String, List<Double>>();

		System.out.println("t1");
		for (int i = 1; i < 13; i++) {
			String k;
			if (i < 10) {
				k = "0" + i;
			} else {
				k = i + "";
			}
			List<Double> l1 = new ArrayList<Double>();
			donneMax.put(k, l1);
			List<Double> l2 = new ArrayList<Double>();
			donneMin.put(k, l2);
			List<Double> l3 = new ArrayList<Double>();
			donneMoy.put(k, l3);
		}
		System.out.println("t2");

		for (int i = 0; i < liste.size(); i++) {

			String[] date = liste.get(i).getDate().toString().split("-");

			donneMax.get(date[1]).add(liste.get(i).getMax());
			donneMin.get(date[1]).add(liste.get(i).getMin());
			donneMoy.get(date[1]).add(liste.get(i).getMoy());

		}
		System.out.println("t3");

		Set<String> cles1 = donneMax.keySet();
		Iterator<String> it1 = cles1.iterator();
		String[] chaineMax = { "", "", "", "", "", "", "", "", "", "", "", "" };

		while (it1.hasNext()) {
			String cle = (String) it1.next();
			List<Double> valeurs = donneMax.get(cle);
			if (valeurs.size() > 0) {
				System.out.println("t3.1 " + valeurs.size());
				double max = valeurs.get(0);
				for (int i = 0; i < valeurs.size(); i++) {
					if (valeurs.get(i) > max) {
						max = valeurs.get(i);
					}
				}
				chaineMax[Integer.parseInt(cle) - 1] = max + "";
			}
		}
		System.out.println("t4");
		resultat[0] = this.conversion(chaineMax);

		Set<String> cles2 = donneMax.keySet();
		Iterator<String> it2 = cles2.iterator();
		String[] chaineMin = { "", "", "", "", "", "", "", "", "", "", "", "" };

		while (it2.hasNext()) {
			String cle = (String) it2.next();
			List<Double> valeurs = donneMax.get(cle);
			if (valeurs.size() > 0) {
				double min = valeurs.get(0);
				for (int i = 0; i < valeurs.size(); i++) {
					if (valeurs.get(i) < min) {
						min = valeurs.get(i);
					}
				}
				chaineMin[Integer.parseInt(cle) - 1] = min + "";
			}
		}
		System.out.println("t5");
		resultat[1] = this.conversion(chaineMin);

		Set<String> cles3 = donneMoy.keySet();
		Iterator<String> it3 = cles3.iterator();
		String[] chaineMoy = { "", "", "", "", "", "", "", "", "", "", "", "" };

		while (it3.hasNext()) {
			String cle = (String) it3.next();
			List<Double> valeurs = donneMax.get(cle);
			if (valeurs.size() > 0) {
				double somme = 0;
				for (int i = 0; i < valeurs.size(); i++) {
					somme += valeurs.get(i);
				}
				chaineMoy[Integer.parseInt(cle) - 1] = (somme / valeurs.size()) + "";
			}
		}

		resultat[2] = this.conversion(chaineMoy);

		return resultat;
	}

	private String conversion(String[] tab) {
		String chaine = "";
		for (int i = 0; i < tab.length; i++) {
			chaine += tab[i];
			if (i < tab.length - 1) {
				chaine += ",";
			}
		}
		System.out.println(chaine);
		return chaine;
	}

}
