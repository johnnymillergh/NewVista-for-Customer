package com.jm.newvista.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by Johnny on 1/21/2018.
 */

public class NetworkConfigurationEntity extends DataSupport {
    private String serverIp;
    private int serverPushPort;
    private int clientPullPort;

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public int getServerPushPort() {
        return serverPushPort;
    }

    public void setServerPushPort(int serverPushPort) {
        this.serverPushPort = serverPushPort;
    }

    public int getClientPullPort() {
        return clientPullPort;
    }

    public void setClientPullPort(int clientPullPort) {
        this.clientPullPort = clientPullPort;
    }
}
