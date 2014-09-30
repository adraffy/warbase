package warbase;

import java.util.Arrays;

public class RandomSuffixGroup {

    public final int id;
    public final RandomSuffix[] suffixes; // this is always sorted by id
    
    public RandomSuffixGroup(int id, RandomSuffix[] suffixes) {
        this.id = id;
        this.suffixes = suffixes;
    }
    
    public int find(int id) {
        return WarBaseUtils.binarySearch(suffixes, RandomSuffix.ID, id);
    }
    
    public boolean contains(int id) {
        return find(id) >= 0;
    }
    
    @Override
    public String toString() {
        return String.format("%s<%d>%s", getClass().getSimpleName(), id, Arrays.toString(suffixes));
    }
        
}
