package com.santander.ibmi.connector;

import com.ibm.as400.access.AS400;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class As400Connection {

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

    public void closeConnection() {
        as400.disconnectAllServices();
    }

}
