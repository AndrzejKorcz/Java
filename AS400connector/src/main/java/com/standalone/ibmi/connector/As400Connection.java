package com.standalone.ibmi.connector;

import com.ibm.as400.access.AS400;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Getter
@Setter
@Log
public class As400Connection implements AutoCloseable {

    private AS400 as400;

    private String hostName;
    private String userId;
    private String password;

    public As400Connection(String hostName, String userId, String password) {
        this.hostName = hostName;
        this.userId = userId;
        this.password = password;
        as400 = new AS400(this.hostName, this.userId, this.password);
    }

    @Override
     public void close() {
        as400.disconnectAllServices();
        log.info("Connection to IBMi has been closed.");
    }

}
