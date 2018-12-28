package com.sample.startup.gc.info;

public class LoadAvg {

	private float load1;
	private float load2;
	private float load3;

	public LoadAvg(float load1, float load2, float load3) {
		this.load1 = load1;
		this.load2 = load2;
		this.load3 = load3;
	}

	public LoadAvg(String load1, String load2, String load3) {
		try {
			this.load1 = Float.parseFloat(load1);
			this.load2 = Float.parseFloat(load2);
			this.load3 = Float.parseFloat(load3);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("数据转换异常");
		}
	}

	public LoadAvg(String[] load) {
		this(load[0], load[1], load[2]);
	}

	public float getLoad1() {
		return load1;
	}

	public void setLoad1(float load1) {
		this.load1 = load1;
	}

	public float getLoad2() {
		return load2;
	}

	public void setLoad2(float load2) {
		this.load2 = load2;
	}

	public float getLoad3() {
		return load3;
	}

	public void setLoad3(float load3) {
		this.load3 = load3;
	}

	@Override
	public String toString() {
		return "Load Average: " + load1 + " / " + load2 + " / " + load3;
	}
}
