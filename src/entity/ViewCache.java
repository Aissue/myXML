package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/23.
 */
public class ViewCache {
    private List<Area> areaList = new ArrayList();
    public List getAreaList() {
        return areaList;
    }
    public void setAreaList(List areaList) {
        this.areaList = areaList;
    }

    // 供Digester调用的方法
    public void addArea(Area area) {
        this.areaList.add(area);
    }

    @Override
    public String toString() {
        StringBuffer sb=new StringBuffer();
        for(Area area : areaList){
            sb.append(area.getId()+"|"+area.getName()+"|")
            .append(area.getAreaType()+"|"+area.getPhoneArea()+"|")
            .append(area.getOrdering()+"|"+area.getParentId()+"\n");
        }
        return sb.toString();
    }
}
