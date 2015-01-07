package com.lvping.lin.course.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author beifeng
 * @Date 2014-6-13 下午4:23:30
 *
 */
public class TableDataUtils {
    
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final String START = "start";
    public static final String LENGTH = "length";
    public static long draw = 1;
    
    public static Map<String, Integer> parseRequest(HttpServletRequest request) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        String start = request.getParameter(START);
        String length = request.getParameter(LENGTH);
        map.put(START, StringUtils.isEmpty(start) ? 0 : Integer.parseInt(start));
        map.put(LENGTH, StringUtils.isEmpty(length) ? DEFAULT_PAGE_SIZE : Integer.parseInt(length));
        return map;
    }
    
    public static Map<String, Object> getDataTable(int count, Collection<? extends Object> collection) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (collection == null) {
            collection = new ArrayList<String>();
        }
        map.put("draw", draw++);
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", collection);
        return map;
    }
    
    public static Map<String, Object> getEmptyTable() {
        return getDataTable(0, new ArrayList<String>());
    }

}
