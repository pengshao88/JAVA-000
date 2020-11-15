package com.yezp.useXml;

import com.yezp.Dog;
import com.yezp.Duck;

/**
 * Description:
 * Created on 2020/11/15 21:11.
 *
 * @author yezp
 */
public class AnimalService {

    private Dog dog;

    private Duck duck;

    public void walkFun() {
        System.out.println("狗狗使用" + dog.walk());
        System.out.println("鸭子使用" + duck.walk());
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
