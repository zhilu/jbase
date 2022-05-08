package shi.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLtag {
	public static void main(String[] args) throws Exception {

	}

	public static void HTMLimg(StringBuffer html) throws Exception {
		// Matcher for isolating <img> tags
		Matcher mImg = Pattern.compile("(?id)<IMG\\s+(.*?)/?>").matcher(html);

		// Matchers that isolate the SRC, WIDTH, and HEIGHT attributes within a
		// tag (with very naïve regexes)
		Matcher mSrc = Pattern.compile("(?ix)\\bSRC   =(\\S+)").matcher(html);
		Matcher mWidth = Pattern.compile("(?ix)\\bWIDTH =(\\S+)").matcher(html);
		Matcher mHeight = Pattern.compile("(?ix)\\bHEIGHT=(\\S+)").matcher(html);

		int imgMatchPointer = 0; // The first search begins at the start of the
									// string
		while (mImg.find(imgMatchPointer)) {

			imgMatchPointer = mImg.end(); // Next image search starts from where
											// this one ended

			// Look for our attributes within the body of the just-found image
			// tag
			Boolean hasSrc = mSrc.region(mImg.start(1), mImg.end(1)).find();
			Boolean hasHeight = mHeight.region(mImg.start(1), mImg.end(1)).find();
			Boolean hasWidth = mWidth.region(mImg.start(1), mImg.end(1)).find();

			// If we have a SRC attribute, but are missing WIDTH and/or HEIGHT .
			// . .
			if (hasSrc && (!hasWidth || !hasHeight)) {

				java.awt.image.BufferedImage i = // this fetches the image
						javax.imageio.ImageIO.read(new java.net.URL(mSrc.group(1)));

				String size; // Will hold the missing WIDTH and/or HEIGHT
								// attributes
				if (hasWidth)
					// We're told the width, so compute the height that
					// maintains the proper aspect ratio
					size = "height='" + (int) (Integer.parseInt(mWidth.group(1)) * i.getHeight() / i.getWidth()) + "' ";
				else if (hasHeight)
					// We're told the height, so compute the width that
					// maintains the proper aspect ratio
					size = "width='" + (int) (Integer.parseInt(mHeight.group(1)) * i.getWidth() / i.getHeight()) + "' ";
				else // We're told neither, so just insert the actual size
					size = "width='" + i.getWidth() + "' " + "height='" + i.getHeight() + "' ";

				html.insert(mImg.start(1), size); // Update the HTML in place
				imgMatchPointer += size.length(); // Account for the new text in
													// mImg's eyes

			}

		}
	}
}
