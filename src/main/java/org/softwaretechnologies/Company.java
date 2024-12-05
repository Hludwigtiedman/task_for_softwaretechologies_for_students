package org.softwaretechnologies;

import org.softwaretechnologies.employee.Employee;
import org.softwaretechnologies.employee.EmployeeType;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private final String name;
    private final List<Employee> employeeList;

    public Company(String name) {
        this.name = name;
        this.employeeList = new ArrayList<>();
    }

    /**
     * Создает и добавляет сотрудника в коллекцию employeeList.
     * @param name имя работника
     * @param baseSalary базовая зарплата сотрудника
     * @param type тип работника
     */
    public void addEmployee(String name, int baseSalary, EmployeeType type) {
        // TODO: реализуйте вышеуказанную функцию
        EmployeeFactory employee = new EmployeeFactory();
        employeeList.add(EmployeeFactory.createEmployee(name, baseSalary,type));
    }

    /**
     * Возвращает сумму зарплат всех сотрудников за указанный месяц
     * @param month номер месяца
     * @return сумма зарплат всех сотрудников за указанный месяц
     */
    public int getMonthSalary(int month) {
        // TODO: реализуйте вышеуказанную функцию
        int Salary = 0;
        int i;
        for (i = 0; i < employeeList.size(); i++) {
            Salary += employeeList.get(i).getMonthSalary(month);
        }
        return Salary;
    }

    public String getName() {
        return name;
    }
}
