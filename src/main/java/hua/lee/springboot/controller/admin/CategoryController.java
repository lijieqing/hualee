package hua.lee.springboot.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import hua.lee.springboot.constant.WebConst;
import hua.lee.springboot.controller.AbstractController;
import hua.lee.springboot.controller.helper.ExceptionHelper;
import hua.lee.springboot.dto.MetaDto;
import hua.lee.springboot.dto.Types;
import hua.lee.springboot.exception.TipException;
import hua.lee.springboot.modal.bo.RestResponseBo;
import hua.lee.springboot.service.IMetaService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 分类标签管理
 *
 * @author psvm
 * @date 2018/1/31 22:56
 */
@Controller
@RequestMapping("admin/category")
public class CategoryController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Resource
    private IMetaService metaService;

    @GetMapping(value = "")
    public String index(HttpServletRequest request) {
        List<MetaDto> categories = metaService.getMetaList(Types.CATEGORY.getType(), null, WebConst.MAX_POSTS);
        List<MetaDto> tags = metaService.getMetaList(Types.TAG.getType(), null, WebConst.MAX_POSTS);
        request.setAttribute("categories", categories);
        request.setAttribute("tags", tags);
        return "admin/category";
    }

    @PostMapping(value = "save")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo saveCategory(@RequestParam String cname, @RequestParam Integer mid) {
        try {
            metaService.saveMeta(Types.CATEGORY.getType(), cname, mid);
        } catch (Exception e) {
            String msg = "分类保存失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponseBo.ok();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo delete(@RequestParam int mid) {
        try {
            metaService.delete(mid);
        } catch (Exception e) {
            String msg = "删除失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponseBo.ok();
    }
}
