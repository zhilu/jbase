package ds.hash.hashtable;

import ds.hash.DataItem;

/**
 * HashTable �ļ�ʵ��
 * 
 * @author shi
 *
 */
class HashTable {
	private DataItem[] hashArray; // store
	private int arraySize;
	private DataItem nonItem; // for deleted items

	
	public HashTable(int size) {
		arraySize = size;
		hashArray = new DataItem[arraySize];
		nonItem = new DataItem(-1); 
	}

	public void displayTable() {
		System.out.print("Table: ");
		for (int j = 0; j < arraySize; j++) {
			if (hashArray[j] != null)
				System.out.print(hashArray[j].getKey() + " ");
			else
				System.out.print("** ");
		}
		System.out.println("");
	}

	/**
	 * hash function
	 * 
	 * @param key
	 * @return
	 */
	public int hashFunc(int key) {
		return key % arraySize; 
	}

	/**
	 * ����
	 * 
	 * @param item
	 */
	public void insert(DataItem item) // insert a DataItem
	{
		int key = item.getKey(); 
		int hashVal = hashFunc(key); // hash the key
										// until empty cell or -1,
		while (hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
			++hashVal; // go to next cell
			hashVal %= arraySize; // wraparound if necessary
		}
		hashArray[hashVal] = item; // insert item
	} // end insert()
	// -------------------------------------------------------------

	public DataItem delete(int key) // delete a DataItem
	{
		int hashVal = hashFunc(key); // hash the key

		while (hashArray[hashVal] != null) // until empty cell,
		{ // found the key?
			if (hashArray[hashVal].getKey() == key) {
				DataItem temp = hashArray[hashVal]; // save item
				hashArray[hashVal] = nonItem; // delete item
				return temp; // return item
			}
			++hashVal; // go to next cell
			hashVal %= arraySize; // wraparound if necessary
		}
		return null; // can't find item
	} // end delete()
	// -------------------------------------------------------------

	public DataItem find(int key) // find item with key
	{
		int hashVal = hashFunc(key); // hash the key

		while (hashArray[hashVal] != null) // until empty cell,
		{ // found the key?
			if (hashArray[hashVal].getKey() == key)
				return hashArray[hashVal]; // yes, return item
			++hashVal; // go to next cell
			hashVal %= arraySize; // wraparound if necessary
		}
		return null; // can't find item
	}
	// -------------------------------------------------------------
} // end class HashTable
