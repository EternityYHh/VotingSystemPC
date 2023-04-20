package org.scu.util;

import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;

/**
 * 用于格式化输出与日志编辑
 * @author Eternity
 */
public class Logger {
    private File logFile;

    /**
     * 初始化Logger
     */
    public Logger() {
        logFile = new File(System.getProperty("user.dir")+"\\logs");
        if (!logFile.exists()) logFile.mkdirs();
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DATE);
        logFile = new File(logFile,"["+y+"-"+m+"-"+d+""+"].log");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 自动带前缀的输出
     * @param str 输出的文字
     */
    public void info(String str) {
        System.out.println(Prefix.PREFIX+str);
        try {
            log(str);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void info(Player p, String str) {
        p.sendMessage(Prefix.PREFIX+str);
        try {
            log("给玩家"+p.getName()+"发送了一条消息:"+str);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void log(String str) throws FileNotFoundException {
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        PrintStream out = new PrintStream(logFile);
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DATE);
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int s = calendar.get(Calendar.SECOND);
        String time = "["+y+"年"+m+"月"+d+"日"+h+"时"+min+"分"+s+"秒"+"]";
        out.println(time+str);
    }
}
