package principle.ocp.improve;

import java.util.Map;

/**
 * @author yujiaqi
 * @date 2019-09-14 10:15
 * @description
 */
public class CoreAssetService extends BaseDataSource {

    @Override
    public void saveData(Map<String, Object> map) {
        System.out.println("CoreAssetService 保存数据");
    }
}
