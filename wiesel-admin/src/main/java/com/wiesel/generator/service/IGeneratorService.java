package com.wiesel.generator.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.wiesel.generator.entity.ReferencedTable;

/**
 *
 * @ClassName 类名：IGeneratorService
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
public interface IGeneratorService {

	List<Map<String, Object>> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);
//
//	Map<String, String> queryTable(String tableName);
//
//	List<Map<String, String>> queryColumns(String tableName);
//
//	List<ReferencedTable> queryReferenced(String tableName);
//
//	byte[] generatorCode(String[] tableNames) throws IOException;
//
//	void generatorAllCode(String[] tableNames) throws IOException;
//
//	void generatorApiCode(String[] tableNames) throws IOException;
//
//	void updateCode(String[] tableNames) throws IOException;

}
