/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/5/14 21:36</create-date>
 *
 * <copyright file="Predefine.java" company="上海林原信息科技有限公司">
 * Copyright (c) 2003-2014, 上海林原信息科技有限公司. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信息科技有限公司 to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.utility;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.hankcs.hanlp.corpus.io.IIOAdapter;

/**
 * 一些预定义的静态全局变量
 */
public class GlobalParams
{
    /**
     * hanlp.properties的路径，一般情况下位于classpath目录中。
     * 但在某些极端情况下（不标准的Java虚拟机，用户缺乏相关知识等），允许将其设为绝对路径
     */
    public static String HANLP_PROPERTIES_PATH;
    
    public static final double MIN_PROBABILITY = 1e-10;

    /**
     * 日志组件
     */
    public static Logger logger = Logger.getLogger("HanLP");
    static
    {
        logger.setLevel(Level.WARNING);
    }

    /**
     * 输出详细信息的调试模式
     */
    public static boolean DEBUG;

    /**
     * IO适配器（默认null，表示从本地文件系统读取），实现com.hankcs.hanlp.corpus.io.IIOAdapter接口
     * 以在不同的平台（Hadoop、Redis等）上运行HanLP
     */
    public static IIOAdapter IOAdapter;
    
    /**
     * trie树文件后缀名
     */
    public static final String TRIE_EXT = ".trie.dat";
    /**
     * 值文件后缀名
     */
    public static final String VALUE_EXT = ".value.dat";

    /**
     * 逆转后缀名
     */
    public static final String REVERSE_EXT = ".reverse";

    /**
     * 二进制文件后缀
     */
    public static final String BIN_EXT = ".bin";


    /**
     * 将异常转为字符串
     *
     * @param e
     * @return
     */
    public static String exceptionToString(Exception e)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
