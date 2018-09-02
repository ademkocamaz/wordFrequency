package Hash;

/**
 *
 * @author Adem KOCAMAZ
 */
public class DataItem {
//--------------------------------------------------------------

    private String word;
    private int frequency;               // data item (key)
//--------------------------------------------------------------

    public DataItem(String word, int frequency) // constructor
    {
        this.word = word;
        this.frequency = frequency;
    }
//--------------------------------------------------------------

    public String getWord() {
        return word;
    }
//--------------------------------------------------------------

    public int getFrequency() {
        return frequency;
    }    
//--------------------------------------------------------------
    public void setWord(String word){
        this.word=word;
    }
//--------------------------------------------------------------
    public void setFrequency(int frequency){
        this.frequency=frequency;
    }
//--------------------------------------------------------------
    
//--------------------------------------------------------------

    @Override
    public String toString() {
        return "["+word+","+frequency+"]";
    }
}
