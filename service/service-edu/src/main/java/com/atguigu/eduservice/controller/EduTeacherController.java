package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author gm-hacker
 * @since 2020-08-31
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping()
    public R list() {

        //int i = 10/0;
        try {
            int a = 10/0;
        }catch(Exception e) {
            throw new GuliException(20001,"出现自定义异常");
        }
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items", list);
    }

    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable(value = "id") String id) {
        boolean b = eduTeacherService.removeById(id);

        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //3 分页查询讲师的方法
    //current 当前页码
    //limit 每页记录数
    @ApiOperation(value = "分页查询讲师方法")
    @GetMapping(value = "/{current}/{limit}")
    public R pageList(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable long limit){
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);

        //调用方法实现分页
        //调用方法时候，底层封装，把分页所有数据封装到pageTeacher对象里面
        eduTeacherService.page(pageTeacher, null);

        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//数据list集合

//        Map map = new HashMap();
//        map.put("total",total);
//        map.put("rows",records);
//        return R.ok().data(map);

        return R.ok().data("total", total).data("records", records);
    }

    @ApiOperation(value = "分页讲师列表")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageQuery(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable long limit,
            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
            @RequestBody TeacherQuery teacherQuery) {

        Page<EduTeacher> pageTeacher = new Page<>(current, limit);

        eduTeacherService.pageQuery(pageTeacher, teacherQuery);

        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();

        return R.ok().data("total", total).data("records", records);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping("addTeacher")
    public R addTeacher(
            @ApiParam(name = "eduTeacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("getTeacher/{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("teacher", teacher);
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PostMapping("updateTeacher")
    public R updateTeacher(
            @ApiParam(name = "eduTeacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}

