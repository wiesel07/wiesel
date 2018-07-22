//package com.wiesel.common.utils;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
//*
//* @ClassName 类名：R
//* @Description 功能说明：通用返回类
//*              <p>
//*              TODO
//*              </p>
//************************************************************************
//* @date 创建日期：2018年7月4日
//* @author 创建人：wuj
//* @version 版本号：V1.0
//*          <p>
//***************************          修订记录*************************************
//* 
//*          2018年7月4日 wuj 创建该类功能。
//*
//***********************************************************************
//*          </p>
//*/
//public class R extends HashMap<String, Object> {
//	private static final long serialVersionUID = 1L;
//
//	public R() {
//		put("code", 0);
//		put("msg", "操作成功");
//	}
//
//	public static R error() {
//		return error(1, "操作失败");
//	}
//
//	public static R error(String msg) {
//		return error(500, msg);
//	}
//
//	public static R error(int code, String msg) {
//		R r = new R();
//		r.put("code", code);
//		r.put("msg", msg);
//		return r;
//	}
//
//	public static R ok(String msg) {
//		R r = new R();
//		r.put("msg", msg);
//		return r;
//	}
//
//	public static R ok(Map<String, Object> map) {
//		R r = new R();
//		r.putAll(map);
//		return r;
//	}
//
//	public static R ok() {
//		return new R();
//	}
//
//	@Override
//	public R put(String key, Object value) {
//		super.put(key, value);
//		return this;
//	}
//}
