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

	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：获取当前数据库表信息列表
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param map
	 * @return
	 *
	 * @date   创建时间：2018年8月22日
	 * @author 作者：wuj
	 */
	List<Map<String, Object>> queryList(Map<String, Object> map);

	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：获取当前数据库表信息记录总数
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param map
	 * @return
	 *
	 * @date   创建时间：2018年8月22日
	 * @author 作者：wuj
	 */
	int queryTotal(Map<String, Object> map);

	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：根据表名查询对应表的相应信息
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param tableName
	 * @return
	 *
	 * @date   创建时间：2018年8月22日
	 * @author 作者：wuj
	 */
	Map<String, String> queryTable(String tableName);

	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：根据表名查询对应表的列信息
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param tableName
	 * @return
	 *
	 * @date   创建时间：2018年8月22日
	 * @author 作者：wuj
	 */
	List<Map<String, String>> queryColumns(String tableName);

	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：根据表名查询对应表的数据信息
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param tableName
	 * @return
	 *
	 * @date   创建时间：2018年8月22日
	 * @author 作者：wuj
	 */
	List<ReferencedTable> queryReferenced(String tableName);

}
