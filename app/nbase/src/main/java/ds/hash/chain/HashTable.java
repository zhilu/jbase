package ds.hash.chain;

class HashTable {
	private SortedList[] hashArray;
	private int arraySize;

	// -------------------------------------------------------------
	public HashTable(int size) {
		arraySize = size;
		hashArray = new SortedList[arraySize];
		for (int j = 0; j < arraySize; j++)
			hashArray[j] = new SortedList();
	}

	// -------------------------------------------------------------
	public void displayTable() {
		for (int j = 0; j < arraySize; j++) {
			System.out.print(j + ". ");
			hashArray[j].displayList();
		}
	}

	// -------------------------------------------------------------
	public int hashFunc(int key) 
	{
		return key % arraySize;
	}

	// -------------------------------------------------------------
	public void insert(Link theLink) // insert a link
	{
		int key = theLink.getKey();
		int hashVal = hashFunc(key); // hash the key
		hashArray[hashVal].insert(theLink); // insert at hashVal
	} 

	public void delete(int key) // delete a link
	{
		int hashVal = hashFunc(key); // hash the key
		hashArray[hashVal].delete(key); // delete link
	} 

	public Link find(int key) // find link
	{
		int hashVal = hashFunc(key); // hash the key
		Link theLink = hashArray[hashVal].find(key); // get link
		return theLink; // return link
	}
}