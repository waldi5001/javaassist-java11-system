package de.fk.javaassist;

import com.sun.tools.attach.VirtualMachine;

import java.lang.management.ManagementFactory;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.tellMyAStory();
        agentAttach();
        main.tellMyAStory();
    }

    public void tellMyAStory() {
        System.out.println("I tell you the story of a drunken sailor");
    }

    private static void agentAttach() {
        try {
            long pid = ManagementFactory.getRuntimeMXBean().getPid();
            String name = ManagementFactory.getRuntimeMXBean().getName();
            VirtualMachine vm = VirtualMachine.attach(String.valueOf(pid));
            vm.loadAgent("target/javaassist-java11-system.jar", "");
            vm.detach();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
