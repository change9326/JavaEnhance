package outOfMemoryError;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yujiaqi
 * @date 2020-04-29 08:40
 * @description
 * VM Args:-Xms20m -Xmx20m  -XX:HeapDumpPath=/data -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
    static class OOMObject{

    }
    public static void main(String[] args) {
      List<OOMObject> list=new ArrayList<>();
      while (true){
          list.add(new OOMObject());
      }
    }
}
