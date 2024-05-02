import java.util.Date;

public class NumericTimeSeriesImp implements NumericTimeSeries  {
    
    TimeSeries<Double> TSDouble;
    
    public NumericTimeSeriesImp()
    {
        TSDouble = new TimeSeriesImp<Double> ();
    }
    
    
    
    // Calculates and returns the moving average of the data points over a specified
    // period. The value period must be strictly positive. If the time series is
    // empty, the output must be empty.

    public NumericTimeSeries calculateMovingAverage(int period) {
        NumericTimeSeries newData = new NumericTimeSeriesImp();

        if (period <= 0 || TSDouble.empty()) {
            // If the period is not positive or the time series is empty, return empty data
            return newData;
        }

        DLL<DataPoint<Double>> dataPoints = TSDouble.getAllDataPoints();

        // Iterate through the data points to calculate moving averages
        for (int i = 0; i <= dataPoints.size() - period; i++) {
            dataPoints.findFirst();
            // Move to the ith data point
            for (int j = 0; j < i; j++) {
                dataPoints.findNext();
            }

            double total = 0;
            Date date = dataPoints.retrieve().date;

            // Calculate the sum of values for the current window
            for (int j = 0; j < period; j++) {
                total += dataPoints.retrieve().value;
                dataPoints.findNext();
            }

            // Calculate and add the moving average to the new data series
            newData.addDataPoint(new DataPoint<Double>(date, total / period));
        }

        return newData;
    }

    // Returns the maximum value in the time series. Time series must not be empty.
    public DataPoint<Double> getMax()
    {
        DataPoint<Double> Maxdata = null;
        if (!TSDouble.empty())
        {
            DLL<DataPoint<Double>> d = TSDouble.getAllDataPoints();
            d.findFirst();
            Maxdata = d.retrieve();
            for (int i = 0 ; i < d.size() ; i++)
            {
                if (Maxdata.value.compareTo(d.retrieve().value) < 0)
                    Maxdata = d.retrieve();
                d.findNext();
            }
        }
        
        return Maxdata;
    }
    
    // Returns the minimum value in the time series. Time series must not be empty.
    public DataPoint<Double> getMin()
    {
        DataPoint<Double> Mindata = null;
        if (!TSDouble.empty())
        {
            DLL<DataPoint<Double>> d = TSDouble.getAllDataPoints();
            d.findFirst();
            Mindata = d.retrieve();
            for (int i = 0 ; i < d.size() ; i++)
            {
                if (Mindata.value.compareTo(d.retrieve().value) > 0)
                    Mindata = d.retrieve();
                d.findNext();
            }
        }
        
        return Mindata;
    
    }

    @Override
    public int size() {
        return TSDouble.size();
    }

    @Override
    public boolean empty() {
        return TSDouble.empty();
    }

    @Override
    public DLL<Date> getAllDates() {
        return TSDouble.getAllDates();
    }

    @Override
    public Date getMinDate() {
        return TSDouble.getMinDate();
    }

    @Override
    public Date getMaxDate() {
        return TSDouble.getMaxDate();
    }

    @Override
    public boolean addDataPoint(DataPoint<Double> dataPoint) {
        return TSDouble.addDataPoint(dataPoint);
    }

    @Override
    public boolean updateDataPoint(DataPoint<Double> dataPoint) {
        return TSDouble.updateDataPoint(dataPoint);
    }

    @Override
    public boolean removeDataPoint(Date date) {
        return TSDouble.removeDataPoint(date);
    }

    @Override
    public DLL<DataPoint<Double>> getAllDataPoints() {
        return TSDouble.getAllDataPoints();
    }

    @Override
    public DLL<DataPoint<Double>> getDataPointsInRange(Date startDate, Date endDate) {
        return TSDouble.getDataPointsInRange(startDate, endDate);
    }

    @Override
    public Double getDataPoint(Date date) {
        return TSDouble.getDataPoint(date);
    }

}