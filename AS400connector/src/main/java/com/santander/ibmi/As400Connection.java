package com.santander.ibmi;

import com.ibm.as400.access.AS400;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class As400Connection {

    private AS400 as400;

    private String hostName;
    private String userId;
    private String password;


    private AS400 newConnection() {
        return new AS400(hostName, userId, password);
    }

    public boolean openConnection() {
        if(as400 == null) {
            as400 = newConnection();
        }
        return Boolean.TRUE;
    }

    public void closeConnection() {
        as400.disconnectAllServices();
    }

}
