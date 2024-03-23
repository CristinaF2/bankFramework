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

}
