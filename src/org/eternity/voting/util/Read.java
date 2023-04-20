package org.scu.util;

import org.scu.position.ClientManager;

import java.io.*;

/** 从某文件中读入某数据类型的对象
 * @author Eternity
 * @param <E> 读入的数据类型
 */
public class Read<E> {
    private ClientManager instance;

    /**
     * 通过ClientManager的引例来初始化
     * @param instance ClientManager的对象
     */
    public Read(ClientManager instance) {
        this.instance = instance;
    }

    /**
     * 无对象的初始化
     */
    public Read() {
        this.instance = ClientManager.getInstance();
    }

    /**
     * @param file 读入的文件
     * @return 读入的类型
     */
    public E get(File file) {
        E result = null;
        if (!file.exists()) {
            instance.getLogger().info(Prefix.ERROR+"文件不存在:"+file.getAbsolutePath());
            return null;
        }
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            result = (E) ois.readObject();
            instance.getLogger().info("成功读入文件:"+file.getAbsolutePath());
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
