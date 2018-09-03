package alg.sort;

public class ShellSortApp {
	/**
	 * ϣ������˼�룺<br>
	 * 1.ѡ�����h��ʹ�ü��h�����ݶ�������<br>
	 * 2.�𽥽�h��С��1����Ϊ��������<br>
	 * 
	 * ���ٲ���������ƶ��Ĵ���������<br>
	 * ���Ӷ� <br>
	 * N^(7/6)~N^(3/2)
	 * 
	 * @param arrary
	 */
	public static void shellSort(SimpleArray arrary) {
		long[] theArray = arrary.getTheArray();
		int nElems = arrary.getnElems();
		int inner, outer;
		long temp;

		// ȷ��������� y=3y+1
		int h = 1;
		while (h <= nElems / 3) {
			h = h * 3 + 1;
		}

		while (h > 0) {
			//���Ʋ�������
			for (outer = h; outer < nElems; outer++) {
				temp = theArray[outer];
				inner = outer;
				while (inner > h - 1 && theArray[inner - h] >= temp) {
					theArray[inner] = theArray[inner - h];
					inner -= h;
				}
				theArray[inner] = temp;
			} 
			h = (h - 1) / 3; 
		} 
	} 

	public static void main(String[] args) {
		int maxSize = 10;
		SimpleArray arr;
		arr = new SimpleArray(maxSize);

		for (int j = 0; j < maxSize; j++) // fill array with
		{ // random numbers
			long n = (int) (java.lang.Math.random() * 99);
			arr.insert(n);
		}
		arr.display(); // display unsorted array
		shellSort(arr);
		arr.display(); // display sorted array
	} // end main()
} // end class ShellSortApp
