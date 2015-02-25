package com.garmendia.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.garmendia.domain.Match;


public class FixtureService {
	
	private HttpClient client;

	public FixtureService() {
		super();
		this.client = new HttpClient();
	}
	
	
	public void Sync(String url) {
		
		String fixtureHTML = getHTMLCode(url);
		
		Map<String,ArrayList<Match>> fixture = getMatches(fixtureHTML);
		
		UpdatedFixture.getInstance().setFixture(fixture);

	}
	
	private String getHTMLCode(String silverCupURL) {

		String responseBodyString = null;
		// Create a method instance.
		GetMethod method = new GetMethod(silverCupURL);

		// Provide custom retry handler is necessary
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));

		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}
			
			responseBodyString = method.getResponseBodyAsString();

		} catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
		}

		return responseBodyString;

	}

	/**
	 * Return 
	 * @return
	 */
	public List<String> getDates() {
		Map<String,ArrayList<Match>> fixture = UpdatedFixture.getInstance().getFixture();
		List<String> list = new ArrayList<String>(fixture.keySet());
		return list;
	}
	
	private Map<String,ArrayList<Match>> getMatches(String responseBodyString) {
		
		Map<String,ArrayList<Match>> fixture = new HashMap<String, ArrayList<Match>>();
		
		responseBodyString = responseBodyString.replace("Estilo120","Estilo33");
		Document parsedDocument = Jsoup.parse(responseBodyString);
		Elements someDivs = parsedDocument.getElementsByClass("Estilo33");
		Elements someOtherDivs = parsedDocument.getElementsByClass("Estilo28");
		 
		 int equipos1 = 7;
		 int equipos2 = 8;
		 
		 for (int i = 0; i < 53; i++) {
			
			 String time = getMatchTime(someDivs,i);
			 
			 String local = getLocalTeam(someOtherDivs, equipos1);
			
			 String visit = getVisitTeam(someOtherDivs, equipos1, equipos2);
			 
			 String gameDate = getMatchDate(someDivs, i);
			 
			 Match match = new Match(local, visit, time);
			 
			 if (fixture.containsKey(gameDate)) {
				 fixture.get(gameDate).add(match);
			 }else {
				 ArrayList<Match> matches = new ArrayList<Match>();
				 matches.add(match);
				 fixture.put(gameDate,matches);
			 }
			 
			 if (i == 46){
				 equipos1 = 99;
				 equipos2 = 100;
			 }else{
				 equipos1 = equipos1 + 2;
				 equipos2 = equipos2 + 2;
			 }
				 
		 }
		 
		 return fixture;
	}

	/**
	 * 
	 * @param someOtherDivs
	 * @param equipos1
	 * @return
	 */
	private String getLocalTeam(Elements someOtherDivs, int equipos1) {
		String local = someOtherDivs.get(equipos1).text().toString();
		return local;
	}


	/**
	 * @param someDivs
	 * @param i
	 * @return
	 */
	private String getMatchDate(Elements someDivs, int i) {
		Element someBrother = someDivs.get(i).parent().previousElementSibling() != null ? someDivs.get(i).parent().previousElementSibling() : someDivs.get(i).parent().parent().previousElementSibling();
		 
		 while ((someBrother.html().contains("Estilo33") && !someBrother.html().contains("Estilo35")) || someBrother.html().contains("Estilo118") || someBrother.html().contains("<em>")) {
			 //Going up in the tree nodes asking to each TR brother searching the date
			 someBrother = someBrother.previousElementSibling();
		 }
		 
		 //someBrother has the game date
		 String gameDate =  someBrother.text();
		return gameDate;
	}
	
	/**
	 * Get the specific time for a given math index
	 * @param someDivs info container 
	 * @param i index
	 * @return
	 */
	private String getMatchTime(Elements someDivs,int i) {

		String time = null;

		while (someDivs.get(i).nodeName() == "span"
				&& someDivs.get(i + 1).nodeName() != "span" 
				&& (i != 31) && (i != 44) && (i != 49)
				&& !someDivs.get(i).html().toString().contains("Estilo120")) {
			
			i++;
		}

		// Patch
		if (someDivs.get(i).html().toString().contains("Estilo35")) {
			i++;
		}

		time = someDivs.get(i).text().toString();
		
		return time;

	}
	
	/**
	 * Get the name of the visit team 
	 * @param someOtherDivs info container
	 * @param equipos1 local team index
	 * @param equipos2 visit team index
	 * @return
	 */
	private String getVisitTeam(Elements someOtherDivs, int equipos1, int equipos2) {
		// Patch
		if (someOtherDivs.get(equipos2).text().toString().contains("Vs.")) {
			equipos1++;
			equipos2++;
		}
		String visit = getLocalTeam(someOtherDivs, equipos2);

		return visit;
	}
}