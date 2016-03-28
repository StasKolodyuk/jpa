package com.epam.jmp.demo;

import com.epam.jmp.PersistenceConfig;
import com.epam.jmp.model.*;
import com.epam.jmp.repository.EmployeeRepository;
import com.epam.jmp.repository.ProjectRepository;
import com.epam.jmp.repository.UnitRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Main {

    @Resource
    EmployeeRepository employeeRepository;
    @Resource
    ProjectRepository projectRepository;
    @Resource
    UnitRepository unitRepository;

    public static void main(String[] args) {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(PersistenceConfig.class);
        Main main = appContext.getBean(Main.class);
        main.test();
    }

    public void test() {
        Employee employee = new Employee();

        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setFirstName("Stas");
        personalInfo.setLastName("Kolodyuk");
        personalInfo.setGender(Gender.MALE);

        Address address = new Address();
        address.setCountry("Belarus");
        address.setState("Brest");
        address.setCity("Brest");

        employee.setPersonalInfo(personalInfo);
        employee.setAddress(address);
        employee.setStatus(EmployeeStatus.JUNIOR);

        employeeRepository.save(employee);
        Employee fromDB = employeeRepository.findOne(1L);

        // the same for projectRepository, unitRepository
        // e.g. employeeRepository.save(employee);
        //      employeeRepository.findOne(id);
        //      employeerepository.delete(id);
    }
}
