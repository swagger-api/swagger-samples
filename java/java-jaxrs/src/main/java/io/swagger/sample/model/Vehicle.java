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

package io.swagger.sample.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Vehicle")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME, // Were binding by providing a name
        include = JsonTypeInfo.As.PROPERTY, // The name is provided in a property
        property = "type", // Property name is type
        visible = true // Retain the value of type after deserialisation
)
@JsonSubTypes({//Below, we define the names and the binding classes.
        @JsonSubTypes.Type(value = Car.class, name = "Car"),
})

public class Vehicle {
  private long id;
  private String name;

  @XmlElement(name = "id")
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @XmlElement(name = "name")
  @ApiModelProperty(example = "kit", required = true)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  private String type;

  @ApiModelProperty(required = true)
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
