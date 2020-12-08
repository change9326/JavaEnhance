package principle.ocp.improve;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yujiaqi
 * @date 2019-09-14 10:16
 * @description
 */
public class SourceMQConsumer {

    public static void main(String[] args) {
        Map<String, Object> map =new HashMap<>(10);
        //map.put("key","principle.ocp.improve.CoreAssetService");
        map.put("key","principle.ocp.improve.OaCostService");

        //----------------------------
        String key = (String) map.get("key");
        BaseDataSource baseDataSource=null;
        try {
             baseDataSource = (BaseDataSource) Class.forName(key).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(baseDataSource!=null){
            baseDataSource.saveData(map);
        }
    }
}
