package cn.xutingyin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootHelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHelloApplication.class, args);
        /*  ThreadMXBean maxBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = maxBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(
                threadInfo.getThreadId() + "---" + threadInfo.getThreadName() + "--" + threadInfo.getThreadState());
            Object o = new Object();
            Thread thread = new Thread();
            ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
            Lock rlk = rwl.readLock();
            Lock wlk = rwl.writeLock();
        
        }
        
        }
        
        private void close(Closeable... closebles) throws IOException {
        for (Closeable closeble : closebles) {
            if (closeble != null) {
                closeble.close();
            }
        }
        }*/
    }

}
