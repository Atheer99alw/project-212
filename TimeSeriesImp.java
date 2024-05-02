
import java.util.Date;

public class TimeSeriesImp<T> implements  TimeSeries<T>
{
    DLLComp<CompPair<DataPoint<T>, Date>> dates;
 
    
    public TimeSeriesImp()
    {
        dates = new DLLCompImp<CompPair<DataPoint<T>, Date>>();
    } 
    // Returns the number of elements in the time series.
    public int size()
    {
        return dates.size();
    }

    // Returns true if the time series is empty, false otherwise.
    public boolean empty()
    {
        return dates.empty();
    }

    // Retrieves the data corresponding to a specific date. This method returns the
    // data point for the specified date, or null if no such data point exists.
    
    public T getDataPoint(Date date)
    {
        T object = null;
        if (dates.empty())
            return null;
        
        dates.findFirst();
        for ( int i = 0 ; i < dates.size() ; i++)
        {
            if ( dates.retrieve().second.compareTo(date) == 0)
                return  dates.retrieve().first.value;
            
            dates.findNext();
        }    
        return null;
    }
   
    
    // Return all dates in increasing order.
    
    public DLL<Date> getAllDates()
    {
       dates.sort(true);
       DLL<Date> dll = new DLLImp<Date>();
       
       dates.findFirst();
       for ( int i = 0 ; i < dates.size() ; i++)
       {
           dll.insert(dates.retrieve().second);
           dates.findNext();
       }
       return dll;
    }

    // Returns min date. Time series must not be empty.
    public Date getMinDate()
    {
        dates.sort(true);
        return dates.getMin().second;
    }

    // Returns max date. Time series must not be empty.
    public Date getMaxDate()
    {
        dates.sort(true);
        return dates.getMax().second;
    }

    // Adds a new data point to the time series. If successful, the method returns
    // true. If date already exists, the method returns false.
    public boolean addDataPoint(DataPoint<T> dataPoint)
    {
        CompPair<DataPoint<T>, Date> val = new CompPair<DataPoint<T>, Date>(dataPoint, dataPoint.date);
            
        if (dates.empty())
        {
            dates.insert(val);
            return true;
        }
        
        dates.findFirst();
        for ( int i = 0 ; i < dates.size() ; i++)
        {
            if ( dates.retrieve().compareTo(val) == 0)
                return false;
            
            dates.findNext();
        }    
        dates.insert(val);
        return true;
    }

// Updates a data point. This method returns true if the date exists and the
    // update was successful, false otherwise.
   public boolean updateDataPoint(DataPoint<T> dataPoint) {
    if (dates.isEmpty())
        return false;

      for ( int i = 0 ; i < dates.size() ; i++) {
        if (date.compareTo(dataPoint.date) == 0) {
            CompPair<DataPoint<T>, Date> val = new CompPair<>(dataPoint, dataPoint.date);
            dates.update(val);
            return true;
        }
    }
    return false;
}


    // Removes a data point with given date from the time series. This method
    // returns true if the data point was successfully removed, false otherwise.
    public boolean removeDataPoint(Date date)
    {
        if (dates.empty())
            return false;
        
        dates.findFirst();
        for ( int i = 0 ; i < dates.size() ; i++)
        {
            if ( dates.retrieve().second.compareTo(date) == 0)
            {
                dates.remove();
                return true;
            }
            dates.findNext();
        }
        return false;
    }

    // Retrieves all data points in the time series as a DLL that is sorted in
    // increasing order of date.
public DLL<DataPoint<T>> getAllDataPointsSortedByDate() {
    // Create a new empty DLL to store sorted data points.
    DLL<DataPoint<T>> sortedDataPoints = new DLLImp<DataPoint<T>>();

    // Check if there are any dates in the time series.
    if (!dates.empty()) {
        // Sort the dates in ascending order.
        dates.sort(true);

        // Start at the first date.
        dates.findFirst();
        
        // Iterate over all dates in the sorted list.
        for (int i = 0; i < dates.size(); i++) {
            // Insert the corresponding data point into the sorted DLL.
            sortedDataPoints.insert(dates.retrieve().first);
            dates.findNext();
        }
    }

    // Return the sorted DLL of data points.
    return sortedDataPoints;
}


    // Gets data points from startDate to endDate inclusive. If startDate is null,
    // fetches from the earliest date. If endDate is null, fetches to the latest
    // date. Returns sorted list in increasing date order.
   // Retrieves data points from startDate to endDate, inclusive. If startDate is null,
public DLL<DataPoint<T>> getDataPointsInRange(Date startDate, Date endDate) {
    // Get all data points from the time series.
    DLL<DataPoint<T>> allDataPoints = getAllDataPoints();
    
    // Check if there are any data points.
    if (!allDataPoints.empty()) {
        // Start at the first data point.
        allDataPoints.findFirst();
        
        // Remove all data points before the start date, if provided.
        if (startDate != null) {
            while (!allDataPoints.empty() && allDataPoints.retrieve().date.compareTo(startDate) < 0) {
                allDataPoints.remove();
            }
        }
        
        // Remove all data points after the end date, if provided.
        if (endDate != null) {
            // Move to the first data point after the end date.
            while (!allDataPoints.empty() && !allDataPoints.last() && allDataPoints.retrieve().date.compareTo(endDate) <= 0) {
                allDataPoints.findNext();
            }
            
            // Remove all data points after the end date.
            while (!allDataPoints.empty() && allDataPoints.retrieve().date.compareTo(endDate) > 0) {
                allDataPoints.remove();
            }
        }
    }
    
    return allDataPoints;
}

    public DLL<DataPoint<T>> getAllDataPoints() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static class date {

        private static int compareTo(Date date) {
            throw new UnsupportedOperationException("Not supported yet."); 
        }

        public date() {
        }
    }
    
}