package shi.alg.ch23.ttt;

public enum CellType {
	CELL_EMPTY('-'), CELL_O('o'), CELL_X('x');

    char c;

	CellType(char c) {
		this.c = c;
	}
	
	public char getValue() {
        return c;
    }
}
