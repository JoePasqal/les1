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
    protected double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public void put(double amountToPut) {
        if (amountToPut > 0) {
            balance += amountToPut;
        }
    }

    public void take(double amountToTake) {
        if (amountToTake > 0 && balance >= amountToTake) {
            balance -= amountToTake;
        }
    }

    public double getAmount() {
        return balance;
    }
}
package ru.gb.lesson1;

public class CreditAccount extends Account {

    public CreditAccount(double balance) {
        super(balance);
    }

    @Override
    public void take(double amountToTake) {
        double commission = amountToTake * 0.01; // 1% комиссия
        double totalAmountToTake = amountToTake + commission;
        super.take(totalAmountToTake); // снимаем с учетом комиссии
    }
}
package ru.gb.lesson1;

import java.time.LocalDate;

public class DepositAccount extends Account {
    private LocalDate lastTakeDate;

    public DepositAccount(double balance) {
        super(balance);
        this.lastTakeDate = LocalDate.now().minusMonths(1); // Устанавливаем дату последнего снятия на месяц назад для возможности снятия после создания счета
    }

    @Override
    public void take(double amountToTake) {
        LocalDate now = LocalDate.now();
        if (lastTakeDate.until(now, ChronoUnit.MONTHS) >= 1) { // проверяем, прошел ли месяц с последнего снятия
            super.take(amountToTake);
            lastTakeDate = now; // обновляем дату последнего снятия
        } else {
            System.out.println("Средства можно снимать не чаще одного раза в месяц.");
        }
    }
}
