package com.zy.common.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * @author by zy.
 */
public class HelloAgent {

    public static void main(String argv[]){
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            ObjectName objectName = new ObjectName("jmxBean:name=hellp");
            mBeanServer.registerMBean(new Hello(), objectName);
            Thread.sleep(30000*100);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
