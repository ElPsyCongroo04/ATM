package lsh;

public class ATMService {
	  private IDBConnection dbconn;
	  public ATMService(IDBConnection db){
		  this.dbconn=db;
	  }
	  

	  public void withDraw(String cardNo,int amount){
		  Account a=dbconn.getAccount(cardNo);
		  // getBalance()时均用try块包住，抛出空指针异常
		  try {
			  int balance=a.getBalance()-amount;
			  // 需要判断是否可以取款
			  if(balance>=0)
			  {
				  a.setBalance(balance);
				  dbconn.updateAccount(a);
			  }	  
		  }
		  catch (NullPointerException e) {
			System.out.println("此账号为非法账号！");
		  }
		  
	  }
	  
	  public void deposit(String cardNo, int amount){
		  Account a=dbconn.getAccount(cardNo);
		  try {
			// 存款时减号改成加号
			  int balance=a.getBalance()+amount;
			  a.setBalance(balance);
			  dbconn.updateAccount(a);
		  }
		  catch (NullPointerException e) {
				System.out.println("此账号为非法账号！");
		  }
	  }
	  
	  public void transfer(String fromCardNo, String toCardNo, int amount){
		  Account a=dbconn.getAccount(fromCardNo);
		  Account b=dbconn.getAccount(toCardNo);
		  try {
			  int aBalance=a.getBalance()-amount;
			  int bBalance=b.getBalance()+amount;
			  // 需要判断是否可以转账成功（转账金额）
			  if(aBalance>=0)
			  {
				  a.setBalance(aBalance);
				  b.setBalance(bBalance);
				  dbconn.updateAccount(a);
				  dbconn.updateAccount(b); 
			  }
		  }
		  catch (NullPointerException e) {
				System.out.println("此账号为非法账号！");
		  }
		  
	  }
	 public int inquiry(String cardNo){
		  Account a=dbconn.getAccount(cardNo);
		  try {
			  return a.getBalance();
		  }
		  catch (NullPointerException e) {
				System.out.println("此账号为非法账号！");
				return 0;
		  }
	 }
	}
