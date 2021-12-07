package com.example.backend.service.impl;

import com.example.backend.exceprtion.ObjectNotFoundException;
import com.example.backend.model.entity.BaseEntity;
import com.example.backend.model.entity.Vehicle;
import com.example.backend.model.entity.User;
import com.example.backend.model.service.CreateVehicleServiceModel;
import com.example.backend.model.view.VehicleVewModel;
import com.example.backend.repository.VehicleRepository;
import com.example.backend.service.VehicleService;
import com.example.backend.service.UserService;
import com.example.backend.service.VehicleTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleTypeService vehicleTypeService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleTypeService vehicleTypeService, ModelMapper modelMapper, UserService userService) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeService = vehicleTypeService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void create(CreateVehicleServiceModel addCarServiceModel) {

        Vehicle vehicle = modelMapper.map(addCarServiceModel, Vehicle.class);


        if (!addCarServiceModel.getOwner().isBlank()) {
            User user = userService.findUserById(addCarServiceModel.getOwner());
            vehicle.setOwner(user);
        }

        vehicle.setType(vehicleTypeService.findVehicleTypeByName(addCarServiceModel.getType()));


        this.vehicleRepository.save(vehicle);
    }

    @Transactional
    @Override
    public List<VehicleVewModel> getAllVehicles() {
        return vehicleRepository.findAllBy()
                .stream()
                .map(car -> modelMapper.map(car, VehicleVewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Vehicle findById(String id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);

        if (vehicle == null) {
            throw new ObjectNotFoundException("id");
        }

        return vehicle;
    }

    @Override
    public List<VehicleVewModel> getAllAvailableVehicle(List<Vehicle> usedVehicles) {

        Collection<String> ids = usedVehicles
                .stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());

        List<VehicleVewModel> vehicleVewModels;

        if (ids.size() == 0) {
            vehicleVewModels =  vehicleRepository.findAllBy()
                    .stream()
                    .map(vehicle -> modelMapper.map(vehicle, VehicleVewModel.class))
                    .collect(Collectors.toList());
        }else {
            vehicleVewModels = vehicleRepository.findAllByIdNotIn(ids)
                    .stream()
                    .map(vehicle -> modelMapper.map(vehicle, VehicleVewModel.class))
                    .collect(Collectors.toList());
        }

        return vehicleVewModels;
    }

    @Override
    public Page<VehicleVewModel> getAllVehiclesPagination(Integer pageNo, Integer pageSize, String sortBy) {

        PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return vehicleRepository.findAll(pageable)
                .map(this::asVehicleVewModel);
    }

    private VehicleVewModel asVehicleVewModel(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleVewModel.class);
    }
}
