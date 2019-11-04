package com.junzijian.cloud.svc.storage.controller;

import com.junzijian.cloud.client.storage.StorageClient;
import com.junzijian.cloud.framework.model.storage.param.StorageParam;
import com.junzijian.cloud.svc.storage.service.StorageService;
import com.junzijian.framework.common.model.response.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author junzijian
 * @date 2019/10/28
 */
@Api(tags = "库存")
@Validated
@RestController
@RequestMapping("/v1/storage")
@Service(protocol = {/*"dubbo",*/ "rest"})
public class StorageController implements StorageClient {

    @Autowired
    private StorageService storageService;


    @ApiOperation(value = "保存库存", notes = "创建/修改")
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultBean<Long> save(@RequestBody @Valid StorageParam param) {
        return ResultBean.ofSuccess(storageService.save(param));
    }

    @ApiOperation(value = "减库存", notes = "cloud")
    @GetMapping("/inventory/decr")
    public ResultBean<Void> decrInventory(@RequestParam @NotNull(message = "productId不能为空") Long productId,
                                          @RequestParam @NotNull(message = "decrNum不能为空") int decrNum) {

        storageService.decrInventory(productId, decrNum);
        return ResultBean.ofSuccess();
    }

    @ApiOperation(value = "加库存", notes = "cloud-dubbo")
    @GetMapping("/inventory/incr")
    public ResultBean<Void> incrInventory(@RequestParam @NotNull(message = "productId不能为空") Long productId,
                                          @RequestParam @NotNull(message = "incrNum不能为空") int incrNum) {

        storageService.incrInventory(productId, incrNum);
        return ResultBean.ofSuccess();
    }
}
