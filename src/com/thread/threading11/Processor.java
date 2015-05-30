package com.thread.threading11;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Processor {

	private Account account1 = new Account();
	private Account account2 = new Account();
	
	Lock lock1 = new ReentrantLock();
	Lock lock2 = new ReentrantLock();
	
	public void firstThread() {
		Random random = new Random();
		for(int i=0; i<1000; i++)  {
			acquireLock(lock1, lock2);
			try  {
				Account.transfer(account1, account2, random.nextInt(100));
			} finally  {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}

	private void acquireLock(Lock l1, Lock l2) {
		
		boolean gotFirstLock = false;
		boolean gotSecondLock = false;

		while(true)  {
			try  {
				gotFirstLock = l1.tryLock();
				gotSecondLock = l2.tryLock();
			} finally  {
				if(gotFirstLock && gotSecondLock)  {
					return;
				}
				
				if(gotFirstLock)  {
					l1.unlock();
				}
				
				if(gotSecondLock)  {
					l2.unlock();
				}
			}
		}
		
		
	}

	public void secondThread() {
		Random random = new Random();
		for(int i=0; i<1000; i++)  {
			acquireLock(lock2, lock1);
			try  {
				Account.transfer(account2, account1, random.nextInt(100));
			} finally  {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}

	public void finished() {
		System.out.println("Account 1 :"+account1.getBalance());
		System.out.println("Account 2 :"+account2.getBalance());
		System.out.println("Total: "+ (account1.getBalance()+account2.getBalance()));
	}

}
