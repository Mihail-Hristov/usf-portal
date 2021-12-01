package com.example.backend.service.impl;

import com.example.backend.exceprtion.ObjectNotFoundException;
import com.example.backend.model.entity.Vehicle;
import com.example.backend.repository.VehicleRepository;
import com.example.backend.service.VehicleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

    private Vehicle testVehicle;

    private VehicleServiceImpl vehicleServiceToTest;

    @Mock
    private VehicleRepository mockVehicleRepository;

    @Mock
    private VehicleTypeServiceImpl vehicleTypeService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserServiceImpl userService;

    @BeforeEach
    void init() {
        vehicleServiceToTest = new VehicleServiceImpl(mockVehicleRepository, vehicleTypeService,
                modelMapper,
                userService);

        testVehicle = new Vehicle();
        testVehicle.setDescription("Test vehicle");
        testVehicle.setMake("Test make");
        testVehicle.setModel("Test model");
        testVehicle.setId("5");
        testVehicle.setCapacity(5);
        testVehicle.setFuelConsumption(6.5);


    }

    @Test
    void create() {

    }

    @Test
    void getAllVehicles() {



    }

    @Test
    void testVehicleFindById() {

        Mockito.when(mockVehicleRepository.findById(testVehicle.getId()))
                .thenReturn(Optional.of(testVehicle));

        var actualVehicle = mockVehicleRepository.findById(testVehicle.getId());

        Assertions.assertEquals(actualVehicle.get(), testVehicle);

        Assertions.assertEquals(actualVehicle.get().getMake(), testVehicle.getMake());
        Assertions.assertEquals(actualVehicle.get().getModel(), testVehicle.getModel());
        Assertions.assertEquals(actualVehicle.get().getId(), testVehicle.getId());
        Assertions.assertEquals(actualVehicle.get().getDescription(), testVehicle.getDescription());
        Assertions.assertEquals(actualVehicle.get().getCapacity(), testVehicle.getCapacity());
    }

    @Test
    void testVehicleNotFound() {
        Assertions.assertThrows(
                ObjectNotFoundException.class,
                () -> vehicleServiceToTest.findById("1")
        );
    }

    @Test
    void getAllAvailableVehicle() {


    }

    @Test
    void getAllVehiclesPagination() {


    }
}