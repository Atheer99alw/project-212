import java.lang.String;
import java.util.Date;

public class StockHistoryDataSetImp  implements StockHistoryDataSet {
        
        Map <String , StockHistory> Companies;
        
    
        public StockHistoryDataSetImp()
        {
            Companies = new BST<String , StockHistory>();
        }
        
        // Returns the number of companies for which data is stored.
        public int size()
        {
            return Companies.size();
        }

        // Returns true if there are no records, false otherwise.
        public boolean empty()
        {
            return Companies.empty();
        }

        // Clears all data from the storage.
        public void clear()
        { 
            Companies.clear();
        }

        // Returns the map of stock histories, where the key is the company code.
        public Map<String, StockHistory> getStockHistoryMap()
        {
            return Companies;
        }

        // Returns the list of all company codes stored in the dataset sorted in
        // increasing order.
        public DLLComp<String> getAllCompanyCodes()
        {
            return Companies.getKeys();
        }

        // Retrieves the stock history for a specific company code. This method returns
        // null if no data is found.
        public StockHistory getStockHistory(String companyCode)
        {
            if ( Companies.find(companyCode))
                return Companies.retrieve();
            return null;
        }

        // Adds the stock history of a specific company. This method returns true if the
        // operation is successful, false otherwise (company code already exists).
        public boolean addStockHistory(StockHistory stockHistory)
        {
            if ( ! Companies.find(stockHistory.getCompanyCode()))
            {
                Companies.insert(stockHistory.getCompanyCode(), stockHistory);
                return true;
            }
            return false;
        }

        // Removes the stock history of a specific company from the storage. This method
        // returns true if the operation is successful and false if the company code
        // does not exist.
        public boolean removeStockHistory(String companyCode)
        {
            if ( Companies.find(companyCode))
            {
                Companies.remove(companyCode);
                return true;
            }
            return false;
        }
}