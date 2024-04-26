import java.util.HashMap;
import java.util.Map;

interface InMemoryDB {
    Integer get(String key);

    void put(String key, int val);

    void begin_transaction();

    void commit();

    void rollback();
}

public class TransactionalInMemoryDB implements InMemoryDB {
    private Map<String, Integer> data;
    private Map<String, Integer> transactionData;
    private boolean inTransaction;
    private boolean commited;

    public TransactionalInMemoryDB() {
        data = new HashMap<>();
        transactionData = new HashMap<>();
        inTransaction = false;
        commited = false;
    }

    @Override
    public Integer get(String key) {
        if (commited && inTransaction && transactionData.containsKey(key)) {
            return transactionData.get(key);
        } else if (data.containsKey(key)) {
            return data.get(key);
        } else {
            return null;
        }
    }

    @Override
    public void put(String key, int val) {
        if (!inTransaction) {
            throw new IllegalStateException("No transaction in progress");
        }
        transactionData.put(key, val);
    }

    @Override
    public void begin_transaction() {
        if (inTransaction) {
            throw new IllegalStateException("Transaction already in progress");
        }
        inTransaction = true;
        commited = false;
    }

    @Override
    public void commit() {
        commited = true;
        if (!inTransaction) {
            throw new IllegalStateException("No transaction in progress");
        }
        data.putAll(transactionData);
        transactionData.clear();
        inTransaction = false;
    }

    @Override
    public void rollback() {
        if (!inTransaction) {
            throw new IllegalStateException("No transaction in progress");
        }
        transactionData.clear();
        inTransaction = false;
    }

    public static void main(String[] args) {
        TransactionalInMemoryDB inMemoryDB = new TransactionalInMemoryDB();

        System.out.println(inMemoryDB.get("A")); // null

        //inMemoryDB.put("A", 5); // Error: No transaction in progress

        inMemoryDB.begin_transaction();

        inMemoryDB.put("A", 5);
        System.out.println(inMemoryDB.get("A")); // null

        inMemoryDB.put("A", 6);

        inMemoryDB.commit();
        System.out.println(inMemoryDB.get("A")); // 6

        // Error: No transaction in progress
        // inMemoryDB.commit();

        // Error: No ongoing transaction
        // inMemoryDB.rollback();

        System.out.println(inMemoryDB.get("B")); // null

        inMemoryDB.begin_transaction();

        inMemoryDB.put("B", 10);

        inMemoryDB.rollback();
        System.out.println(inMemoryDB.get("B")); // null
    }
}
