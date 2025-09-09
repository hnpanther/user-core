package com.hnp.usercore.service;

import com.hnp.usercore.dto.PermissionCoreDTO;
import com.hnp.usercore.entity.PermissionCore;
import com.hnp.usercore.exception.DuplicatePermissionException;
import com.hnp.usercore.exception.InvalidPermissionDataException;
import com.hnp.usercore.mapper.PermissionCoreMapper;
import com.hnp.usercore.repository.PermissionCoreRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class PermissionsServiceImpl implements PermissionCoreService {

    private static final Logger logger = LoggerFactory.getLogger(PermissionsServiceImpl.class);

    private final PermissionCoreRepository permissionCoreRepository;
    private final PermissionCoreMapper permissionCoreMapper;

    public PermissionsServiceImpl(PermissionCoreRepository permissionCoreRepository, PermissionCoreMapper permissionCoreMapper) {
        this.permissionCoreRepository = permissionCoreRepository;
        this.permissionCoreMapper = permissionCoreMapper;
    }


    @Override
    public PermissionCoreDTO createPermission(PermissionCoreDTO dto) {

        logger.info("===> createPermission with {}", dto);
        if(dto == null) {
            logger.error("createPermission: dto is null");
            throw new InvalidPermissionDataException("PermissionCoreDTO is null");
        }
        if(dto.getPermissionName() == null || dto.getPermissionName().isBlank()) {
            logger.error("===> createPermission: permissionName is null or empty");
            throw new InvalidPermissionDataException("PermissionName is empty");
        }
        if(permissionCoreRepository.existsByPermissionName(dto.getPermissionName())) {
            logger.error("===> createPermission: permissionName already exists: {}", dto.getPermissionName());
            throw new DuplicatePermissionException("Permission with name '" + dto.getPermissionName() + "' already exists");
        }


        PermissionCore entity = permissionCoreMapper.toEntity(dto);
        PermissionCore saved = permissionCoreRepository.save(entity);
        logger.info("createPermission: saved {}", saved);
        return permissionCoreMapper.toDTO(saved);
    }

    @Override
    public List<PermissionCoreDTO> createPermissions(List<PermissionCoreDTO> dtos) {
        logger.info("===> createPermissions with {}", dtos);
        List<PermissionCoreDTO> list = new ArrayList<>();
        for(PermissionCoreDTO dto: dtos) {
            try{
                PermissionCoreDTO permission = createPermission(dto);
                list.add(permission);
            } catch (InvalidPermissionDataException | DuplicatePermissionException e) {
                logger.error("createPermissions: invalid permission data: {}", e.getMessage());
            }
        }

        logger.info("===> createPermissions: list {}, input size is {} and output size is {}", list, dtos.size(), list.size());
        return list;
    }

    @Override
    public PermissionCoreDTO getPermissionById(Long id) {
        return null;
    }

    @Override
    public PermissionCoreDTO getPermissionByName(String name) {
        return null;
    }
}
