import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.*;
import cn.hutool.core.util.*;

import java.io.*;
import java.util.*;
import java.util.regex.*;


/**
 * @author : ThinkStu
 * @since : 2023/1/23, 17:48, 周一
 **/
public class MainProgram {
    public static void main(String[] args) {
        // 传入 fileName 文件名，文件应位于 resources 目录下
        imageTrasation("README.md");
    }

    private static void imageTrasation(String fileName) {
        Integer count = 0;
        Matcher matcher;
        // 正则表达式适配 原图片链接
        Pattern pattern = Pattern.compile(".*<img.*=\"(.*)\".*=\"(.*)\" .*zoom: ?(\\d+)%.*/>.*");
        // 文件应置于 src/main/resources/目录下
        String basePath = "src/main/resources/";
        // 源文件与目标文件
        File sourceFile = new File(basePath + fileName);
        File targetFile = new File(basePath + "CSDN-" + sourceFile.getName());
        // 目标文件若存在，则删除、新建
        if (targetFile.exists()) {
            targetFile.delete();
        }
        FileReader   fileReader = new FileReader(sourceFile);
        List<String> lines      = fileReader.readLines();
        FileAppender appender   = new FileAppender(targetFile, 16, true);
        // 开始逐行遍历，利用正则表达式进行适配
        for (String line : lines) {
            matcher = pattern.matcher(line);
            if (matcher.matches()) {
                // 打印，测试
                System.out.println("1 ===》" + line);

                Integer rate = Integer.parseInt(matcher.group(3));
                // 对图片尺寸作适配
                if (rate < 33) {
                    rate = 120;
                } else if (rate <= 50) {
                    rate = 240;
                } else if (rate <= 70) {
                    rate = 480;
                } else {
                    rate = 640;
                }
                line = StrUtil.format("![{}]({} ={}x)",
                        matcher.group(2), matcher.group(1), rate);
                count++;
                System.out.println("2 ===》" + line);
            }
            appender.append(line);
        }
        appender.flush();
        System.out.println("执行完毕，一共替换：" + count + " 行");
    }
}
