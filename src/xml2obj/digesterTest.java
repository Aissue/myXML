package xml2obj;

import common.XmlStore;
import entity.Area;
import entity.ViewCache;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.*;

/**
 * digester解析xml报文字符串为Java对象
 * Created by Administrator on 2017/9/23.
 */
public class digesterTest {
    private String xmlStr;

    @Before
    public void getXmlStr(){
        this.xmlStr = XmlStore.XML;

    }

    /**
     * 方式一
     */
    @Test
    public void test1() throws Exception{
        Digester digester = new Digester();
        /*不进行XML与相应的DTD的合法性验证*/
        digester.setValidating(false);
        /*当遇到viewcache/areas时创建一个ViewCache对象，并将其放在栈顶*/
        digester.addObjectCreate("viewcache/areas", ViewCache.class);
        /*当遇到viewcache/areas/area时创建Area对象，并将其放在栈顶*/
        digester.addObjectCreate("viewcache/areas/area", Area.class);
        /*设置对象属性,与xml文件对应,不设置则是默认*/
        digester.addBeanPropertySetter("viewcache/areas/area/id", "id");
        digester.addBeanPropertySetter("viewcache/areas/area/parentId", "parentId");
        digester.addBeanPropertySetter("viewcache/areas/area/name", "name");
        digester.addBeanPropertySetter("viewcache/areas/area/areaType", "areaType");
        digester.addBeanPropertySetter("viewcache/areas/area/ordering", "ordering");
        digester.addBeanPropertySetter("viewcache/areas/area/zip", "zip");
        digester.addBeanPropertySetter("viewcache/areas/area/phoneArea", "phoneArea");
        /* 当移动到下一个标签中时的动作 */
        digester.addSetNext("viewcache/areas/area", "addArea");

        ViewCache vc = null;
        try {
//            vc = (ViewCache) digester.parse(new File("D:/IdeaProjects/myXML/src/xml2obj/viewcache.xml"));
            InputStream in_xmlStr = new ByteArrayInputStream(this.xmlStr.getBytes("UTF-8"));
            vc = (ViewCache) digester.parse(in_xmlStr);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        System.out.println(vc.toString());

    }

    /**
     * 方式二：
     */
    @Test
    public void test2(){
        Digester digester = DigesterLoader.createDigester(digesterTest.class.getResource("rule.xml"));

        ViewCache vc = null;
        try {
            InputStream in_xmlStr = new ByteArrayInputStream(this.xmlStr.getBytes("UTF-8"));
            vc = (ViewCache) digester.parse(in_xmlStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(vc.toString());
    }

    /**
     * 方式3：该方式适用于xml节点带有属性节点的情况
     */
    public void test3(){

    }

}
