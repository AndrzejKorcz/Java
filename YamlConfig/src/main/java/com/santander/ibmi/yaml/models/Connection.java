package com.santander.ibmi.yaml.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public final class Connection {
    private String hostName;
    private String userId;
    private String password;
}
