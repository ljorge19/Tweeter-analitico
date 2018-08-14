package br.com.fiap.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.fiap.Utils.Datas;
import br.com.fiap.Utils.Quantidades;
import br.com.fiap.Utils.TwitterConectionFactory;
import twitter4j.DirectMessage;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class Main {

	public static void main(String[] args) {
		String queryDoTwitar = "";
		int contadorPrimeiroUsuario = 0;
		int contadorPrimeiroData = 0;
		List<Status> listaTweets = new ArrayList<>();
		Quantidades quantidade = new Quantidades();

		Twitter twitter;
		TwitterConectionFactory twitterConectionFactory = new TwitterConectionFactory();
		twitter = twitterConectionFactory.main(args);

		try {

			int contadorDias = 0;
			long qtdTweetsTemp = 0;
			long qtdReTweetsTemp = 0;
			long qtdLikesTemp = 0;

			String dataFormatadaInicial = Datas.getDataInicial();
			String datas[] = dataFormatadaInicial.split("/");
			String ano = datas[0];
			String mes = datas[1];
			String dia = datas[2];
			// nesse metodo janeiro é 0 e assim por diante, sempre subtrair 1 da data
			int mesCorreto = Integer.parseInt(mes);
			mesCorreto = mesCorreto - 1;
			// GregorianCalendar gCalendarInicio = new GregorianCalendar(2018, 6, 21);
			GregorianCalendar gCalendarInicio = new GregorianCalendar(Integer.parseInt(ano), mesCorreto,
					Integer.parseInt(dia));

			// System.out.println(gCalendarInicio.getTime());
			GregorianCalendar gCalendarAgora = new GregorianCalendar();
			// System.out.println(gCalendarAgora.getTime());

			SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");

			while (gCalendarInicio.before(gCalendarAgora)) {

				Query query = new Query("#java8");
				queryDoTwitar = query.toString();
				GregorianCalendar gCalendarAnterior = new GregorianCalendar();
				gCalendarAnterior.setTime(gCalendarInicio.getTime());
				gCalendarAnterior.add(GregorianCalendar.DAY_OF_MONTH, -1);

				/*
				 * System.out.println("\n");
				 * 
				 * System.out.println(gCalendarAnterior.getTime());
				 * System.out.println(gCalendarInicio.getTime());
				 * 
				 * System.out.println("\n");
				 */

				query.setSince(formatador.format(gCalendarAnterior.getTime()));
				query.setUntil(formatador.format(gCalendarInicio.getTime()));
				QueryResult result;
				result = twitter.search(query);

				while (result.hasNext()) {
					query = result.nextQuery();
					for (Status status : result.getTweets()) {
						listaTweets.add(status);

						// caso queira ver todas informações
						
						  System.out.println("-----------------------------------------------");
						  System.out.println("Usuário: " + status.getUser().getScreenName());
						  System.out.println("Mensagem: " + status.getText());
						  System.out.println("Data de Criação: " + status.getCreatedAt());
						  System.out.println("Número de Favoritos: " + status.getFavoriteCount());
						  System.out.println("Geolocalização: " + status.getGeoLocation());
						  System.out.println("Lugar: " + status.getPlace());
						  System.out.println("Número de Retweets: " + status.getRetweetCount());
						  System.out.println("-----------------------------------------------\n");
						 

						qtdTweetsTemp++;
						qtdReTweetsTemp = qtdReTweetsTemp + status.getRetweetCount();
						qtdLikesTemp = qtdLikesTemp + status.getFavoriteCount();

					}
					result = twitter.search(query);
				}

				contadorDias++;
				gCalendarInicio.add(GregorianCalendar.DAY_OF_MONTH, 1);

				switch (contadorDias) {
				case 1:
					quantidade.setQtdtweetsDia1(qtdTweetsTemp);
					quantidade.setQtdRetweetsDia1(qtdReTweetsTemp);
					quantidade.setQtdLikesDia1(qtdLikesTemp);
					quantidade.setDia1(gCalendarAnterior.getTime());
					break;
				case 2:
					quantidade.setQtdtweetsDia2(qtdTweetsTemp);
					quantidade.setQtdRetweetsDia2(qtdReTweetsTemp);
					quantidade.setQtdLikesDia2(qtdLikesTemp);
					quantidade.setDia2(gCalendarAnterior.getTime());
					break;
				case 3:
					quantidade.setQtdtweetsDia3(qtdTweetsTemp);
					quantidade.setQtdRetweetsDia3(qtdReTweetsTemp);
					quantidade.setQtdLikesDia3(qtdLikesTemp);
					quantidade.setDia3(gCalendarAnterior.getTime());
					break;
				case 4:
					quantidade.setQtdtweetsDia4(qtdTweetsTemp);
					quantidade.setQtdRetweetsDia4(qtdReTweetsTemp);
					quantidade.setQtdLikesDia4(qtdLikesTemp);
					quantidade.setDia4(gCalendarAnterior.getTime());
					break;
				case 5:
					quantidade.setQtdtweetsDia5(qtdTweetsTemp);
					quantidade.setQtdRetweetsDia5(qtdReTweetsTemp);
					quantidade.setQtdLikesDia5(qtdLikesTemp);
					quantidade.setDia5(gCalendarAnterior.getTime());
					break;
				case 6:
					quantidade.setQtdtweetsDia6(qtdTweetsTemp);
					quantidade.setQtdRetweetsDia6(qtdReTweetsTemp);
					quantidade.setQtdLikesDia6(qtdLikesTemp);
					quantidade.setDia6(gCalendarAnterior.getTime());
					break;
				case 7:
					quantidade.setQtdtweetsDia7(qtdTweetsTemp);
					quantidade.setQtdRetweetsDia7(qtdReTweetsTemp);
					quantidade.setQtdLikesDia7(qtdLikesTemp);
					quantidade.setDia7(gCalendarAnterior.getTime());
					break;
				default:
					break;
				}

				// Ordenando por usuario
				Collections.sort(listaTweets, new Comparator<Status>() {
					@Override
					public int compare(Status p1, Status p2) {
						return p1.getUser().getScreenName().compareTo(p2.getUser().getScreenName());
					}
				});

				for (int i = 0; i < listaTweets.size(); i++) {

					if (contadorPrimeiroUsuario == 0) {
						quantidade.setPrimeiroUsuario(listaTweets.get(i).getUser().getScreenName());
						contadorPrimeiroUsuario++;
					}

					quantidade.setUltimoUsuario(listaTweets.get(i).getUser().getScreenName());

				}

				// Ordenando por data
				Collections.sort(listaTweets, new Comparator<Status>() {
					@Override
					public int compare(Status p1, Status p2) {
						return p1.getCreatedAt().compareTo(p2.getCreatedAt());
					}
				});

				for (int i = 0; i < listaTweets.size(); i++) {

					if (contadorPrimeiroData == 0) {
						quantidade.setPrimeiroData(listaTweets.get(i).getCreatedAt());
						contadorPrimeiroData++;
					}

					quantidade.setUltimaData(listaTweets.get(i).getCreatedAt());

				}

				qtdTweetsTemp = 0;
				qtdReTweetsTemp = 0;
				qtdLikesTemp = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String dia1 = null;
		String dia2 = null;
		String dia3 = null;
		String dia4 = null;
		String dia5 = null;
		String dia6 = null;
		String dia7 = null;

		// tirando horário zerado dos dias anteriores
		if (quantidade.getDia1() != null) {
			dia1 = (quantidade.getDia1().toString().replace("00:00:00", ""));
		} else {
			dia1 = quantidade.getDia1().toString();

		}

		if (quantidade.getDia2() != null) {
			dia2 = (quantidade.getDia2().toString().replace("00:00:00", ""));
		} else {
			dia2 = quantidade.getDia2().toString();

		}

		if (quantidade.getDia3() != null) {
			dia3 = (quantidade.getDia3().toString().replace("00:00:00", ""));
		} else {
			dia3 = quantidade.getDia3().toString();

		}

		if (quantidade.getDia4() != null) {
			dia4 = (quantidade.getDia4().toString().replace("00:00:00", ""));
		} else {
			dia4 = quantidade.getDia4().toString();

		}

		if (quantidade.getDia5() != null) {
			dia5 = (quantidade.getDia5().toString().replace("00:00:00", ""));
		} else {
			dia5 = quantidade.getDia5().toString();

		}

		if (quantidade.getDia6() != null) {
			dia6 = (quantidade.getDia6().toString().replace("00:00:00", ""));
		} else {
			dia6 = quantidade.getDia6().toString();

		}

		if (quantidade.getDia7() != null) {
			dia7 = (quantidade.getDia7().toString().replace("00:00:00", ""));
		} else {
			dia7 = quantidade.getDia7().toString();

		}

		
		
		String tweet1 =

				"\n" + " qtdTweets:  " + quantidade.getQtdtweetsDia1() + "\n" + " qtdFavoritos:  "
						+ quantidade.getQtdLikesDia1() + "\n" + " qtdretweet: " + quantidade.getQtdRetweetsDia1() + "\n"
						+ " dia: " + dia1 + "\n";

		try {
			// DirectMessage message1 = twitter.sendDirectMessage("@michelpf", tweet1);
			Status status = twitter.updateStatus(tweet1 + "@michelpf");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("bloqueio de postagem pelo twitter, pedido em analise para o desbloqueio, aguardando aprovação \n");

		}

		String tweet2 =

				"\n" + " qtdTweets:  " + quantidade.getQtdtweetsDia2() + "\n" + " qtdFavoritos:  "
						+ quantidade.getQtdLikesDia2() + "\n" + " qtdretweet: " + quantidade.getQtdRetweetsDia2() + "\n"
						+ " dia: " + dia2 + "\n";

		try {
			// DirectMessage message1 = twitter.sendDirectMessage("@michelpf", tweet2);
			Status status = twitter.updateStatus(tweet2 + "@michelpf");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("bloqueio de postagem pelo twitter, pedido em analise para o desbloqueio, aguardando aprovação \n");

		}

		String tweet3 =

				"\n" + " qtdTweets:  " + quantidade.getQtdtweetsDia3() + "\n" + " qtdFavoritos:  "
						+ quantidade.getQtdLikesDia3() + "\n" + " qtdretweet: " + quantidade.getQtdRetweetsDia3() + "\n"
						+ " dia: " + dia3 + "\n";

		try {
			// DirectMessage message1 = twitter.sendDirectMessage("@michelpf", tweet3);
			Status status = twitter.updateStatus(tweet3 + "@michelpf");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("bloqueio de postagem pelo twitter, pedido em analise para o desbloqueio, aguardando aprovação \n");

		}

		String tweet4 =

				"\n" + " qtdTweets:  " + quantidade.getQtdtweetsDia4() + "\n" + " qtdFavoritos:  "
						+ quantidade.getQtdLikesDia4() + "\n" + " qtdretweet: " + quantidade.getQtdRetweetsDia4() + "\n"
						+ " dia: " + dia4 + "\n";

		try {
			// DirectMessage message1 = twitter.sendDirectMessage("@michelpf", tweet4);
			Status status = twitter.updateStatus(tweet4 + "@michelpf");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("bloqueio de postagem pelo twitter, pedido em analise para o desbloqueio, aguardando aprovação \n");

		}

		String tweet5 =

				"\n" + " qtdTweets:  " + quantidade.getQtdtweetsDia5() + "\n" + " qtdFavoritos:  "
						+ quantidade.getQtdLikesDia5() + "\n" + " qtdretweet: " + quantidade.getQtdRetweetsDia5() + "\n"
						+ " dia: " + dia5 + "\n";

		try {
			// DirectMessage message1 = twitter.sendDirectMessage("@michelpf", tweet5);
			Status status = twitter.updateStatus(tweet5 + "@michelpf");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("bloqueio de postagem pelo twitter, pedido em analise para o desbloqueio, aguardando aprovação \n");

		}

		String tweet6 =

				"\n" + " qtdTweets:  " + quantidade.getQtdtweetsDia6() + "\n" + " qtdFavoritos:  "
						+ quantidade.getQtdLikesDia6() + "\n" + " qtdretweet: " + quantidade.getQtdRetweetsDia6() + "\n"
						+ " dia: " + dia6 + "\n";

		try {
			// DirectMessage message1 = twitter.sendDirectMessage("@michelpf", tweet6);
			Status status = twitter.updateStatus(tweet6 + "@michelpf");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("bloqueio de postagem pelo twitter, pedido em analise para o desbloqueio, aguardando aprovação \n");

		}

		String tweet7 =

				"\n" + " qtdTweets:  " + quantidade.getQtdtweetsDia7() + "\n" + " qtdFavoritos:  "
						+ quantidade.getQtdLikesDia7() + "\n" + " qtdretweet: " + quantidade.getQtdRetweetsDia7() + "\n"
						+ " dia: " + dia7 + "\n";

		try {
			// DirectMessage message1 = twitter.sendDirectMessage("@michelpf", tweet7);
			Status status = twitter.updateStatus(tweet7 + "@michelpf");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("bloqueio de postagem pelo twitter, pedido em analise para o desbloqueio, aguardando aprovação \n");

		}

		String tweet8 =

				"\n" + " primeiro User: " + quantidade.getPrimeiroUsuario() + "\n" + " ultimo user: "
						+ quantidade.getUltimoUsuario() + "\n" +

						"\n" + " primeira data: " + quantidade.getPrimeiroData() + "\n" + " ultima   data: "
						+ quantidade.getUltimaData() + "";

		try {
			// DirectMessage message1 = twitter.sendDirectMessage("@michelpf", tweet7);
			Status status = twitter.updateStatus(tweet8 + "@michelpf");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("bloqueio de postagem pelo twitter, pedido em analise para o desbloqueio, aguardando aprovação \n");


		}
		
		
		System.out.println("*********************************************************************************************************************************************");
		System.out.println("Relatório de Dados analíticos gerados através da hashtag solicitada");
		System.out.println("*********************************************************************************************************************************************");
		
		String mensagemFinal =

				"\n" + " qtdTweets :  " + quantidade.getQtdtweetsDia1() + "\n" + " qtdFavoritos:  "
						+ quantidade.getQtdLikesDia1() + "\n" + " qtdretweet: " + quantidade.getQtdRetweetsDia1() + "\n"
						+ " dia: " + dia1 + "\n" +

						"\n" + " qtdTweets :  " + quantidade.getQtdtweetsDia2() + "\n"
						+ " qtdFavoritos:  " + quantidade.getQtdLikesDia2() + "\n" + " qtdretweet: "
						+ quantidade.getQtdRetweetsDia2() + "\n" + " dia: " + dia2 + "\n" +

						"\n" + " qtdTweets " + quantidade.getQtdtweetsDia3() + "\n"
						+ " qtdFavoritos:  " + quantidade.getQtdLikesDia3() + "\n" + " qtdretweet: "
						+ quantidade.getQtdRetweetsDia3() + "\n" + " dia: " + dia3 + "\n" +

						"\n" + " qtdTweets " + quantidade.getQtdtweetsDia4() + "\n"
						+ " qtdFavoritos:  " + quantidade.getQtdLikesDia4() + "\n" + " qtdretweet: "
						+ quantidade.getQtdRetweetsDia4() + "\n" + " dia: " + dia4 + "\n" +

						"\n" + " qtdTweets " + quantidade.getQtdtweetsDia5() + "\n"
						+ " qtdFavoritos:  " + quantidade.getQtdLikesDia5() + "\n" + " qtdretweet: "
						+ quantidade.getQtdRetweetsDia5() + "\n" + " dia: " + dia5 + "\n" +

						"\n" + " qtdTweets " + quantidade.getQtdtweetsDia6() + "\n"
						+ " qtdFavoritos:  " + quantidade.getQtdLikesDia6() + "\n" + " qtdretweet: "
						+ quantidade.getQtdRetweetsDia6() + "\n" + " dia: " + dia6 + "\n" +

						"\n" + " qtdTweets " + quantidade.getQtdtweetsDia7() + "\n"
						+ " qtdFavoritos:  " + quantidade.getQtdLikesDia7() + "\n" + " qtdretweet: "
						+ quantidade.getQtdRetweetsDia7() + "\n" + " dia: " + dia7 + "\n" +

						"\n" + " primeiro User: " + quantidade.getPrimeiroUsuario() + "\n" + " ultimo user: "
						+ quantidade.getUltimoUsuario() + "\n" +

						"\n" + " primeira data: " + quantidade.getPrimeiroData() + "\n" + " ultima   data: "
						+ quantidade.getUltimaData();

		
		System.out.println(mensagemFinal);
		System.out.println("\n");
		System.out.println("*********************************************************************************************************************************************");
		

		;

	}
}