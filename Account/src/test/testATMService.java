package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.experimental.max.MaxCore;
import org.junit.jupiter.api.Test;

import lsh.ATMService;
import lsh.Account;
import lsh.MockDbConnection;

class testATMService {
	
	
	MockDbConnection mdb = new MockDbConnection();
	ATMService atm = new ATMService(mdb);
	
	@Test
	final void testWithDraw() {
		// 有效等价类测试用例 
		// 利用边界值分析设计以下6组测试用例
		// 1.输入: cardNo = "1", amount = 0
		// 预期输出: 账户"1"余额应该为300
		mdb.getAccount("1").setBalance(300);
		atm.withDraw("1", 0);
		assertEquals(mdb.getAccount("1").getBalance(),300);
		// 2.输入: cardNo = "1", amount = 100
		// 预期输出: 账户"1"余额应该为200
		mdb.getAccount("1").setBalance(300);
		atm.withDraw("1", 100);
		assertEquals(mdb.getAccount("1").getBalance(),200);
		// 3.输入: cardNo = "1", amount = 300
		// 预期输出: 账户"1"余额应该为0
		mdb.getAccount("1").setBalance(300);
		atm.withDraw("1", 300);
		assertEquals(mdb.getAccount("1").getBalance(),0);
		
		// 4.输入: cardNo = "2", amount = 0
		// 预期输出: 账户"2"余额应该为2000
		mdb.getAccount("2").setBalance(2000);
		atm.withDraw("2", 0);
		assertEquals(mdb.getAccount("2").getBalance(),2000);
		// 5.输入: cardNo = "2", amount = 1000
		// 预期输出: 账户"2"余额应该为1000
		mdb.getAccount("2").setBalance(2000);
		atm.withDraw("2", 1000);
		assertEquals(mdb.getAccount("2").getBalance(),1000);
		// 6.输入: cardNo = "2", amount = 2000
		// 预期输出: 账户"2"余额应该为0
		mdb.getAccount("2").setBalance(2000);
		atm.withDraw("2", 2000);
		assertEquals(mdb.getAccount("2").getBalance(),0);
		
		// 无效等价类设计测试用例
		// 有效账户+无效取款金额
		// 7.输入: cardNo = "1", amount = 301
		// 预期输出: 抛出异常，无法取款，账户"1"余额应不变
		mdb.getAccount("1").setBalance(300);
		atm.withDraw("1", 301);
		assertEquals(mdb.getAccount("1").getBalance(),300);
		// 8.无效账户+有/无效取款金额
		// 输入: cardNo = "3", amount = 301
		// 预期输出: 抛出异常，无法取款
		try {
			
			fail("should have thrown a exception");
		} catch (NullPointerException e) {
			assertTrue(true);
			System.out.println(111);
		}
	}

	@Test
	final void testDeposit() {
		// 有效等价类测试用例 
		// 利用边界值分析设计以下两组测试用例
		// 1.输入: cardNo = "1", amount = 0
		// 预期输出: 账户"1"余额应该为300
		mdb.getAccount("1").setBalance(300);
		atm.deposit("1", 0);
		assertEquals(mdb.getAccount("1").getBalance(),300);
		// 2.输入: cardNo = "1", amount = 300
		// 预期输出: 账户"1"余额应该为600
		mdb.getAccount("1").setBalance(300);
		atm.deposit("1", 600);
		assertEquals(mdb.getAccount("1").getBalance(),600);
		// 3.输入: cardNo = "1", amount = MAX_VALUE
		// 预期输出: 账户"1"余额应该为 MAX_VALUE+300
		mdb.getAccount("1").setBalance(300);
		atm.deposit("1", Integer.MAX_VALUE);
		assertEquals(mdb.getAccount("1").getBalance(),300+Integer.MAX_VALUE);
		
		// 4.输入: cardNo = "2", amount = 0
		// 预期输出: 账户"2"余额应该为2000
		mdb.getAccount("2").setBalance(2000);
		atm.deposit("2", 0);
		assertEquals(mdb.getAccount("2").getBalance(),2000);
		// 5.输入: cardNo = "2", amount = 2000
		// 预期输出: 账户"2"余额应该为4000
		mdb.getAccount("2").setBalance(2000);
		atm.deposit("2", 2000);
		assertEquals(mdb.getAccount("2").getBalance(),4000);
		// 6.输入: cardNo = "2", amount = MAX_VALUE
		// 预期输出: 账户"2"余额应该为 MAX_VALUE+2000
		mdb.getAccount("2").setBalance(2000);
		atm.deposit("2", Integer.MAX_VALUE);
		assertEquals(mdb.getAccount("2").getBalance(),2000+Integer.MAX_VALUE);
	}

	@Test
	final void testTransfer() {
		// 有效测试类
		
		// 1.输入: fromCardNo= "1", amount = 0，toCardNo = “2”
		// 预期输出: 账户"1"余额应该为300, 账户"2"余额应该为2000
		mdb.getAccount("1").setBalance(300);
		mdb.getAccount("2").setBalance(2000);
		atm.transfer("1", "2", 0);
		assertEquals(mdb.getAccount("1").getBalance(),300);
		assertEquals(mdb.getAccount("2").getBalance(),2000);
		// 2.输入: fromCardNo= "1", amount = 300，toCardNo = “2”
		// 预期输出: 账户"1"余额应该为0, 账户"2"余额应该为2300
		mdb.getAccount("1").setBalance(300);
		mdb.getAccount("2").setBalance(2000);
		atm.transfer("1", "2", 300);
		assertEquals(mdb.getAccount("1").getBalance(),0);
		assertEquals(mdb.getAccount("2").getBalance(),2300);
		// 3.输入: fromCardNo= "1", amount = 100，toCardNo = “2”
		// 预期输出: 账户"1"余额应该为200, 账户"2"余额应该为2100
		mdb.getAccount("1").setBalance(300);
		mdb.getAccount("2").setBalance(2000);
		atm.transfer("1", "2", 100);
		assertEquals(mdb.getAccount("1").getBalance(),200);
		assertEquals(mdb.getAccount("2").getBalance(),2100);
		// 4.输入: fromCardNo= "2", amount = 0，toCardNo = “1”
		// 预期输出: 账户"1"余额应该为300, 账户"2"余额应该为2000
		mdb.getAccount("1").setBalance(300);
		mdb.getAccount("2").setBalance(2000);
		atm.transfer("2", "1", 0);
		assertEquals(mdb.getAccount("1").getBalance(),300);
		assertEquals(mdb.getAccount("2").getBalance(),2000);
		// 5.输入: fromCardNo= "2", amount = 2000，toCardNo = “1”
		// 预期输出: 账户"1"余额应该为2300, 账户"2"余额应该为0
		mdb.getAccount("1").setBalance(300);
		mdb.getAccount("2").setBalance(2000);
		atm.transfer("2", "1", 2000);
		assertEquals(mdb.getAccount("1").getBalance(),2300);
		assertEquals(mdb.getAccount("2").getBalance(),0);
		// 6.输入: fromCardNo= "2", amount = 1000，toCardNo = “1”
		// 预期输出: 账户"1"余额应该为1300, 账户"2"余额应该为1000
		mdb.getAccount("1").setBalance(300);
		mdb.getAccount("2").setBalance(2000);
		atm.transfer("2", "1", 1000);
		assertEquals(mdb.getAccount("1").getBalance(),1300);
		assertEquals(mdb.getAccount("2").getBalance(),1000);
		
		// 无效等价类
		// 1、4等价类：
		
		// 7.输入: fromCardNo= "2", amount = 2001，toCardNo = “1”
		// 预期输出: 抛出异常，无法转账，账户"1"和“2“余额应不变
		mdb.getAccount("1").setBalance(300);
		mdb.getAccount("2").setBalance(2000);
		atm.transfer("2", "1", 2001);
		assertEquals(mdb.getAccount("1").getBalance(),300);
		assertEquals(mdb.getAccount("2").getBalance(),2000);
		// 8.输入: fromCardNo= "1", amount = 301，toCardNo = “2”
		// 预期输出: 抛出异常，无法转账，账户"1"和“2“余额应不变
		mdb.getAccount("1").setBalance(300);
		mdb.getAccount("2").setBalance(2000);
		atm.transfer("1", "2", 301);
		assertEquals(mdb.getAccount("1").getBalance(),300);
		assertEquals(mdb.getAccount("2").getBalance(),2000);

		//2、3等价类：
		//（9）输入: fromCardNo= "3", amount = 301，toCardNo = “4”
		//预期输出: 非法账户，无法转账
		try {
			atm.transfer("3", "4", 301);
			fail("should have thrown a exception");
		} catch (Exception e) {
			assertTrue(true);
		}
		//（10）输入: fromCardNo= "4", amount = 301，toCardNo = “3”
		//预期输出: 非法账户，无法转账
		try {
			atm.transfer("4", "3", 301);
			fail("should have thrown a exception");
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	final void testInquiry() {
		// 有效测试类
		// 1.输入: cardNo = "1"
		// 预期输出: 账户"1"余额查询结果应该为300
		mdb.getAccount("1").setBalance(300);
		assertEquals(atm.inquiry("1"),300);
		// 2.输入: cardNo = "2"
		// 预期输出: 账户"2"余额查询结果应该为2000
		mdb.getAccount("2").setBalance(2000);
		assertEquals(atm.inquiry("1"),0);
		
		// 无效测试类
		// 3.输入: cardNo = "3"
		// 预期输出: 抛出异常，无法查询
		try {
			atm.inquiry("3");
			fail("should have thrown a exception");
		} catch (Exception e) {
			assertTrue(true);
		}
	}

}
