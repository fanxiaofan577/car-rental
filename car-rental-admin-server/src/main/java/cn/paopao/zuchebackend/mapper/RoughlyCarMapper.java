package cn.paopao.zuchebackend.mapper;

import cn.paopao.zuchebackend.entity.RoughlyCar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (RoughlyCar)表数据库访问层
 *
 * @since 2022-02-10 15:58:41
 */
@Mapper
public interface RoughlyCarMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RoughlyCar queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param roughlyCar 查询条件
     * @return 对象列表
     */
    List<RoughlyCar> queryAllByLimit(RoughlyCar roughlyCar);

    /**
     * 统计总行数
     *
     * @param roughlyCar 查询条件
     * @return 总行数
     */
    long count(RoughlyCar roughlyCar);

    /**
     * 新增数据
     *
     * @param roughlyCar 实例对象
     * @return 影响行数
     */
    int insert(RoughlyCar roughlyCar);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<RoughlyCar> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<RoughlyCar> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<RoughlyCar> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<RoughlyCar> entities);

    /**
     * 修改数据
     *
     * @param roughlyCar 实例对象
     * @return 影响行数
     */
    int update(RoughlyCar roughlyCar);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

