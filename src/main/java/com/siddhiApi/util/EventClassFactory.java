package com.siddhiApi.util;


public class EventClassFactory {
    private static EventClassFactory factory;

    private EventClassFactory() {}

    public static EventClassFactory getFactory() {
        if(factory == null){
            factory = new EventClassFactory();
        }
        return factory;
    }

    public void createJavaClass(String streamName){

    }
}
