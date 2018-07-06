//package com.wiesel.common.config;
//
//import java.io.IOException;
//import java.lang.reflect.Type;
//
//import com.alibaba.fastjson.parser.DefaultJSONParser;
//import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
//import com.alibaba.fastjson.serializer.JSONSerializer;
//import com.alibaba.fastjson.serializer.ObjectSerializer;
//import com.alibaba.fastjson.serializer.SerializeWriter;
//
///**
// *
// * @ClassName 类名：LongObjectSerializer
// * @Description 功能说明：
// *              <p>
// *              TODO
// *              </p>
// ************************************************************************
// * @date 创建日期：2018年7月4日
// * @author 创建人：wuj
// * @version 版本号：V1.0
// *          <p>
// ***************************          修订记录*************************************
// * 
// *          2018年7月4日 wuj 创建该类功能。
// *
// ***********************************************************************
// *          </p>
// */
//public class LongObjectSerializer implements ObjectDeserializer, ObjectSerializer {
//
//	@Override
//	public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features)
//			throws IOException {
//		SerializeWriter out = serializer.out;
//
//		if (object == null) {
//			out.append("");
//			return;
//		}
//
//		String strVal = object.toString();
//		out.writeString(strVal);
//	}
//
//	@Override
//	public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
//		return null;
//	}
//
//	@Override
//	public int getFastMatchToken() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//}
