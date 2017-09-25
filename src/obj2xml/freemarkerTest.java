package obj2xml;

import entity.Area;
import entity.ViewCache;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 利用freemarker框架完成Java对象到xml的转换
 * Created by Administrator on 2017/9/25.
 */
public class freemarkerTest {
    private Configuration configuration = null;

    @Before
    public void init(){
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
    }

    @Test
    public void test1(){
        ViewCache viewCache = new ViewCache();
        List<Area> areaList = new ArrayList<>();
        for(int i=0;i<5;i++) {
            Area area = new Area();
            area.setId(i);
//            area.setAreaType("areaType=="+i);
            area.setName("name=="+i);
            area.setOrdering(i);
            area.setParentId(i);
            area.setPhoneArea("phoneArea=="+i);
            area.setZip("zip=="+i);
            areaList.add(area);
        }
        viewCache.setAreaList(areaList);
        String xml = createWord("temp.ftl",viewCache);
        System.out.println("xml="+xml);
    }

    /**
     * 测试jvm参数获取，若取不到返回null
     * -Dtest=abcde
     */
    @Test
    public void test2(){
        String jvmIndex = System.getProperty("test");
        System.out.println("==" + jvmIndex);
    }

    /**
     * 转换方法
     * @param tmpFile 规则文件名可以全路径
     * @param object  转换对象
     * @return 生成的xml按照字符串的形式返回
     */
    public String createWord(String tmpFile,Object object){
        Map<String,Object> dataMap=new HashMap();
        dataMap.put("dto",object);
        configuration.setClassForTemplateLoading(this.getClass(), "/obj2xml");  //FTL文件所存在的位置，我的只能放在与java相同的包下
        Template t=null;
        String result=null;
        try {
            t = configuration.getTemplate(tmpFile); //文件名
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintWriter pw = new PrintWriter(new BufferedOutputStream(baos),true);
            t.process(dataMap,pw);
            result = new String(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //这里赋值的时候需要注意,xml中需要的数据你必须提供给它,不然会报找不到某元素错的.
    private void getData(Map<String, Object> dataMap) {
        dataMap.put("domaintype", "mytype");
    }

}
