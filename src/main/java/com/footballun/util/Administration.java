/**
 * @author: yen.nt
 * @created on Dec 4, 2015
 */
package com.footballun.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yen.nt
 *
 */
public class Administration {

	public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/tools-config.xml");
        ctx.registerShutdownHook();
        DataImporter dataImporter = ctx.getBean("dataImporter", DataImporter.class);
        dataImporter.importExcel();
    }
}
