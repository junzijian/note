package com.bebopze.cloud.svc.storage.service.impl;

import com.bebopze.cloud.client.storage.StorageClient;
import com.bebopze.cloud.framework.model.storage.param.StorageParam;
import com.bebopze.cloud.svc.storage.mapper.StorageDOMapper;
import com.bebopze.framework.util.IdWorker;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

/**
 * @author bebopze
 * @date 2019/10/28
 */
@Slf4j
@Service
public class StorageServiceImpl implements StorageClient {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private StorageDOMapper storageDOMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long save(@Valid StorageParam param) {

        Long id = param.getId();

        if (id == null) {

            param.setId(idWorker.nextId());

            // insert
            storageDOMapper.insertSelective(param);

        } else {

            // update
            storageDOMapper.updateByPrimaryKeySelective(param);
        }

        return param.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decrInventory(Long productId, int decrNum) {
        log.info("decrInventory begin        >>>     xid : {}", RootContext.getXID());

        storageDOMapper.decrInventory(productId, decrNum);

        log.info("decrInventory end        >>>     xid : {}", RootContext.getXID());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void incrInventory(Long productId, int incrNum) {
        log.info("incrInventory begin        >>>     xid : {}", RootContext.getXID());

        storageDOMapper.incrInventory(productId, incrNum);

        log.info("incrInventory end        >>>     xid : {}", RootContext.getXID());
    }
}
