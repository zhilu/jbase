package math.other.image;

import java.util.Arrays;

import math.cluster.Clusterable;

public class PixelPoint implements Clusterable {

	private int row; 
	private int col;
	
	private double [] rgb = null;
	
	public PixelPoint(int row, int col, double[] rgb) {
		super();
		this.row = row;
		this.col = col;
		this.rgb = rgb;
	}


	public int getRow() {
		return row;
	}


	public int getCol() {
		return col;
	}


	public double[] getRgb() {
		return rgb;
	}


	@Override
	public double[] getPoint() {
		return rgb;
	}

	@Override
	public String toString() {
		return "PixelPiont [row=" + row + ", col=" + col + ", rgb=" + Arrays.toString(rgb) + "]";
	}

}
