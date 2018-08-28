package com.service;

import com.dto.SysUser;
import com.dao.SysUserDao;

import java.io.Serializable;
import java.util.List;

/*
* http://localhost:8080/services
* */

public class TableInfoService implements Serializable {

    private static final long serialVersionUID = 677484458789332877L;
    private SysUserDao requestInfo = new SysUserDao();

    public String getName(String name) {
        return requestInfo.getName(name);
    }

    public String getByName(String name) {
        return requestInfo.getByName(name);
    }

    public List getUserAll() {
        return requestInfo.getAll();
    }

    public SysUser getOne() {
        return requestInfo.getOne();
    }


}