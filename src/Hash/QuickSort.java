package Hash;

/**
 *
 * @author Adem KOCAMAZ
 */
public class QuickSort {
     
    private DataItem array[];
    private int length;
 
    public void sort(DataItem[] inputArr) {
         
        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.array = inputArr;
        length = inputArr.length;
        quickSort(0, length - 1);
    }
 
    private void quickSort(int lowerIndex, int higherIndex) {
         
        int i = lowerIndex;
        int j = higherIndex;
        
        DataItem pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
        
//        while (i <= j) {
//            
//            while (array[i].getFrequency() < pivot.getFrequency()) {
//                i++;
//            }
//            while (array[j].getFrequency() > pivot.getFrequency()) {
//                j--;
//            }
//            if (i <= j) {
//                exchangeNumbers(i, j);
//                
//                i++;
//                j--;
//            }
//        }
        while (i <= j) {
            
            while (array[i].getFrequency() > pivot.getFrequency()) {
                i++;
            }
            while (array[j].getFrequency() < pivot.getFrequency()) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                
                i++;
                j--;
            }
        }
        
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }
 
    private void exchangeNumbers(int i, int j) {
        DataItem temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
     
//    public static void main(String a[]){
//         
//        QuickSort sorter = new QuickSort();
//        DataItem[] input = {new DataItem("a", 50),
//                            new DataItem("b",10),
//                            new DataItem("c",100),
//                            new DataItem("d",3),
//                            new DataItem("e",10),
//                            new DataItem("f",90),
//                            new DataItem("g",10),
//                            new DataItem("h",6),
//                            new DataItem("i",1),
//                            new DataItem("j",80),
//                            
//        };
//        sorter.sort(input);
//        for(DataItem i:input){
//            System.out.print(i);
//            System.out.print(" ");
//        }
//    }
}
