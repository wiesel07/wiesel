package com.wiesel.generator.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.wiesel.generator.entity.ReferencedTable;

/**
 *
 * @ClassName 类名：GeneratorMapper
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年8月11日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年8月11日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Mapper
public interface GeneratorMapper {

	List<Map<String, Object>> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	Map<String, String> queryTable(String tableName);

	List<Map<String, String>> queryColumns(String tableName);

	List<ReferencedTable> queryReferenced(String tableName);

}
