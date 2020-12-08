package principle.ocp.improve;

import java.util.Map;

/**
 * @author yujiaqi
 * @date 2019-09-14 10:13
 * @description
 */
public abstract class BaseDataSource {

    public abstract void saveData(Map<String, Object> map);
}
