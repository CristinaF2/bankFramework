package ObjectData;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class CustomerObject extends CommonObject{
    private String firstName;
    private String lastName;
    private String postCode;

    private List<AccountObject> accounts;

    public CustomerObject(HashMap<String,String> testData) {
        populateData(testData);
        prepareAccounts(testData);
        prepareAccountTransactions(testData);

    }

    public void populateData(HashMap<String, String> testData) {
        for(String key: testData.keySet()){
            switch (key){
                case "firstName":
                    setFirstName(testData.get(key)+System.currentTimeMillis());
                    break;
                case "lastName":
                    setLastName(testData.get(key));
                    break;
                case "postCode":
                    setPostCode(testData.get(key));
                    break;

            }
        }

    }

    private void prepareAccounts(HashMap<String, String> testData){
        List<String> currencies=getValueList(testData.get("currencies"));
        accounts=new ArrayList<>();
        for (int index=0; index< currencies.size();index++){
            accounts.add(new AccountObject(currencies.get(index),""));
        }
    }

    private void prepareAccountTransactions(HashMap<String,String> testData){
        //verificam daca fisierul de proprietati contine o cheie pentru tranzactii
        if (testData.containsKey("transactionsDollar")){
           populateTransactions("transactionsDollar",testData);
        }
        if (testData.containsKey("transactionsPound")){
            populateTransactions("transactionsPound",testData);
        }
        if (testData.containsKey("transactionsRupee")){
           populateTransactions("transactionsRupee",testData);
        }
    }

    private void populateTransactions(String key,HashMap<String,String> testData ){
        String currency=key.split("transactions")[1];
        int position=0;
        for (int index=0; index< accounts.size(); index++){
            if(accounts.get(index).getCurrency().equals(currency)){
                position=index;
                break;
            }
        }
        String transactions=testData.get(key);
        accounts.get(position).prepareAccountTransactions(transactions);

    }


}
