package com.example.wangkuan.fuwuyinyuebofang;

import android.os.Environment;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

/**
 * 1：左右
 * 2：名字
 * 3：日期
 */
public class SaoMiao {


    private static ArrayList<File> ls;

    public static ArrayList<File> Sdk() {
        ls = new ArrayList<>();
        File file = Environment.getExternalStorageDirectory();
        getSaoMiao(file);
        return ls;
    }

    private static void getSaoMiao(File file) {

        //  --file.listFiles:是指把找到的sdcard卡这个盘符里面的文件夹文件的全部罗列出来，
        //  列出的类型就是一个File数组。需要一个过滤器，直接new
        File[] listFiles = file.listFiles(new FileFilter() {

            //  --自动重写一个accept（接受）的方法，返回值是boolean类型，
            //   返回true:就是将该文件或文件夹加到一个数组中,而file.listFiles本身就是一个File类型的数组
            //  所以就直接存进了listFiles里面
            @Override
            public boolean accept(File pathname) {
                // TODO Auto-generated method stub
                // --判断pathname:是不是文件夹，也就是判断的是sdcard盘符里面的东西是不是文件夹
                if (pathname.isDirectory()) {
                    //   --如果是文件夹的话就存到数组中，直接返回true就可以了。
                    return true;
                    //    --得到pathname.getAbsolutePath().endsWith(tag)：得到路径名有后缀的绝对路径，
                    //    也是全路径名称如mnt/sdcaed/a.mp3,相当于sdcaed这个盘符最外层里面有a.mp3这首歌，
                    //    如果不挂后缀的话会得到sdcard这个盘符最外层里的所有文件(.txt.mp4类型)和文件夹
                    //    ，只要是绝对的就相当于找到了我们需要的MP3格式的文件
                } else if (pathname.getAbsolutePath().endsWith(".mp3")) {//--如果是后缀名是jpq的绝对路径存到数组中
                    return true;
                }
                return false;
            }
        });
        //    --扫描完以后，判断数组是不是空
        if (listFiles != null) {//如果数组不等于空，说明找到了
            //     --遍历数组
            for (File file2 : listFiles) {
                //  --还需要判断一下，扫描后存到数组中的是文件夹，还是我们需要的。MP3的文件
                //  如果是我们需要的MP3这个文件就创建一个集合存到集合中，类型肯定是Flie
                //    因为File可以是文件或文件夹
                if (file2.isDirectory()) {//--如果file2是文件夹

                 /*       --调用刚才扫描的方法，刚才的是扫描sdcard的最外层的里面的文件或文件夹，
                    刚才判断了是文件夹的也存到了数组中,
                            遍历了数组拿到了刚才扫描的文件夹，
                    现在调用刚才扫描的方法相当于扫描的是（如mnt/sdcard/src这个路径）
                    这个路径也就相当于是盘符了,调用这个方法还是相当于把我们需要的。
                    MP3文件存到了集合中，所以我们需要在上面遍历集合
*/
                    getSaoMiao(file2);
                } else {//--不是文件夹就肯定是我们需要的MP3文件了，直接添加到集合
                    ls.add(file2);//集合一定要写整体的写在成员位置
                }
            }
        }
    }
}
