package lsh;

public class Account {
	String cardNo;
	String password;
	int balance;
	
   public Account(String cardNo,String password,int balance){
		this.cardNo=cardNo;
		this.password=password;
		this.balance=balance;
		
	}

	public void setCardNo(String cardNo){
		this.cardNo=cardNo;
	}
	
	public void setPassword(String ps){
		this.password=ps;

	}
	public void setBalance(int amount){
		this.balance=amount;
	}
	public String getCardNo(){
		return this.cardNo;
	}
	public String getPassword(){
		return this.password;
	}
	public int getBalance(){
		return this.balance;
	}
}

