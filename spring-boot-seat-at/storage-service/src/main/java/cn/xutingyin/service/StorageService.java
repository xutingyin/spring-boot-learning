package cn.xutingyin.service;

import java.sql.SQLException;

import cn.xutingyin.entity.Storage;

public interface StorageService {
    void deduct(String commodityCode, int count);

    Storage get(Integer id);

    void batchUpdate() throws SQLException;

    void batchDelete() throws SQLException;

}
