package ObjectData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AccountObject {

    private String currency;

    private String accountId;

    private List<TransactionObject> transactions;

    public AccountObject(String currency, String accountId) {
        this.currency = currency;
        this.accountId = accountId;
    }

    public void prepareAccountTransactions(String value){
        transactions=new ArrayList<>();
        String[] actions=value.split(",");
        for (int index=0; index<actions.length; index++){
            String[] transaction=actions[index].split("-");
            String transactionType=transaction[0];
            int transactionAmount=Integer.parseInt(transaction[1]);
            transactions.add(new TransactionObject(transactionType,transactionAmount));
        }

    }

}
