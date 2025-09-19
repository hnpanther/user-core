package com.hnp.usercore.service;

import com.hnp.usercore.dto.PermissionCoreDTO;
import com.hnp.usercore.entity.PermissionCore;
import com.hnp.usercore.exception.DuplicateDataException;
import com.hnp.usercore.exception.InvalidDataException;
import com.hnp.usercore.mapper.PermissionCoreMapper;
import com.hnp.usercore.repository.PermissionCoreRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


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
            throw new InvalidDataException("PermissionCoreDTO is null");
        }
        if(dto.getPermissionName() == null || dto.getPermissionName().isBlank()) {
            logger.error("===> createPermission: permissionName is null or empty");
            throw new InvalidDataException("PermissionName is empty");
        }
        if(permissionCoreRepository.existsByPermissionName(dto.getPermissionName())) {
            logger.error("===> createPermission: permissionName already exists: {}", dto.getPermissionName());
            throw new DuplicateDataException("Permission with name '" + dto.getPermissionName() + "' already exists");
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
            } catch (InvalidDataException | DuplicateDataException e) {
                logger.error("createPermissions: invalid permission data: {}", e.getMessage());
            }
        }

        logger.info("===> createPermissions: list {}, input size is {} and output size is {}", list, dtos.size(), list.size());
        return list;
    }

    @Override
    public Optional<PermissionCoreDTO> getPermissionById(Long id) {
        logger.info("===> getPermissionById {}", id);

        if(id == null) {
            logger.error("getPermissionById: id is null");
            throw new InvalidDataException("id is null");
        }

        Optional<PermissionCoreDTO> permissionCoreDTOOptional = permissionCoreRepository.findById(id)
                .map(permissionCoreMapper::toDTO);
        if(permissionCoreDTOOptional.isPresent()) {
            logger.info("getPermissionById={}: permissionCoreDTO is {}", id, permissionCoreDTOOptional.get());
        } else {
            logger.info("getPermissionById={}: permissionCoreDTO is null", id);
        }
        return permissionCoreDTOOptional;

    }

    @Override
    public Optional<PermissionCoreDTO> getPermissionByName(String name) {
        logger.info("===> getPermissionByName {}", name);
        if(name == null || name.isBlank()) {
            logger.error("getPermissionByName: name is empty or null");
            throw new InvalidDataException("name is empty or null");
        }

        logger.info("===> getPermissionByName {}", name);
        Optional<PermissionCoreDTO> permissionCoreDTOOptional = permissionCoreRepository.findByPermissionName(name)
                .map(permissionCoreMapper::toDTO);
        if(permissionCoreDTOOptional.isPresent()) {
            logger.info("getPermissionByName={}: permissionCoreDTO is {}", name, permissionCoreDTOOptional.get());
        } else {
            logger.info("getPermissionByName={}: permissionCoreDTO is null", name);
        }
        return permissionCoreDTOOptional;
    }

    @Override
    public List<PermissionCoreDTO> getAllPermissions() {
        List<PermissionCore> allPermission = permissionCoreRepository.findAll();
        return allPermission.stream()
                .map(permissionCoreMapper::toDTO).collect(Collectors.toList());

    }

    @Override
    public Set<PermissionCore> findAllPermissionsByIds(Set<Long> ids) {
        return new HashSet<>(permissionCoreRepository.findAllById(ids));
    }



    @Override
    public PermissionCoreDTO updatePermission(PermissionCoreDTO dto) {
        logger.info("===> updatePermission {}", dto);
        if(dto == null || dto.getId() == null) {
            logger.error("updatePermission: dto is null");
            throw new InvalidDataException("dto is null");
        }

        Optional<PermissionCore> permissionCoreOptional = getPermissionCoreById(dto.getId());

        if(permissionCoreOptional.isEmpty()) {
            logger.error("updatePermission: permissionCore is null");
            throw new InvalidDataException("permissionCore is null, id=" + dto.getId());
        }

        PermissionCore permissionCore = permissionCoreOptional.get();

        if(dto.getPermissionName() != null && !dto.getPermissionName().isBlank()) {
            permissionCore.setPermissionName(dto.getPermissionName());
        }

        if(dto.getDescription() != null && !dto.getDescription().isBlank()) {
            permissionCore.setDescription(dto.getDescription());
        }

        PermissionCore saved = permissionCoreRepository.save(permissionCore);

        logger.info("updatePermission: saved {}", saved);

        return permissionCoreMapper.toDTO(saved);
    }



    private Optional<PermissionCore> getPermissionCoreById(Long id) {
        logger.info("===> getPermissionCoreById {}", id);
        return permissionCoreRepository.findById(id);
    }


}
