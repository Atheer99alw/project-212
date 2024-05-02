import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StockDataLoaderImp implements StockDataLoader {
    
        Map<String, StockHistoryDataSetImp> dataSet;
        
        public StockDataLoaderImp()
        {
            dataSet = new BST<String, StockHistoryDataSetImp>();
        }
        
        // Loads and adds stock history from the specified CSV file. The code of the
        // company is the basename of the file. This method returns null if the
        // operation is not successful. Errors include, non-existing file, incorrect
        // format, repeated dates, dates not sorted in increasing order, etc.
        public StockHistory loadStockDataFile(String fileName)
        {
            StockHistory SH = (StockHistory) new StockHistoryImp();
            Date oldDate = new Date ("1/1/1800");

            try{
                File file = new File(fileName);
                SH.SetCompanyCode(file.getName().substring(0,  file.getName().indexOf(".csv")));
                Scanner reader = new Scanner (file);
                reader.useDelimiter(",");
               
                String line = reader.nextLine();
                while(reader.hasNext()) 
                {
                    line = reader.nextLine();
                    String [] values = line.split(",");
                    String [] d = values[0].split("-");
                    
                    Date date = new Date (d[0]+"/"+d[1]+"/"+d[2]);
                    Double open = Double.parseDouble(values[1]);
                    Double high = Double.parseDouble(values[2]);
                    Double low = Double.parseDouble(values[3]);
                    Double close = Double.parseDouble(values[4]);
                    long volume = Long.parseLong(values[5]);
                    
                    StockData SD = new StockData(open, high, low, close, volume);
                    if (! SH.addStockData(date , SD))
                        throw new Exception();
                    
                    if (date.compareTo(oldDate) < 0)
                        throw new Exception();
                    
                    date = oldDate;
                }
                reader.close();
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                return null;
            }
         return SH;
        }

      
        // Loads and returns stock history data from all CSV files in the specified
        // directory. This method returns null if the operation is not successful (see
        // possible errors in the method loadStockDataFile).
        
public StockHistoryDataSet loadStockDataDir(String directoryPath) {
    StockHistoryDataSet dataSet = new StockHistoryDataSetImp();

    try {
        final File folder = new File(directoryPath);

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.getName().endsWith(".csv")) {
                StockHistory stockHistory = loadStockDataFile(fileEntry.getPath());

                if (stockHistory == null || !dataSet.addStockHistory(stockHistory)) {
                    throw new Exception("Failed to load or add stock history data.");
                }
            }
        }
    } catch (Exception e) {
        return null; // Operation unsuccessful, return null.
    }

    return dataSet;
}
}