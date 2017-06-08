package cn.gdeng.nst.util.server.zookeeper;

import org.apache.zookeeper.ZooKeeper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取task执行权限，解决多服务器同时执行问题
 * 使用了zookeeper CreateMode.EPHEMERAL文件
 * 当客户端断开连接时 文件会自动删除
 * 休眠10秒关闭客户端，解决服务器之间时差问题
 * 解决重复执行的问题(服务器时差不能大于10秒)
 * @author nfzhang 20170202
 *
 */
@Aspect
public class ZookeeperAspect {
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  /** 定时任务包名+方法名 如下：
   * 请复制后加到最前面  execution(* cn.gdeng.nst.server.order.quartz.OrderConfirmHandleTaskImpl.execute()) or
   * **/
  private static final String PACKAGE_PATH = "execution(* cn.gdeng.nst.server.order.quartz.OrderConfirmHandleTaskImpl.execute()) or "
      + "execution(* cn.gdeng.nst.server.order.quartz.PrePaymentOverdueTask.execute()) or "
      + "execution(* cn.gdeng.nst.server.source.quartz.GoodsOverdueTask.execute()) or "
      + "execution(* cn.gdeng.nst.server.source.quartz.RuleLogisticTask.execute()) or "
      + "execution(* cn.gdeng.nst.server.advertisement.quartz.AdPutOnTask.execute()) or "
      + "execution(* cn.gdeng.nst.server.order.quartz.DriverExamineCargoTimeOutTask.execute(..))";
  /** 项目名称,用于zk根目录**/
  private static String PROJECT_NAME = null;
  /** zookeeper集群配置信息 **/
  private static String ZOOKEEPER_HOSTS = null;
      
  /**
   * 定时任务zk切面
   * @param joinPoint
   */
  @Around(PACKAGE_PATH)  
  public void zkAround(ProceedingJoinPoint joinPoint){
    String clazz = joinPoint.getTarget().getClass().getName();
    ZooKeeper zk = null;
    logger.info("task start---->"+clazz);
    try {
      zk = ZookeeperUtil.getRunRight(ZOOKEEPER_HOSTS, "/"+PROJECT_NAME, "/"+clazz);
      if(null != zk){
        joinPoint.proceed();
        logger.info("task finished---->"+clazz);
      }else{
        logger.info("task no right---->"+clazz);
      }
    } catch (Throwable e) {
      logger.info("task error---->"+clazz);
      logger.error("",e);
    }finally{
      if(null != zk){
        try {
          //休眠10秒解决服务器之间时差问题
          Thread.sleep(10000);
          zk.close();
        } catch (Exception e) {
          logger.error("",e);
        }
      }
    }
  }

  public static String getPROJECT_NAME() {
    return PROJECT_NAME;
  }

  public static void setPROJECT_NAME(String pROJECT_NAME) {
    PROJECT_NAME = pROJECT_NAME;
  }

  public static String getZOOKEEPER_HOSTS() {
    return ZOOKEEPER_HOSTS;
  }

  public static void setZOOKEEPER_HOSTS(String zOOKEEPER_HOSTS) {
    ZOOKEEPER_HOSTS = zOOKEEPER_HOSTS;
  }
  
  
}
