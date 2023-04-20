package org.scu.util;

import org.scu.position.ClientManager;

import java.io.*;

/**
 * 特定数据类型的文件写入
 * @author Eternity
 */
public class Write<E> {
    private ClientManager instance;

    /**
     * 通过ClientManager的引例来初始化
     * @param instance ClientManager的对象
     */
    public Write(ClientManager instance) {
        this.instance = instance;
    }

    /**
     * 无对象的初始化
     */
    public Write() {
        this.instance = ClientManager.getInstance();
    }

    /**
     * 将数据写入文件
     * @param data 需要写入的数据
     * @param file 写入的文件
     */
    public void write(E data, File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            instance.getLogger().info("成功将文件保存进文件:"+file.getAbsolutePath());
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
