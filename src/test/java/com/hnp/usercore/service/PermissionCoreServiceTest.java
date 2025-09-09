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
    void testCreationPermission_WithDuplicateData_Faile() {
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


        when(permissionCoreService.createPermission(dto1)).thenReturn(dto1out);
        when(permissionCoreService.createPermission(dto2)).thenReturn(dto2out);
        when(permissionCoreService.createPermission(dto3)).thenReturn(dto3out);


    }

}
