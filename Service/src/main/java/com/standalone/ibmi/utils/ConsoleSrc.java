package com.standalone.ibmi.utils;

import java.io.Console;

public class ConsoleSrc {

    Console console = System.console();

    public String readHostName() {
       return console.readLine("Enter host name: ") ;
    }

    public String readUserName() {
        return console.readLine("Enter user name: ");
    }

    public String readPassword() {
        return console.readLine("Enter password: ");
    }


}
