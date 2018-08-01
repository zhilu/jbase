package shi.alg.ch22;

public enum ShapeT {
	S_S(0), S_Z(1), S_L(2), S_J(3), S_I(4), S_O(5), S_T(6);
	
	int idx;
	ShapeT(int num){
		this.idx=num;
	}
	
	public int getIdx(){
		return idx;
	}
}
