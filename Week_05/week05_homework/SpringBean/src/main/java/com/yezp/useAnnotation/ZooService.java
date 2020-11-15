package com.yezp.useAnnotation;

import com.yezp.Dog;
import com.yezp.Duck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Created on 2020/11/15 20:28.
 *
 * @author yezp
 */
@Component("zooService")
public class ZooService {

    @Autowired
    private Dog dog;

    @Autowired
    private Duck duck;

    public void animalShow() {
        System.out.println(dog.say());
        System.out.println(duck.say());
        System.out.println("--------------------------");
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Duck getDuck() {
        return duck;
    }

    public void setDuck(Duck duck) {
        this.duck = duck;
    }
}
