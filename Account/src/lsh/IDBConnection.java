package lsh;

public interface IDBConnection {
	public Account getAccount(String id);
	public void updateAccount(Account account);
	public void addAccount(Account account);
}