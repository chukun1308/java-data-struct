package com.chukun.datastruct.hash;

import java.util.ArrayList;
import java.util.List;

/**
  *@auther : chukun
  *@description : 自定义实现hashMap
  *@create : 2019/7/4 10:56
  *@copyright www.hualala.com
  */
public class UserDefineHashMap {

    public static void main(String[] args) {

    }


}

class UserDefineHashTable{

    private final static int DEFAULT_SIZE  = 16;
    private final static float LOAD_FACTOR = 0.75f;

    private EmployeeLinkedList[] employeeLinkedLists;

    private int size;

    public UserDefineHashTable(){
        this(DEFAULT_SIZE);
    }

    public UserDefineHashTable(int size){
         this.size = size;
         employeeLinkedLists = new EmployeeLinkedList[size];
         //分别初始化每个链表
         for(int i=0;i<size;i++){
             employeeLinkedLists[i] = new EmployeeLinkedList();
         }
    }

    public void addEmployee(Employee e){
        int index = hashFun(e.getEmpNo());
        if(getActualHashTableSize()>size*LOAD_FACTOR){
           rehash();
        }
        employeeLinkedLists[index].addEmployee(e);
    }

    public void listEmployee(){
        for(EmployeeLinkedList e:employeeLinkedLists){
            e.listEmployee();
        }
    }

    public Employee findEmpById(int empNo){
        int index = hashFun(empNo);
        return employeeLinkedLists[index].findEmplyeeById(empNo);

    }

    private int getActualHashTableSize(){
        int count=0;
        for(EmployeeLinkedList e:employeeLinkedLists){
            if(e.getActualSize()>0){
                count++;
            }
        }
        return count;
    }

    private void rehash(){
        //这里做简单的处理
        EmployeeLinkedList[] tempList = new EmployeeLinkedList[size*2];
        for(EmployeeLinkedList e :employeeLinkedLists){
            List<Employee> employees = e.employeeList();
            if(employees==null || employees.size()==0){
                continue;
            }
            for(Employee emp:employees){
                int index = hashFun(emp.getEmpNo());
                tempList[index].addEmployee(emp);
            }
        }
        employeeLinkedLists = tempList;
    }

    public int hashFun(int empNo){
        return empNo%employeeLinkedLists.length;
    }
}

class Employee{
    private int empNo;
    private String empName;
    private Employee nextEmp;
    public Employee() {}

    public Employee(int empNo, String empName) {
        this.empNo = empNo;
        this.empName = empName;
        nextEmp = null;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Employee getNextEmp() {
        return nextEmp;
    }

    public void setNextEmp(Employee nextEmp) {
        this.nextEmp = nextEmp;
    }
}

class EmployeeLinkedList{

    private Employee head = new Employee(-1,"");

    private int actualSize;
    public void addEmployee(Employee employee){
        actualSize++;
        if(head.getNextEmp()==null){
             head.setNextEmp(employee);
             return;
        }
        Employee nextEmp = head.getNextEmp();
        while (true){
            if(nextEmp.getNextEmp()==null){
                break;
            }
            nextEmp = nextEmp.getNextEmp();
        }
        nextEmp.setNextEmp(employee);
    }

    public void listEmployee(){
        if(head.getNextEmp()==null){
            return;
        }
        Employee curNode = head.getNextEmp();
        while(true){
            System.out.printf("empNo=%d,empName=%s",curNode.getEmpNo(),curNode.getEmpName());
            if(curNode.getNextEmp()==null){
                break;
            }
            curNode = curNode.getNextEmp();
        }
    }

    public List<Employee> employeeList(){
        if(head.getNextEmp()==null){
            return null;
        }
        List<Employee> resultList = new ArrayList<>();
        Employee curNode = head.getNextEmp();
        while(true){
            resultList.add(curNode);
            if(curNode.getNextEmp()==null){
                break;
            }
            curNode = curNode.getNextEmp();
        }
        return resultList;
    }

    public Employee findEmplyeeById(int empNo){
        if(head.getNextEmp()==null){
            return null;
        }
        Employee curNode = head.getNextEmp();
        boolean isFind=false;
        while(true){
            if(curNode.getNextEmp()==null){
                break;
            }
            if(curNode.getEmpNo()==empNo){
                isFind = true;
                break;
            }
            curNode = curNode.getNextEmp();
        }
        return isFind?curNode:null;
    }

    public int getActualSize() {
        return actualSize;
    }
}
