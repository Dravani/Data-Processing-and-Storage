# Data-Processing-and-Storage


Transactional In-Memory Database
This is a Java implementation of an in-memory key-value database with transaction support. It provides functions to begin, commit, and rollback transactions, as well as to get and put key-value pairs.

# Setup
1. Java Development Kit (JDK): Ensure you have JDK installed on your system. You can download and install JDK from the official Oracle website.;
2. Clone the Repository: Clone this repository to your local machine using the following command:
   
Copy code
```
git clone https://github.com/your-username/Data-Processing-and-Storage.git
```
Navigate to the Project Directory: 
Change your current directory to the cloned repository:

Copy code
```
cd Data-Processing-and-Storage
```
# Running the Code
1. Compile the Java Files: Compile the Java files using the Java compiler.
2. Run the following command from the project root directory:

Copy code
```
javac TransactionalInMemoryDB.java
```
Run the Main Class: Run the main class TransactionalInMemoryDB:

Copy code
```
java TransactionalInMemoryDB
```

# Usage
Once the program is running, you can interact with the in-memory database using the provided functions:

get(key): Retrieve the value associated with the specified key.

put(key, value): Add or update the value associated with the specified key.

begin_transaction(): Start a new transaction.

commit(): Apply changes made within the transaction to the main database state.

rollback(): Revert all changes made within the transaction.

Example usage:
```
TransactionalInMemoryDB inMemoryDB = new TransactionalInMemoryDB();

// Start a new transaction
inMemoryDB.begin_transaction();

// Set the value of key "A" to 5 within the transaction
inMemoryDB.put("A", 5);

// Commit the transaction
inMemoryDB.commit();

// Retrieve the value of key "A"
System.out.println(inMemoryDB.get("A")); // Output: 5
```

There is already a main function with usage in there. If you want to test the commands that cause errors, uncomment them.


# Modifications

I think the instructions for the project need to be more clear, especially for people taking this class without as much coding experience. It took a while for me to figure out how to make a method with a int return type, return a null, when normally it should return -1. It would have been nice to have some clearer instructions on that. For grading, I think providing a suite of unit tests to evaluate the correctness of the implementation would be very useful. Test cases should cover various scenarios, including normal transaction operations, error handling, concurrency scenarios, and boundary cases.
