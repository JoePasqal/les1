package ru.gb.lesson1;

public class Homework {

  /*
   * 1. Создать класс "Счет в банке" - Account.
   * У этого класса должно быть поле с текущим балансом на счете.
   * У этого класса должно быть 3 метода:
   * - (put) Пополнить счет - принимат положительное число, увеличивает текущий баланс
   * - (take) Вывести средства - принимат положительное число, уменьшает текущий баланс
   * - (getAmount) Показать баланс - ничего не принимает, возвращает текущий баланс (getter)
   *
   * 2. Создать несколько наследников.
   * - Кредитный счет (CreditAccount)
   * Особенность: на каждое снятие нужно наложить комиссию в размере 1% от суммы снятия.
   * То есть, если снимаем 100 у.е., то снять нужно 101.
   * - Депозитный счет (DepositAccount) *
   * Особенность: нельзя снимать средства чаще, чем раз в месяц.
   * То есть, нужно завести поле с датой последнего снятия, и использовать его.
   * (Для дат лучше использовать LocalDate, пример в классе Dates в проекте урока)
   */

}
package ru.gb.lesson1;

public class Account {
    private double balance;

    public Account(double initialBalance) {
        this.balance = initialBalance > 0 ? initialBalance : 0;
    }

    public void put(double amount) {
        if (amount > 0) {
            this.balance += amount;
        } else {
            System.out.println("Сумма пополнения должна быть положительной.");
        }
    }

    public void take(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
        } else {
            System.out.println("Сумма снятия должна быть положительной и не превышать баланс счета.");
        }
    }

    public double getAmount() {
        return this.balance;
    }
}
package ru.gb.lesson1;

public class CreditAccount extends Account {

    public CreditAccount(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void take(double amount) {
        double amountWithCommission = amount * 1.01; // Комиссия 1%
        super.take(amountWithCommission);
    }
}
package ru.gb.lesson1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DepositAccount extends Account {
    private LocalDate lastTakeDate;

    public DepositAccount(double initialBalance) {
        super(initialBalance);
        this.lastTakeDate = LocalDate.now().minusMonths(1); // Дата последнего снятия установлена на месяц назад для возможности снятия после создания счета
    }

    @Override
    public void take(double amount) {
        LocalDate currentDate = LocalDate.now();
        long monthsBetween = ChronoUnit.MONTHS.between(this.lastTakeDate, currentDate);

        if (monthsBetween < 1) {
            System.out.println("Средства можно снимать не чаще одного раза в месяц.");
        } else {
            super.take(amount);
            this.lastTakeDate = currentDate;
        }
    }
}
