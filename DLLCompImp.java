
public class DLLCompImp <T extends Comparable<T>>   implements DLLComp <T>
{
    public DLL<T> dllList;
    boolean increasing = true;
    
    public DLLCompImp()
    {
        dllList = new DLLImp<T> ();
    }
    
    // [ Requires Chapter on Sorting ] Sorts the list . The parameter " increasing "
    // indicates whether the sort is done in increasing or decreasing order .
    public void sort (boolean increasing )
    {
        this.increasing = increasing;
        if (dllList.empty())
            return;
        
        T[] a;
        a = (T[] )new Comparable [dllList.size()];
        this.findFirst();
        int i = 0;
        while (! dllList.empty())
        {
            a[i++]  = this.retrieve();
            this.remove();
        }
        
        mergesort (a, 0, a.length-1);

        if (increasing )
            for (int index = 0 ; index < a.length ; index ++ )
                this.insert(a[index]);
        else
            for (int index = a.length-1 ; index >= 0 ; index -- )
                this.insert(a[index]);
        
        
    }
    
//====================================================================================
 private void mergesort (T[] a,  int l , int r ) 
    {
        if ( l >= r )
            return;
        int m = ( l + r ) / 2;
        mergesort (a, l , m ) ;          // Sort first half
        mergesort (a, m + 1 , r ) ;    // Sort second half
        merge (a, l , m , r ) ;            // Merge
    }

    private void merge ( T[] sortedArray, int l , int m , int r ) 
    {
        T [] B = (T[]) new Comparable [ r - l + 1];
        int i = l , j = m + 1 , k = 0;
    
        while ( i <= m && j <= r )
        {
            if ( sortedArray[i].compareTo(sortedArray[j]) <= 0)
                B [ k ++] = sortedArray[ i ++];
            else
                B [ k ++] = sortedArray[ j ++];
        }
        
        if ( i > m )
            while ( j <= r )
                B [ k ++] = sortedArray[ j ++];
        else
            while ( i <= m )
                B [ k ++] = sortedArray[ i ++];
        
        for ( k = 0; k < B . length ; k ++)
            sortedArray[ k + l ] = B [ k ];
    }

    // Returns the maximum element . The list must not be empty .
    public T getMax () 
    {
        if ( empty())
            return null;
        
        this.findFirst();
        if (increasing )
        {
            while (!last())
                findNext();
        }
        return this.retrieve();
    }
    
    // Returns the maximum element . The list must not be empty .
    public T getMin () 
    {
        if ( empty())
            return null;
        
        findFirst();
        
        if (!increasing)
            
        {
            while ( !last() )
                this.findNext();
        }
        return this.retrieve();
    }

    @Override
    public int size() {
        return dllList.size();
    }

    @Override
    public boolean empty() {
        return dllList.empty();
                
    }

    @Override
    public boolean last() {
        return dllList.last();
    }

    @Override
    public boolean first() {
         return dllList.first();
    }

    @Override
    public void findFirst() {
        dllList.findFirst();
    }

    @Override
    public void findNext() {
        dllList.findNext();
    }

    @Override
    public void findPrevious() {
        dllList.findPrevious();
    }

    @Override
    public T retrieve() {
        return dllList.retrieve();
    }

    @Override
    public void update(T val) {
        dllList.update(val);
    }

    @Override
    public void insert(T val) {
        dllList.insert(val);
    }

    @Override
    public void remove() {
        dllList.remove();
    }


}