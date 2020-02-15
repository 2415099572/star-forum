package com.starforum.base.controller;

import com.github.pagehelper.PageInfo;
import com.starforum.base.entity.Label;
import com.starforum.base.service.LabelService;
import com.starforum.entity.PageResult;
import com.starforum.entity.Result;
import com.starforum.entity.StateCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/label")
@ComponentScan(basePackages = {"com.starforum.handler"})
public class LabelController {

    @Autowired
    LabelService labelService;

    @ApiOperation(value = "查询所有标签")
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StateCode.SUCCESS, "查询成功", labelService.findAll());
    }

    @ApiOperation(value = "根据id查询标签")
    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public Result findById(@PathVariable("labelId") long labelId){
        return new Result(true, StateCode.SUCCESS, "查询成功", labelService.findById(labelId));
    }

    @ApiOperation(value = "添加标签")
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true, StateCode.SUCCESS, "保存成功");
    }

    @ApiOperation(value = "根据id删除标签")
    @RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable long labelId){
        labelService.delete(labelId);
        return new Result(true, StateCode.SUCCESS, "删除成功");
    }

    @ApiOperation(value = "修改标签")
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestBody Label label){
        labelService.update(label);
        return new Result(true, StateCode.SUCCESS, "修改成功");
    }

    @ApiOperation(value = "根据条件查标签")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result query(@RequestBody Label label){
        return new Result(true, StateCode.SUCCESS, "查询成功", labelService.query(label));
    }

    @ApiOperation(value = "分页查询标签")
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result pageQuery(@RequestBody Label label, @PathVariable("page") int page, @PathVariable("size") int size){
        PageInfo<Label> pageData = labelService.pageQuery(label, page, size);
        return new Result(true, StateCode.SUCCESS, "查询成功", new PageResult<Label>(pageData.getTotal(), pageData.getList()));
    }

}
