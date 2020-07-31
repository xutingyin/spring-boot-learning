package cn.xutingyin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import cn.xutingyin.entity.Storage;

@Mapper
@Component
public interface StorageMapper {

    Storage selectById(@Param("id") Integer id);

    Storage findByCommodityCode(@Param("commodityCode") String commodityCode);

    int updateById(Storage record);

    void insert(Storage record);

    void insertBatch(List<Storage> records);

    int updateBatch(@Param("list") List<Long> ids, @Param("commodityCode") String commodityCode);
}