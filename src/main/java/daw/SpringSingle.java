package daw;

import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringSingle {
    private static final AbstractXmlApplicationContext C = new FileSystemXmlApplicationContext("D:\\daw\\config.xml");
    public static AbstractXmlApplicationContext getContext(){
        return C;
    }
}

