package lsh;

import java.util.HashMap;

public class MockDbConnection implements IDBConnection{
	private HashMap<String,Account> mdb;
	
	public MockDbConnection(){
		mdb=new HashMap<String,Account>();
		Account a=new Account("1","11111",300);
		Account b=new Account("2","22222",2000);
		mdb.put(a.getCardNo(),a);
		mdb.put(b.getCardNo(),b);
	}
	
	public Account getAccount(String cardNo){
		//需填充编写代码
		return mdb.get(cardNo);
	}
	public void updateAccount(Account a){
		//需填充编写代码
		mdb.replace(a.getCardNo(),a);
	}
	public void addAccount(Account a){
		//需填充编写代码
		mdb.put(a.getCardNo(),a);
	}


} 

