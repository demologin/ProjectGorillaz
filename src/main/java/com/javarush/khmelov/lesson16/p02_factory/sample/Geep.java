package com.javarush.khmelov.lesson16.p02_factory.sample;

class Geep implements Car{

    @Override
    public void drive() {
        System.out.println("Drive speed 50 km/h");
    } 
    
    @Override
    public void stop() {
        System.out.println("Stopped at 5 sec");
    }

}
