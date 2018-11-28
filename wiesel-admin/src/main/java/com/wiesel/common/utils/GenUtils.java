package com.wiesel.common.utils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.wiesel.generator.entity.ColumnEntity;
import com.wiesel.generator.entity.ReferencedTable;
import com.wiesel.generator.entity.TableEntity;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import wiesel.common.utils.IDUtils;


@Slf4j
@UtilityClass
public class GenUtils {
	public static final char UNDERLINE = '_';

	private  List<String> getTemplates() {

		List<String> templates = new ArrayList<String>();
		templates.add("vm/java/Entity.java.vm");
		templates.add("vm/java/Mapper.java.vm");
		templates.add("vm/xml/Mapper.xml.vm");
		templates.add("vm/java/Service.java.vm");
		templates.add("vm/java/ServiceImpl.java.vm");
		templates.add("vm/java/Controller.java.vm");
		templates.add("vm/java/ReqEntity.java.vm");
		
		templates.add("vm/html/list.html.vm");
		templates.add("vm/html/add.html.vm");
		templates.add("vm/html/edit.html.vm");

		templates.add("vm/js/list.js.vm");
		templates.add("vm/js/add.js.vm");
		templates.add("vm/js/edit.js.vm");

		templates.add("vm/sql/menu.sql.vm");
		return templates;
	}

	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：模板信息组装
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param config
	 * @param tableEntity
	 * @return
	 *
	 * @date   创建时间：2018年9月1日
	 * @author 作者：wuj
	 */
	private  VelocityContext getVelocityContext(Configuration config, TableEntity tableEntity) {

		// 设置velocity资源加载器
		Properties prop = new Properties();
		prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		Velocity.init(prop);

		// String mainPath = config.getString("mainPath");
		// mainPath = StringUtils.isBlank(mainPath) ? "com.wiesel" : mainPath;

		// 封装模板数据
		Map<String, Object> map = new HashMap<>();
		map.put("email", config.getString("email"));
		map.put("tableName", tableEntity.getTableName());
		map.put("comments", tableEntity.getComments());
		map.put("pk", tableEntity.getPk());
		map.put("className", tableEntity.getClassName());
		map.put("classname", tableEntity.getClassname());
		map.put("columns", tableEntity.getColumns());
		map.put("listReferencedTable", tableEntity.getListReferencedTable());
		map.put("hasBigDecimal", tableEntity.getHasBigDecimal());

		map.put("package", config.getString("package"));
		String moduleName = config.getString("moduleName");
		map.put("moduleName", moduleName);
		if (StrUtil.isNotBlank(moduleName)) {
			map.put("packageName", config.getString("package") + "." + moduleName);
		} else {
			map.put("packageName", config.getString("package") + "." + moduleName);
		}
		map.put("author", config.getString("author"));
		map.put("datetime", DateUtil.format(DateUtil.date(), DatePattern.NORM_DATE_PATTERN));
		map.put("entityName", String.format(config.getString("entityName"), tableEntity.getClassName()));
		map.put("reqEntityName", map.get("entityName") + "Req");
		map.put("reqEntityname", map.get("classname") + "Req");
		map.put("mapperName", String.format(config.getString("mapperName"), tableEntity.getClassName()));
		map.put("xmlName", String.format(config.getString("xmlName"), tableEntity.getClassName()));
		map.put("serviceName", String.format(config.getString("serviceName"), tableEntity.getClassName()));
		map.put("IServiceImpl", String.format(config.getString("serviceName"), tableEntity.getClassname()));
		map.put("serviceImplName", String.format(config.getString("serviceImplName"), tableEntity.getClassName()));
		map.put("controllerName", String.format(config.getString("controllerName"), tableEntity.getClassName()));

		// 生成的menu.sql语句中的ID
		map.put("menuId", IDUtils.newID());
		map.put("addId", IDUtils.newID());
		map.put("editId", IDUtils.newID());
		map.put("deleteId", IDUtils.newID());
		map.put("batchDeleteId", IDUtils.newID());
		return new VelocityContext(map);
	}

	// 生成渲染表的信息
	private  TableEntity getTableInfo(Configuration config, Map<String, String> table,
			List<ReferencedTable> listReferencedTable, List<Map<String, String>> columns) {
		boolean hasBigDecimal = false;
		// 表信息
		TableEntity tableEntity = new TableEntity();
		tableEntity.setTableName(table.get("tableName"));
		tableEntity.setComments(table.get("tableComment"));
		// 表名转换成Java类名
		String className = tableToJava(tableEntity.getTableName(), config.getString("tablePrefix"));
		tableEntity.setClassName(className); // 设置类名(第一个字母大写)
		tableEntity.setClassname(StringUtils.uncapitalize(className)); // 设置类名(第一个字母小写)

		// 列信息
		List<ColumnEntity> columsList = new ArrayList<>();
		for (Map<String, String> column : columns) {
			ColumnEntity columnEntity = new ColumnEntity();
			columnEntity.setUpperColumnName(column.get("columnName").toUpperCase());
			columnEntity.setColumnName(column.get("columnName"));
			columnEntity.setDataType(column.get("dataType"));
			columnEntity.setComments(column.get("columnComment"));
			columnEntity.setExtra(column.get("extra"));
			columnEntity.setIsNullable(column.get("isNullable"));
			// 列名转换成Java属性名
			String attrName = columnToJava(columnEntity.getColumnName());
			columnEntity.setAttrName(attrName);
			columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

			// 列的数据类型，转换成Java类型
			String attrType = config.getString(columnEntity.getDataType(), "unknowType");
			columnEntity.setAttrType(attrType);
			if (!hasBigDecimal && attrType.equals("BigDecimal")) {
				hasBigDecimal = true;
			}
			// 是否主键
			if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableEntity.getPk() == null) {
				tableEntity.setPk(columnEntity);
			}

			columsList.add(columnEntity);
		}
		tableEntity.setColumns(columsList);
		tableEntity.setHasBigDecimal(hasBigDecimal);
		// 没主键，则第一个字段为主键
		if (tableEntity.getPk() == null) {
			tableEntity.setPk(tableEntity.getColumns().get(0));
		}
		// 转换驼峰
		for (ReferencedTable referencedTable : listReferencedTable) {

			String referencedColumnName = columnToJava(referencedTable.getReferencedColumnName());
			String refTableName = StringUtils
					.uncapitalize(tableToJava(referencedTable.getTableName(), config.getString("tablePrefix")));
			String referencedTableName = StringUtils.uncapitalize(
					tableToJava(referencedTable.getReferencedTableName(), config.getString("tablePrefix")));
			String refColumnName = columnToJava(referencedTable.getColumnName());
			referencedTable.setTableNameJava(refTableName);
			referencedTable.setReferencedColumnNameJava(referencedColumnName);
			referencedTable.setReferencedTableNameJava(referencedTableName);
			referencedTable.setColumnNameJava(refColumnName);
		}
		tableEntity.setListReferencedTable(listReferencedTable);
		return tableEntity;
	}

	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：生成代码
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param table
	 * @param listReferencedTable
	 * @param columns
	 * @param zip
	 * @throws IOException
	 *
	 * @date   创建时间：2018年9月1日
	 * @author 作者：wuj
	 */
	public  void generatorCode(Map<String, String> table, List<ReferencedTable> listReferencedTable,
			List<Map<String, String>> columns, ZipOutputStream zip) throws IOException {
		// 配置信息
		Configuration config = getConfig();
		TableEntity tableEntity = getTableInfo(config, table, listReferencedTable, columns);
		VelocityContext context = getVelocityContext(config, tableEntity);

		// 获取模板列表
		List<String> templates = getTemplates();
		for (String template : templates) {
			// 渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, "UTF-8");
			tpl.merge(context, sw);
			try {
				// 添加到zip
				zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity, config)));
				IOUtils.write(sw.toString(), zip, "UTF-8");

				IOUtils.closeQuietly(sw);
				zip.closeEntry();
			} catch (IOException e) {
				e.printStackTrace();
				log.info("渲染模板失败，表名：" + tableEntity.getTableName());
			}
		}
	}

	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：列名转换成Java属性名
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param columnName
	 * @return
	 *
	 * @date   创建时间：2018年9月1日
	 * @author 作者：wuj
	 */
	private  String columnToJava(String columnName) {
		return WordUtils.capitalizeFully(columnName, new char[] { '_' }).replace("_", "");
	}

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：表名转换成Java类名(替换指定表前缀为空)
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param tableName
	 * @param tablePrefix
	 * @return
	 *
	 * @date 创建时间：2018年8月22日
	 * @author 作者：wuj
	 */
	private  String tableToJava(String tableName, String tablePrefix) {
		if (StringUtils.isNotBlank(tablePrefix)) {
			tableName = tableName.replace(tablePrefix, "");
		}
		return columnToJava(tableName);
	}

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：驼峰格式字符串转换为下划线格式字符串
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param param
	 * @return
	 *
	 * @date 创建时间：2018年8月22日
	 * @author 作者：wuj
	 */
	// private static String camelToUnderline(String param) {
	// if (param == null || "".equals(param.trim())) {
	// return "";
	// }
	// int len = param.length();
	// StringBuilder sb = new StringBuilder(len);
	// for (int i = 0; i < len; i++) {
	// char c = param.charAt(i);
	// if (Character.isUpperCase(c)) {
	// sb.append(UNDERLINE);
	// sb.append(Character.toLowerCase(c));
	// } else {
	// sb.append(c);
	// }
	// }
	// return sb.toString();
	// }

	// public static Map camelToUnderlineMap(Map param) {
	// Map<String, Object> newMap = new HashMap<String, Object>();
	// Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
	// while (it.hasNext()) {
	// Map.Entry<String, Object> entry = it.next();
	// String key = entry.getKey();
	// String newKey = camelToUnderline(key);
	// newMap.put(newKey, entry.getValue());
	// }
	// return newMap;
	// }

	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：获取配置信息
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @return
	 *
	 * @date   创建时间：2018年9月1日
	 * @author 作者：wuj
	 */
	public  Configuration getConfig() {
		try {
			return new PropertiesConfiguration("generator.properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
			log.error("获取配置文件失败,错误信息:"+ExceptionUtil.getExceptionStackTrace(e));
		}
		return null;
	}

	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：获取文件名
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param template
	 * @param tableEntity
	 * @param config
	 * @return
	 *
	 * @date   创建时间：2018年9月1日
	 * @author 作者：wuj
	 */
	private  String getFileName(String template, TableEntity tableEntity, Configuration config) {

		String className = tableEntity.getClassName();
		String classname = tableEntity.getClassname();
		String packageName = config.getString("package");
		String moduleName = config.getString("moduleName");
		

		String entityName = String.format(config.getString("entityName"), className);
		String mapperName = String.format(config.getString("mapperName"), className);
		String xmlName = String.format(config.getString("xmlName"), className);
		String serviceName = String.format(config.getString("serviceName"), className);
		String serviceImplName = String.format(config.getString("serviceImplName"), className);
		String controllerName = String.format(config.getString("controllerName"), className);
		String reqEntityName = String.format(config.getString("entityName"), className) + "Req";

		String packagePath = "src"+File.separator+"main"+File.separator+"java";
		if (StringUtils.isNotBlank(packageName)) {
			packagePath +=File.separator+ packageName.replace(".", File.separator) ;
		}
		if (StringUtils.isNotBlank(moduleName)) {
			packagePath +=File.separator+ moduleName ;
		}

		if (template.contains("Entity.java.vm")&& !template.contains("Req")) {
			return packagePath + File.separator+"entity" + File.separator + entityName + ".java";
		}

		if (template.contains("Mapper.java.vm")) {
			return packagePath + File.separator+"mapper" + File.separator + mapperName + ".java";
		}

		if (template.contains("Service.java.vm")) {
			return packagePath + File.separator+"service" + File.separator + serviceName + ".java";
		}

		if (template.contains("ServiceImpl.java.vm")) {
			return packagePath +File.separator+ "service" + File.separator + "impl" + File.separator + serviceImplName + ".java";
		}

		if (template.contains("Controller.java.vm")) {
			return packagePath +File.separator+ "controller" + File.separator + controllerName + ".java";
		}

		if (template.contains("ReqEntity.java.vm")) {
			return packagePath +File.separator+ "controller" + File.separator +   "req"
					+ File.separator + reqEntityName + ".java";
		}

	
        // html 
		String htmlPrefix="src"+File.separator+"main"+File.separator+"resources";
		String jsPrefix ="src"+File.separator+"main"+File.separator+"resources";
		String xmlPrefix="src"+File.separator+"main"+File.separator+"resources";
		if (StrUtil.isNotBlank(moduleName)) {
			htmlPrefix+=File.separator + "templates" + File.separator + moduleName ;
			jsPrefix+= File.separator + "static" + File.separator+"js"+File.separator+"app"+File.separator + moduleName;
			xmlPrefix+= File.separator + "mapper" + File.separator + moduleName;
		}else {
			htmlPrefix+= File.separator + "templates";
			jsPrefix+= File.separator + "static" + File.separator+"js"+File.separator+"app";
			xmlPrefix+= File.separator + "mapper" ;
		}
		
		if (template.contains("Mapper.xml.vm")) {
			return  xmlPrefix+ File.separator + xmlName
					+ ".xml";
		}
		
		
		if (template.contains("list.html.vm")) {
			return  htmlPrefix+ File.separator+classname
					+ File.separator + classname + ".html";
		}

		if (template.contains("add.html.vm")) {
			return htmlPrefix + File.separator+classname
					+ File.separator +   "add.html";
		}
		if (template.contains("edit.html.vm")) {
			return htmlPrefix + File.separator + classname
					+ File.separator + "edit.html";
		}

		// js
		if (template.contains("list.js.vm")) {
			return jsPrefix + File.separator + classname
					+ File.separator + classname + ".js";
		}

		if (template.contains("add.js.vm")) {
			return jsPrefix + File.separator + classname
					+ File.separator + "add.js";
		}
		
		if (template.contains("edit.js.vm")) {
			return jsPrefix+ File.separator + classname
					+ File.separator + "edit.js";
		}

		if (template.contains("menu.sql.vm")) {
			return classname + "_menu.sql";
		}

		return null;
	}
}
