package org.softwaretechnologies;

import org.softwaretechnologies.employee.*;

public class EmployeeFactory {

    /**
     * Сначала создайте классы, наследуемые от  {@link  org.softwaretechnologies.employee.Employee Employee} для каждого из значений в EmployeeType.
     * Функция должна создавать и возвращать Employee нужного типа. Тип зависит от значения параметра type.
     *  name имя сотрудника
     *  baseSalary базовая зарплата сотрудника
     *  type тип сотрудника
     * @return созданного сотрудника нужного типа. Тип зависит от параметра type.
     */



    public static Employee createEmployee(String name, int baseSalary, EmployeeType type) {
        // TODO: реализуйте вышеуказанную функцию
        if (type == EmployeeType.Programmer) {
            Employee employee = new Programmer(name, baseSalary);
            return employee;
        }
        if (type == EmployeeType.Manager) {
            Employee employee = new Manager(name, baseSalary);
            return employee;
        }
        if (type == EmployeeType.Tester) {
            Employee employee = new Tester(name, baseSalary);
            return employee;
        }

        return null;
    }
}
