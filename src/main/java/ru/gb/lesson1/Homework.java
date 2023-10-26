package ru.gb.lesson1;

package ru.gb.lesson1;

public interface Account {
    double getAmount();
    void put(double amount);
    void take(double amount);
}

// AbstractAccount.java
package ru.gb.lesson1;

public abstract class AbstractAccount implements Account {
    protected double balance;

    public AbstractAccount(double initialBalance) {
        this.balance = initialBalance > 0 ? initialBalance : 0;
    }

    @Override
    public double getAmount() {
        return this.balance;
    }

    @Override
    public void put(double amount) {
        if (amount > 0) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException("Сумма пополнения должна быть положительной");
        }
    }

    @Override
    public void take(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма снятия должна быть положительной");
        }
        if (amount > this.balance) {
            throw new IllegalArgumentException("Недостаточно средств на счете");
        }
        doTake(amount);
    }

    protected abstract void doTake(double amount);
}

// FixedAmountAccount.java
package ru.gb.lesson1;

public class FixedAmountAccount extends AbstractAccount {

    public FixedAmountAccount(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void put(double amount) {
        // Ничего не делаем, чтобы баланс оставался неизменным
    }

    @Override
    protected void doTake(double amount) {
        // Ничего не делаем, чтобы баланс оставался неизменным
    }
}
