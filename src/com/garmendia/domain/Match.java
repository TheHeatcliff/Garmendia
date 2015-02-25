package com.garmendia.domain;

public class Match {
	
	private String visit;
	private String local;
	private String time;
	
	/**
	 * @param local
	 * @param visit
	 * @param time
	 */
	public Match(String local, String visit, String time) {
		this.local = local;
		this.visit = visit;
		this.time = time;
	}

	public String getLocal() {
		return local;
	}
	
	public String getVisit() {
		return visit;
	}
	
	public String getTime() {
		return time;
	}

	@Override
	public String toString() {
		return "Match [visit=" + visit + ", local=" + local + ", time=" + time
				+ "]" + "\n";
	}
	
}