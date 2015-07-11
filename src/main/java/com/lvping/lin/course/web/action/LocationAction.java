package com.lvping.lin.course.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lvping.lin.course.common.Constants;
import com.lvping.lin.course.common.TableDataUtils;
import com.lvping.lin.course.model.entity.Location;
import com.lvping.lin.course.model.service.LocationService;

/**
 * @author NAIBA  
 * @date 2015年7月11日
 */
@Controller
@RequestMapping("/location")
public class LocationAction {
    
    @Resource
    private LocationService locationService;
    
    @RequestMapping("/add")
    public String add(HttpServletRequest request) {
        request.getSession().setAttribute(Constants.SEESION_LINK, "location");
        return "location/add";
    }
    
    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        request.getSession().setAttribute(Constants.SEESION_LINK, "location");
        return "location/list";
    }
    
    @RequestMapping("/save")
    public String save(HttpServletRequest request) {
        locationService.addLocation(request.getParameter("name"), request.getParameter("adminName"), request.getParameter("adminPassword"));
        return "redirect:list";
    }
    
    @RequestMapping("/data") 
    @ResponseBody
    public Map<String, Object> data() {
        Map<String, Object> datamap = new HashMap<String, Object>();
        try {
            List<Location> list = locationService.getAll();
            datamap = TableDataUtils.getDataTable(list.size(), list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datamap;
    }
    
    @RequestMapping("/valid/{id}")
    public String valid(@PathVariable int id) {
        Location location = new Location();
        location.setId(id);
        location.setStatus(0);
        locationService.updateStatus(location);
        return "redirect:../list";
    }

    @RequestMapping("/invalid/{id}")
    public String invalid(@PathVariable int id) {
        Location location = new Location();
        location.setId(id);
        location.setStatus(1);
        locationService.updateStatus(location);
        return "redirect:../list";
    }
    
    @RequestMapping("/modify")
    public String modify(HttpServletRequest request) {
        Location location = new Location();
        location.setId(Integer.parseInt(request.getParameter("id")));
        location.setName(request.getParameter("name"));
        locationService.updateName(location);
        return "redirect:list";
    }

}
