package math.other.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import math.cluster.CentroidCluster;
import math.cluster.kmeans.KMeansPlusPlusClusterer;

public class ImageSpliter {

	public static void main(String[] args) throws Exception {
		List<PixelPoint> pixels = new ArrayList<>();

		File file = new File("D:/test.jpg");
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int width = bi.getWidth();
		int height = bi.getHeight();
		int minx = bi.getMinX();
		int miny = bi.getMinY();
		for (int i = minx; i < width; i++) {
			for (int j = miny; j < height; j++) {
				int pixel = bi.getRGB(i, j);
				double[] rgb = new double[3];

				rgb[0] = (pixel & 0xff0000) >> 16;
				rgb[1] = (pixel & 0xff00) >> 8;
				rgb[2] = (pixel & 0xff);
				PixelPoint point = new PixelPoint(i, j, rgb);
				pixels.add(point);
			}
		}

		KMeansPlusPlusClusterer<PixelPoint> kmeans = new KMeansPlusPlusClusterer<PixelPoint>(7);
		List<CentroidCluster<PixelPoint>> res = kmeans.cluster(pixels);

		for (int i = 0; i < res.size(); i++) {
			List<PixelPoint> img = res.get(i).getPoints();
			String outname = "d:/Simg" + i+".jpg";
			outputImage(outname, img, width, height);
		}

	}

	private static void outputImage(String outname, List<PixelPoint> img, int width, int height) {
		File file = new File(outname);
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = (Graphics2D) bi.getGraphics();
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				int color = 0;
				Color c = new Color(color);
				g2.setColor(c);
				g2.drawLine(w, h, w + 1, h + 1);
			}
		}
		for(PixelPoint pix :img){
			int row = pix.getRow();
			int col = pix.getCol();
//			double[] rgb = pix.getPoint();
//			g2.setColor(new Color((int)rgb[0],(int)rgb[0],(int)rgb[0]));
			g2.setColor(Color.red);
			g2.drawLine(row, col, row + 1, col + 1);
		}
		try {
			ImageIO.write(bi, "jpg", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
