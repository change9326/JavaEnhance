package entity;

import lombok.*;

import java.util.Set;

/**
 * @author yujiaqi
 * @date 2019-04-20 22:21
 * @description
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Employee {
    private String name;
    private int age;
    private double salary;
    private Status status;
    private Set<String> sns;

    public Employee(int age) {
        this.age = age;
    }

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    public Employee(String name, int age, double salary,Status status) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.status=status;
    }

    public enum Status{
        FREE,
        BUSY,
        VACATION
    }
}
