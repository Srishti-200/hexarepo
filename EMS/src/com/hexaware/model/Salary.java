package com.hexaware.model;

public class Salary {
	private int basic;
	private double hra;
	private double pf;
	private double gross;
	private double net;

	public Salary() {
		super();
	
	}

	public Salary(int basic, double hra, double pf, double gross, double net) {
		super();
		this.basic = basic;
		this.hra = hra;
		this.pf = pf;
		this.gross = gross;
		this.net = net;
	}

	public int getBasic() {
		return basic;
	}

	public void setBasic(int basic) {
		this.basic = basic;
	}

	public double getHra() {
		return hra;
	}

	public void setHra() {
		this.hra = this.basic * 0.1;
	}

	public double getPf() {
		return pf;
	}

	public void setPf() {
		this.pf = this.basic * 0.05;
	}

	public double getGross() {
		return gross;
	}

	public void setGross() {
		this.gross = this.getBasic() + this.getHra() + this.getPf();
	}

	public double getNet() {
		return net;
	}

	public void setNet() {
		this.net = this.getGross() - this.getPf();
	}

	@Override
	public String toString() {
		return "Salary [basic=" + basic + ", hra=" + hra + ", pf=" + pf + ", gross=" + gross + ", net=" + net + "]";
	}

}