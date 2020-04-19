package com.source.testng.Parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * 这是一个 参数化测试（ 通过 Dataprovider 注解对象传参）*/
public class DataProviderTest {

//    private Method method;

    @Test(dataProvider = "DATA")         //传递一个 dataProvider 属性名称
    public void DataTestMethod(String name,int age){
        System.out.println("name = "+name+"; age = "+age);
    }

    @DataProvider(name="DATA")            // DataProvider 注解的运用，初始化一个名称，用于给测试方法的调用
    public Object[][] ProviderTestMethod(){            // objects类型方法
        Object[][] object = new Object[][]{         //Object类型的数组定义，并初始化出实际对象
                {"张三",10},
                {"李四",20},
                {"王五",30}
        };
        return object;
    }


    //--------------------------如下是通过 Object对象 区分测试方法的参数化测试------------------

    @Test(dataProvider = "method")
    public void TestMethod111(String name,int age){
        System.out.println("TestMethod111的 name = "+name+"; age = "+age);
    }

    @Test(dataProvider = "method")
    public void TestMethod222(String name,int age){
        System.out.println("TestMethod222的 name = "+name+"; age = "+age);
    }

    @DataProvider(name="method")
    public Object[][] parameMethod(Method method){        //传递 method，用于获取 测试方法
        Object[][] result=null;     //定义一个object 数组对象为 null

        if(method.getName().equals("TestMethod111")){       //判断方法名是 TestMethod111 时执行
            result=new Object[][]{
                    {"小赵",11},
                    {"小钱",22}
            };
        }else if (method.getName().equals("TestMethod222")){    //判断方法名是 TestMethod222 时执行
            result=new Object[][]{
                    {"小孙",33},
                    {"小李",44}
            };

        }
        return result;

    }
}
