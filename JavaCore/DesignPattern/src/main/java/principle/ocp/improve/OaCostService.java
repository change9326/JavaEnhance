package principle.ocp.improve;

import java.util.Map;

/**
 * @author yujiaqi
 * @date 2019-09-14 10:16
 * @description
 */
public class OaCostService extends BaseDataSource {

    @Override
    public void saveData(Map<String, Object> map) {
        System.out.println("OaCostService 保存数据");
    }
}
