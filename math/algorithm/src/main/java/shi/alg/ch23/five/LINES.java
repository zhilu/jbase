package shi.alg.ch23.five;

public class LINES {
	public static int  MAX_LINE_S = 9;
	
	int line_s[] = new int[MAX_LINE_S];
    int off_dir;
    
    public static LINES line_cpts[] = new LINES[4];
    static {
    	for (int i = 0; i < line_cpts.length; i++) {
    		line_cpts[i]=new LINES();
		}
    }
}
