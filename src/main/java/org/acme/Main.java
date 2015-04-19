package org.acme;

import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * @author Franky Villadiego
 */
public class Main {

    public static void main(String[] args) {

        DefaultListableBeanFactory container = new DefaultListableBeanFactory();

        GenericBeanDefinition dotMatrixPrinterDef = new GenericBeanDefinition();
        dotMatrixPrinterDef.setBeanClass(DotMatrixPrinter.class);

        GenericBeanDefinition officeDef = new GenericBeanDefinition();
        officeDef.setBeanClass(Office.class);

        ConstructorArgumentValues officeConstructorValues = new ConstructorArgumentValues();
        officeConstructorValues.addGenericArgumentValue(dotMatrixPrinterDef);

        officeDef.setConstructorArgumentValues(officeConstructorValues);

        container.registerBeanDefinition("dotMatrixPrinter", dotMatrixPrinterDef);
        container.registerBeanDefinition("office", officeDef);

        Office officeBean = container.getBean(Office.class);
        officeBean.setMessageToPrint("Hola mundo!!");
        officeBean.print();
    }
}
