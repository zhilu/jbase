package corejava.thread;

/**
 * This program shows data corruption when multiple threads access a data structure.
 * 多线程未同步操作  数据损坏
 * @version 1.30 2004-08-01
 * @author Cay Horstmann
 */
public class UnsynchBankTest
{
   public static final int NACCOUNTS = 100;
   public static final double INITIAL_BALANCE = 1000;

   public static void main(String[] args)
   {
      BankUnsyn b = new BankUnsyn(NACCOUNTS, INITIAL_BALANCE);
      int i;
      for (i = 0; i < NACCOUNTS; i++)
      {
         TransferRunnableUnsyn r = new TransferRunnableUnsyn(b, i, INITIAL_BALANCE);
         Thread t = new Thread(r);
         t.start();
      }
   }
}

/**
 * 将一个账户转向以个随机账户
 * @author shi
 *
 */
class TransferRunnableUnsyn implements Runnable
{
   private BankUnsyn bank;
   private int fromAccount;
   private double maxAmount;
   private int DELAY = 10;

   /**
    * Constructs a transfer runnable.
    * @param b the bank between whose account money is transferred
    * @param from the account to transfer money from
    * @param max the maximum amount of money in each transfer
    */
   public TransferRunnableUnsyn(BankUnsyn b, int from, double max)
   {
      bank = b;
      fromAccount = from;
      maxAmount = max;
   }

   public void run()
   {
      try
      {
         while (true)
         {
            int toAccount = (int) (bank.size() * Math.random());
            double amount = maxAmount * Math.random();
            bank.transfer(fromAccount, toAccount, amount);
            Thread.sleep((int) (DELAY * Math.random()));
         }
      }
      catch (InterruptedException e)
      {
      }
   }
}


class BankUnsyn
{
   private final double[] accounts;

   /**
    * Constructs the bank.
    * @param n the number of accounts
    * @param initialBalance the initial balance for each account
    */
   public BankUnsyn(int n, double initialBalance)
   {
      accounts = new double[n];
      for (int i = 0; i < accounts.length; i++)
         accounts[i] = initialBalance;
   }

   /**
    * Transfers money from one account to another.
    * @param from the account to transfer from
    * @param to the account to transfer to
    * @param amount the amount to transfer
    */
   public void transfer(int from, int to, double amount)
   {
      if (accounts[from] < amount) return;
      System.out.print(Thread.currentThread());
      accounts[from] -= amount;
      System.out.printf(" %10.2f from %d to %d", amount, from, to);
      accounts[to] += amount;
      System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
   }

   /**
    * Gets the sum of all account balances.
    * @return the total balance
    */
   public double getTotalBalance()
   {
      double sum = 0;

      for (double a : accounts)
         sum += a;

      return sum;
   }

   /**
    * Gets the number of accounts in the bank.
    * @return the number of accounts
    */
   public int size()
   {
      return accounts.length;
   }
}
