package com.yezp;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            Class clazz = new HelloClassLoader().findClass("Hello");
            if (clazz == null)
                return;

            Object obj = clazz.newInstance();
            Method method = clazz.getMethod("hello");
            method.invoke(obj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) {
        String filePath = System.getProperty("user.dir") + File.separator + "resources";
        String fileName = filePath + File.separator + "hello.xlass";

        File file = new File(fileName);
        byte[] bytes;
        try {
            FileInputStream fs = new FileInputStream(file);
            long size = file.length();
            if (size == 0L) {
                return null;
            }
            if (size > Integer.MAX_VALUE) {
                return null;
            }

            int count = (int) size;
            bytes = new byte[count];
            fs.read(bytes);
            convert(bytes, count);

            fs.close();
            return defineClass(name, bytes, 0, bytes.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void convert(byte[] bs, int len) {
        for (int i = 0; i < len; i++) {
            bs[i] = ((byte) (bs[i] ^ 0xFFFFFFFF));
        }
    }
}
