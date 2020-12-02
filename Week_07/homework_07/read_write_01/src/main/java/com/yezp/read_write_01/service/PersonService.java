package com.yezp.read_write_01.service;

import com.yezp.read_write_01.domain.Person;

import java.util.List;

/**
 * Description:
 * Created on 2020/12/3 0:45.
 *
 * @author yezp
 */
public interface PersonService {

    List<Person> getPersonList();

    int insertPerson(Person person);

}
