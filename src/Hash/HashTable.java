package Hash;

import java.util.ArrayList;

/**
 *
 * Adem KOCAMAZ
 */
public class HashTable {

    private DataItem[] hashArray;    // array holds hash table
    private int arraySize;
    private DataItem nonItem;        // for deleted items
// -------------------------------------------------------------

    public HashTable(int size) // constructor
    {
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem("", 0);   // deleted item key is -1
    }
// -------------------------------------------------------------

    public Object[][] getTop10() {
        
        
//        for (int i = 0; i < arraySize; i++) {
//            temp[i]=hashArray[i];
//        }
//        System.arraycopy(hashArray, 0, temp, 0, temp.length);
//        if(temp!=null){
//            try {
//                QuickSort qs=new QuickSort();
//                qs.sort(temp);
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//            
//        }
        
        
        ArrayList hashList = new ArrayList();
        for (int i = 0; i < arraySize; i++) {
            if (hashArray[i] != null) {
                hashList.add(hashArray[i]);
            }
        }        
        QuickSortList qs=new QuickSortList();
        qs.sort(hashList);
        
        Object[][] top10 = new Object[hashList.size()][2];

        for (int i = 0; i < 10; i++) {
            top10[i][0] = ((DataItem) hashList.get(i)).getWord();
            top10[i][1] = ((DataItem) hashList.get(i)).getFrequency();
        }
        
        
        return top10;
    }
// -------------------------------------------------------------

    public Object[][] getItems() {
        ArrayList hashList = new ArrayList();
        for (int i = 0; i < arraySize; i++) {
            if (hashArray[i] != null) {
                hashList.add(hashArray[i]);
            }
        }
        Object[][] items = new Object[hashList.size()][2];
//        Object[][] items=new Object[arraySize][2];        
//        for (int i = 0; i < arraySize; i++) {
//            if(hashArray[i]!=null){
//                items[i][0]=hashArray[i].getWord();
//                items[i][1]=hashArray[i].getFrequency();
//            }
//        }
        for (int i = 0; i < hashList.size(); i++) {
            items[i][0] = ((DataItem) hashList.get(i)).getWord();
            items[i][1] = ((DataItem) hashList.get(i)).getFrequency();
        }
        return items;
    }
// -------------------------------------------------------------

    public void displayTable() {
        System.out.print("Table: ");
        for (int j = 0; j < arraySize; j++) {
            if (hashArray[j] != null) {
                System.out.print(hashArray[j].getWord() + "[" + hashArray[j].getFrequency() + "] ");
            } else {
                System.out.print("** ");
            }
        }
        System.out.println("");
    }
// -------------------------------------------------------------

    private int wordFunc(String word) {
        int sum = 0;
        for (int i = 0; i < word.length(); i++) {
            sum += (int) (word.charAt(i)) + (i);
        }
        return sum;
    }
// -------------------------------------------------------------

    public int hashFunc(String word) {
        return wordFunc(word) % arraySize;       // hash function
    }
// -------------------------------------------------------------
    //public void insert(String word,int frequency){

    public void insert(String word) {
        DataItem w = find(word);
        if (w != null) {
            w.setFrequency(w.getFrequency() + 1);
        } else {
            insert(new DataItem(word, 1));
        }

    }
// -------------------------------------------------------------
//  public void insert(DataItem item) // insert a DataItem

    private void insert(DataItem item) // insert a DataItem
    // (assumes table not full)
    {
        String word = item.getWord();      // extract key
        int hashVal = hashFunc(word);  // hash the key
        int step = 1;
        // until empty cell or -1,
        while (hashArray[hashVal] != null
                && !hashArray[hashVal].getWord().equals("")) {
            //++hashVal;                 // go to next cell
            hashVal = hashFunc(word) + (step * step);
            hashVal %= arraySize;      // wraparound if necessary
            step++;
        }
        hashArray[hashVal] = item;    // insert item
    }  // end insert()
// -------------------------------------------------------------

    public DataItem delete(String word) // delete a DataItem
    {
        int hashVal = hashFunc(word);  // hash the key

        while (hashArray[hashVal] != null) // until empty cell,
        {                               // found the key?
            if (hashArray[hashVal].getWord() == word) {
                DataItem temp = hashArray[hashVal]; // save item
                hashArray[hashVal] = nonItem;       // delete item
                return temp;                        // return item
            }
            ++hashVal;                 // go to next cell
            hashVal %= arraySize;      // wraparound if necessary
        }
        return null;                  // can't find item
    }  // end delete()
// -------------------------------------------------------------

    public DataItem find(String word) // find item with key
    {
        int hashVal = hashFunc(word);  // hash the key

        while (hashArray[hashVal] != null) // until empty cell,
        {                               // found the key?
            if (hashArray[hashVal].getWord().equals(word)) {
                return hashArray[hashVal];   // yes, return item
            }
            ++hashVal;                 // go to next cell
            hashVal %= arraySize;      // wraparound if necessary
        }
        return null;                  // can't find item
    }
// -------------------------------------------------------------
}
