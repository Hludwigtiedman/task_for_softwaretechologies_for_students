package org.softwaretechnologies;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import static java.lang.Integer.MAX_VALUE;

public class Money {
    private final MoneyType type;
    private final BigDecimal amount;

    public Money(MoneyType type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }

    /**
     * Money равны, если одинаковый тип валют и одинаковое число денег до 4 знака после запятой.
     * Округление по правилу: если >= 5, то в большую сторону, интаче - в меньшую
     * Пример округления:
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     *
     * @param o объект для сравнения
     * @return true - равно, false - иначе
     */
    @Override
    public boolean equals(Object o) {
        // TODO: реализуйте вышеуказанную функцию
        if (this == o) return true; // Проверка на идентичность
        if (!(o instanceof Money)) // Проверка на класс
            return false;

        Money money = (Money) o;

        if(money.type != this.type) // Проверка различие типов
            return false;

        if(money.amount == null && this.amount == null)
            return true;

        if (money.amount == null || this.amount == null)
            return false;

        BigDecimal thisAmount = this.getAmount().setScale(4, RoundingMode.HALF_UP);
        BigDecimal otherAmount = money.getAmount().setScale(4, RoundingMode.HALF_UP);

        return thisAmount.equals(otherAmount);
    }


    /**
     * Формула:
     * (Если amount null 10000, иначе количество денег окрукленные до 4х знаков * 10000) + :
     * если USD , то 1
     * если EURO, то 2
     * если RUB, то 3
     * если KRONA, то 4
     * если null, то 5
     * Если amount округленный до 4х знаков * 10000 >= (Integer.MaxValue - 5), то хеш равен Integer.MaxValue
     * Округление по правилу: если >= 5, то в большую сторону, иначе - в меньшую
     * Пример округления:
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     *
     * @return хеш код по указанной формуле
     */
    @Override
    public int hashCode() {
        // TODO: реализуйте вышеуказанную функцию
        BigDecimal roundedAmount = null;

        if (amount != null){
            roundedAmount = amount.setScale(4, RoundingMode.HALF_UP);
        } else {
            roundedAmount = BigDecimal.valueOf(10000);
        }

        int currencyCode;
        if (type == null){
            currencyCode = 5;
        } else {
            switch (type) {
                case RUB:
                    currencyCode = 3;
                    break;
                case USD:
                    currencyCode = 1;
                    break;
                case EURO:
                    currencyCode = 2;
                    break;
                case KRONA:
                    currencyCode = 4;
                    break;
                default:
                    currencyCode = 0;
                    break;
            }
        }

        int hashCode = roundedAmount.multiply(BigDecimal.valueOf(10000)).intValue()+currencyCode;

        if (hashCode >= MAX_VALUE - 5){
            hashCode = MAX_VALUE;
        }
        return hashCode;
    }

    /**
     * Верните строку в формате
     * Тип_ВАЛЮТЫ: количество.XXXX
     * Тип_валюты: USD, EURO, RUB или KRONA
     * количество.XXXX - округленный amount до 4х знаков.
     * Округление по правилу: если >= 5, то в большую сторону, интаче - в меньшую
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     * <p>
     * Если тип валюты null, то вернуть:
     * null: количество.XXXX
     * Если количество денег null, то вернуть:
     * Тип_ВАЛЮТЫ: null
     * Если и то и то null, то вернуть:
     * null: null
     *
     * @return приведение к строке по указанному формату.
     */
    @Override
    public String toString() {
        // TODO: реализуйте вышеуказанную функцию
        String typeString = "null";
        String num = "null";
        if (this.amount != null) {
            num = this.amount.setScale(4, RoundingMode.HALF_UP).toString();
        }
        if (this.type != null) {
            typeString = this.type.toString();
        }
        return typeString + ": " + num;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public MoneyType getType() {
        return type;
    }

    public static void main(String[] args) {
        Money money = new Money(MoneyType.EURO, BigDecimal.valueOf(10.00012));
        Money money1 = new Money(MoneyType.USD, BigDecimal.valueOf(10.5000));
        System.out.println(money1.toString());
        System.out.println(money1.hashCode());
        System.out.println(money.equals(money1));
    }
}
