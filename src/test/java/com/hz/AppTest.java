package com.hz;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {

        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (int i = 0; i < list.size(); i++) {
            VirtualMachineDescriptor virtualMachineDescriptor = list.get(i);
            System.out.println(virtualMachineDescriptor.id()+":"+virtualMachineDescriptor.displayName());
        }
    }

    public void testcpuMonitor(){
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();

        long[] threadIds = threadBean.getAllThreadIds();
        for (long threadId : threadIds) {
            long cpuTime = threadBean.getThreadCpuTime(threadId);
            long userTime = threadBean.getThreadUserTime(threadId);
            double cpuUsage = (double) cpuTime / (double) userTime;
            ThreadInfo threadInfo = threadBean.getThreadInfo(threadId);
            System.out.println(threadInfo.getThreadName()+"<<<<<");
            System.out.println("Thread ID: " + threadId + ", CPU Usage: " + cpuUsage);
        }

        double systemCpuLoad = osBean.getSystemLoadAverage();
        System.out.println("System CPU Load: " + systemCpuLoad);
    }

    public void testSystem(){
        System.out.println(System.getProperty("java.home"));
    }
}
