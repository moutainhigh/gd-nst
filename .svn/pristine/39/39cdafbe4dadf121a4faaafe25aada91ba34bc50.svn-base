package cn.gdeng.nst.util.server;

import java.util.Collection;
import java.util.Map;

/**
 * 只能判断一个对象(包括Collection,Map等)是否为空，对基本类型不适用，
 * 比如 new int[]{} ,int i =0这样的基本类型不能正确判断
 * @author fz
 *
 */
public class NullUtil
{
  public static boolean isNull(Object obj)
  {
    if (obj == null)
    {
      return true;
    }

    if ((obj instanceof String))
    {
      if (((String)obj).equals(""))
      {
        return true;
      }
    }
    else {
      if ((obj instanceof Collection))
      {
        if (((Collection)obj).size() > 0) {
          return false;
        }
        return true;
      }

      if ((obj instanceof Object[]))
      {
        if (((Object[])obj).length > 0) {
          return false;
        }
        return true;
      }

      if ((obj instanceof Map))
      {
        if (((Map)obj).size() > 0) {
          return false;
        }
        return true;
      }

      if ((obj instanceof StringBuffer))
      {
        if (((StringBuffer)obj).length() > 0) {
          return false;
        }
        return true;
      }
    }

    return false;
  }

  public static boolean isNotNull(Object obj)
  {
    return !isNull(obj);
  }
}