package com.red.stocks.fxml.model;

public class Sector {
	
	private String sectorinfo, sectorDescription;
	
	
	public Sector(String sectorinfo, String sectorDescription) {
		super();
		this.sectorinfo = sectorinfo;
		this.sectorDescription = sectorDescription;
	}

	public String getSectorinfo() {
		return sectorinfo;
	}

	public void setSectorinfo(String sectorinfo) {
		this.sectorinfo = sectorinfo;
	}

	public String getSectorDescription() {
		return sectorDescription;
	}

	public void setSectorDescription(String sectorDescription) {
		this.sectorDescription = sectorDescription;
	}

	@Override
	public String toString() {
		return "Sector [sector=" + sectorinfo + ", sectorDescription=" + sectorDescription + "]";
	}


	
}
