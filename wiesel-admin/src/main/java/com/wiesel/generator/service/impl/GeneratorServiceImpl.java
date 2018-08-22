package com.wiesel.generator.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiesel.common.utils.GenUtils;
import com.wiesel.generator.entity.ReferencedTable;
import com.wiesel.generator.mapper.GeneratorMapper;
import com.wiesel.generator.service.IGeneratorService;

/** 
*
* @ClassName   类名：GeneratorServiceImpl 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年8月11日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2018年8月11日   wuj     创建该类功能。
*
***********************************************************************
*</p>
*/
@Service
public class GeneratorServiceImpl implements IGeneratorService{

	@Autowired
	private GeneratorMapper generatorMapper;

	public List<Map<String, Object>> queryList(Map<String, Object> map) {
		return generatorMapper.queryList(map);
	}

	public int queryTotal(Map<String, Object> map) {
		return generatorMapper.queryTotal(map);
	}

	public Map<String, String> queryTable(String tableName) {
		return generatorMapper.queryTable(tableName);
	}

	public List<Map<String, String>> queryColumns(String tableName) {
		return generatorMapper.queryColumns(tableName);
	}
 
	public List<ReferencedTable> queryReferenced(String tableName) {
		return generatorMapper.queryReferenced(tableName);
	}
	
	public byte[] generatorCode(String[] tableNames) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);

		for (String tableName : tableNames) {
			// 查询表信息
			Map<String, String> table = queryTable(tableName);
			// 查询列信息
			List<Map<String, String>> columns = queryColumns(tableName);
			// 查询关联表的信息
			List<ReferencedTable> listReferencedTable = queryReferenced(tableName);
			// 生成代码
			GenUtils.generatorCode(table, listReferencedTable, columns, zip);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}
	
//	@Override
//	public byte[] generatorCode(String[] tableNames) {
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//		ZipOutputStream zip = new ZipOutputStream(outputStream);
//		for(String tableName : tableNames){
//			//查询表信息
//			Map<String, String> table = queryTable(tableName);
//			//查询列信息
//			List<Map<String, String>> columns = queryColumns(tableName);
//			//生成代码
//			GenUtils.generatorCode(table, columns, zip);
//		}
//		IOUtils.closeQuietly(zip);
//		return outputStream.toByteArray();
//	}

}
