/**
 *  Copyright 2016 SmartBear Software
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.swagger.sample.data;

import io.swagger.sample.model.Category;
import io.swagger.sample.model.Vehicle;
import io.swagger.sample.model.Tag;
import io.swagger.sample.model.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleData {
  static List<Vehicle> vehicles = new ArrayList<Vehicle>();

  static {

    vehicles.add(createVehicle(1, "car1"));
    vehicles.add(createVehicle(2, "car2"));
  }

  public Vehicle getVehicleById(long vehicleId) {
    for (Vehicle vehicle : vehicles) {
      if (vehicle.getId() == vehicleId) {
        return vehicle;
      }
    }
    return null;
  }

  public boolean deleteVehicle(long vehicleId) {
    if(vehicles.size() > 0) {
      for (int i = vehicles.size() - 1; i >= 0; i--) {
        Vehicle vehicle = vehicles.get(i);
        if(vehicle.getId() == vehicleId) {
          vehicles.remove(i);
          return true;
        }
      }
    }
    return false;
  }


  public Vehicle addVehicle(Vehicle vehicle) {
    if(vehicle.getId() == 0) {
      long maxId = 0;
      for (int i = vehicles.size() - 1; i >= 0; i--) {
        if(vehicles.get(i).getId() > maxId) {
          maxId = vehicles.get(i).getId();
        }
      }
      vehicle.setId(maxId + 1);
    }
    if (vehicles.size() > 0) {
      for (int i = vehicles.size() - 1; i >= 0; i--) {
        if (vehicles.get(i).getId() == vehicle.getId()) {
          vehicles.remove(i);
        }
      }
    }
    vehicles.add(vehicle);
    return vehicle;
  }


  static Vehicle createVehicle(long id, String name) {
    Vehicle vehicle = new Vehicle();
    vehicle.setId(id);
    vehicle.setName(name);
    return vehicle;
  }

}