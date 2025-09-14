package com.hnp.usercore.service;

import com.hnp.usercore.dto.PermissionCoreDTO;
import com.hnp.usercore.entity.PermissionCore;
import com.hnp.usercore.exception.DuplicatePermissionException;
import com.hnp.usercore.exception.InvalidPermissionDataException;
import com.hnp.usercore.mapper.PermissionCoreMapper;
import com.hnp.usercore.repository.PermissionCoreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PermissionCoreServiceTest {

    @Mock
    private PermissionCoreRepository permissionCoreRepository;
    @Mock
    private PermissionCoreMapper permissionCoreMapper;

    @InjectMocks
    private PermissionsServiceImpl permissionCoreService;


    @Test
    void testCreatePermission_WithValidData_Success() {

        // test data

        String permissionName = "READ_USER";
        Long permissionId = 1L;

        PermissionCoreDTO dto = new PermissionCoreDTO();
        dto.setPermissionName(permissionName);

        PermissionCore entity = new PermissionCore();
        entity.setPermissionName(permissionName);

        PermissionCore savedEntity = new PermissionCore();
        savedEntity.setId(permissionId);
        savedEntity.setPermissionName(permissionName);

        PermissionCoreDTO savedDto = new PermissionCoreDTO();
        savedDto.setId(permissionId);
        savedDto.setPermissionName(permissionName);


        // condition
        when(permissionCoreRepository.existsByPermissionName(dto.getPermissionName()))
                .thenReturn(false);
        when(permissionCoreMapper.toEntity(dto)).thenReturn(entity);
        when(permissionCoreRepository.save(entity)).thenReturn(savedEntity);
        when(permissionCoreMapper.toDTO(any(PermissionCore.class))).thenReturn(savedDto);


        // act
        PermissionCoreDTO result = permissionCoreService.createPermission(dto);

        // assert
        assertNotNull(result);
        assertEquals(permissionName, result.getPermissionName());
        assertEquals(permissionId, result.getId());
        verify(permissionCoreRepository).save(entity);



    }

    @Test
    void testCreationPermission_WithInvalidData_Fail() {

        String permissionName = " ";
        PermissionCoreDTO dto = new PermissionCoreDTO();
        dto.setPermissionName(permissionName);

        assertThrows(
                InvalidPermissionDataException.class,
                () -> permissionCoreService.createPermission(dto)
        );
        verify(permissionCoreRepository, never()).save(any(PermissionCore.class));
    }

    @Test
    void testCreationPermission_WithDuplicateData_Fail() {
        String permissionName = "READ_USER";
        PermissionCoreDTO dto = new PermissionCoreDTO();
        dto.setPermissionName(permissionName);

        when(permissionCoreRepository.existsByPermissionName(dto.getPermissionName())).thenReturn(true);

        assertThrows(
                DuplicatePermissionException.class,
                () -> permissionCoreService.createPermission(dto)
        );
        verify(permissionCoreRepository, never()).save(any(PermissionCore.class));
    }

    @Test
    void testCreationListPermission_WithValidData_Success() {
        PermissionCoreDTO dto1 = new PermissionCoreDTO();
        dto1.setPermissionName("READ_USER");

        PermissionCoreDTO dto2 = new PermissionCoreDTO();
        dto2.setPermissionName("CREATE_USER");

        PermissionCoreDTO dto3 = new PermissionCoreDTO();
        dto3.setPermissionName("UPDATE_USER");

        List<PermissionCoreDTO> list = new ArrayList<>();
        list.add(dto1);
        list.add(dto2);
        list.add(dto3);

        PermissionCoreDTO dto1out = new PermissionCoreDTO();
        dto1out.setPermissionName("READ_USER");
        dto1out.setId(1L);
        PermissionCoreDTO dto2out = new PermissionCoreDTO();
        dto2out.setPermissionName("CREATE_USER");
        dto2out.setId(2L);
        PermissionCoreDTO dto3out = new PermissionCoreDTO();
        dto3out.setPermissionName("UPDATE_USER");
        dto1out.setId(3L);

        PermissionCore pc = new PermissionCore();
        pc.setPermissionName("READ_USER");
        pc.setId(1L);


        when(permissionCoreRepository.existsByPermissionName(any())).thenReturn(false);
        when(permissionCoreMapper.toEntity(any())).thenReturn(pc);
        when(permissionCoreRepository.save(any())).thenReturn(pc);
        when(permissionCoreMapper.toDTO(any(PermissionCore.class))).thenReturn(dto1out);


        List<PermissionCoreDTO> permissions = permissionCoreService.createPermissions(list);
        assertNotNull(permissions);
        assertEquals(list.size(), permissions.size());
        verify(permissionCoreRepository, times(3)).save(any(PermissionCore.class));


    }

    @Test
    void testCreationListPermission_WithInvalidData_Fail() {
        PermissionCoreDTO dto1 = new PermissionCoreDTO();
        dto1.setPermissionName("READ_USER");

        PermissionCoreDTO dto1out = new PermissionCoreDTO();
        dto1out.setPermissionName("READ_USER");
        dto1out.setId(1L);

        PermissionCore pc = new PermissionCore();
        pc.setPermissionName("READ_USER");
        pc.setId(1L);


        PermissionCoreDTO dto2 = new PermissionCoreDTO();
        PermissionCore pc2 = new PermissionCore();

        List<PermissionCoreDTO> list = new ArrayList<>();
        list.add(dto1);
        list.add(dto2);

        when(permissionCoreRepository.existsByPermissionName(any())).thenReturn(false);
        when(permissionCoreMapper.toEntity(any())).thenReturn(pc);
        when(permissionCoreRepository.save(any())).thenReturn(pc);
        when(permissionCoreMapper.toDTO(any(PermissionCore.class))).thenReturn(dto1out);

        permissionCoreService.createPermissions(list);

        verify(permissionCoreRepository, never()).save(pc2);
        verify(permissionCoreRepository,times(1)).save(pc);

    }


    @Test
    void testGetPermissionById_WithValidData_Success() {
        Long id = 1L;
        String permissionName = "READ_USER";

        PermissionCore permissionCore = new PermissionCore();
        permissionCore.setId(id);
        permissionCore.setPermissionName(permissionName);

        PermissionCoreDTO dto = new PermissionCoreDTO();
        dto.setPermissionName(permissionName);
        dto.setId(id);
        Optional<PermissionCoreDTO> permissionCoreDTOOptional = Optional.of(dto);

        when(permissionCoreRepository.findById(id)).thenReturn(Optional.of(permissionCore));
        when(permissionCoreMapper.toDTO(permissionCore)).thenReturn(dto);


        Optional<PermissionCoreDTO> permissionById = permissionCoreService.getPermissionById(id);

        assertNotNull(permissionById);
        assertEquals(id, permissionById.get().getId());
        assertEquals(permissionName, permissionById.get().getPermissionName());


    }

    @Test
    void testGetPermissionById_WithInvalidData_Fail() {

        Long id = null;

        assertThrows(
                InvalidPermissionDataException.class,
                () -> permissionCoreService.getPermissionById(id)
        );
        verify(permissionCoreRepository, never()).findById(any());


    }


    @Test
    void testGetPermissionById_NotFound() {

        when(permissionCoreRepository.findById(any())).thenReturn(Optional.empty());

        Optional<PermissionCoreDTO> permissionById = permissionCoreService.getPermissionById(1L);
        assertFalse(permissionById.isPresent());
    }

    @Test
    void testGetPermissionByName_WithValidData_Success() {

        String permissionName = "READ_USER";
        Long id = 1L;
        PermissionCore permissionCore = new PermissionCore();
        permissionCore.setPermissionName(permissionName);
        permissionCore.setId(id);

        PermissionCoreDTO dto = new PermissionCoreDTO();
        dto.setPermissionName(permissionName);
        dto.setId(id);

        when(permissionCoreRepository.findByPermissionName(any())).thenReturn(Optional.of(permissionCore));
        when(permissionCoreMapper.toDTO(permissionCore)).thenReturn(dto);

        Optional<PermissionCoreDTO> permissionByName = permissionCoreService.getPermissionByName(permissionName);
        assertEquals(permissionCore.getPermissionName(), permissionByName.get().getPermissionName());

    }

    @Test
    void testGetPermissionByName_WithInvalidData_Fail() {
        String permissionName = " ";

        assertThrows(
                InvalidPermissionDataException.class,
                () -> permissionCoreService.getPermissionByName(permissionName)
        );
    }

    @Test
    void testDeletePermissionByName_NotFound() {

        when(permissionCoreRepository.findByPermissionName(any())).thenReturn(Optional.empty());

        Optional<PermissionCoreDTO> permissionByName = permissionCoreService.getPermissionByName("READ_USER");

        assertFalse(permissionByName.isPresent());
    }

}
