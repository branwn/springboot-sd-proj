package org.spring.springboot.domain;

/**
 * 钱包实体类
 *
 * Created by bysocket on 07/02/2017.
 */
public class Wallet {


    private long id;

    private long asset;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAsset() {
        return asset;
    }

    public void setAsset(long asset) {
        this.asset = asset;
    }
}
