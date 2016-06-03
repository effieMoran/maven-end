/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.helloworld;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.NotFoundException;


import com.google.appengine.api.datastore.*;
import com.googlecode.objectify.*;
import java.util.*;

import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;


// [START header]
/** An endpoint class we are exposing */
@Api(name = "myApi",
    version = "v3",
    namespace = @ApiNamespace(ownerDomain = "helloworld.example.com",
        ownerName = "helloworld.example.com",
        packagePath = ""))
// [END header]

public class YourFirstAPI {

    private  DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    private  Objectify objectify = OfyService.ofy();

    // [START get]
    @ApiMethod(
          name = "students.get",
          path = "student",
          httpMethod = ApiMethod.HttpMethod.GET
    )
    public Student getStudent(@Named("id") long id) throws NotFoundException {
          Student student = ofy().load().type(Student.class).id(id).now();
          if (student != null) {
              return student;
          }
          else{
              throw new NotFoundException("Could not find Student with File Number: " + id);
          }
    }
    // [END get]
    // [START post]
    @ApiMethod(
        name = "students.insert",
        path = "student",
        httpMethod = ApiMethod.HttpMethod.POST
    )
    public Student insertStudent(Student student){
        Student st = ofy().load().type(Student.class).id(student.getFileNumber()).now();
        if(st  == null) {
            ofy().save().entity(student).now();
            return ofy().load().type(Student.class).id(student.getFileNumber()).now();
        }
        else{
            return null;
        }
    }
    // [END post]

    // [START update]
    @ApiMethod(
        name = "students.update",
        path = "student",
        httpMethod = ApiMethod.HttpMethod.PUT
    )
    public Student updateStudent(Student student) throws  NotFoundException{
        Student st = ofy().load().type(Student.class).id(student.getFileNumber()).now();
        if(st.getFileNumber().equals(student.getFileNumber())) {
            ofy().save().entity(student).now();
            return ofy().load().type(Student.class).id(student.getFileNumber()).now();
        }
        else{
            throw new NotFoundException("Could not find Student with File Number: " + student.getFileNumber());
        }
    }
    // [END update]
    // [START delete]
    @ApiMethod(
        name = "students.delete",
        path = "student",
        httpMethod = ApiMethod.HttpMethod.DELETE
    )
    public Student deleteStudent(@Named("id") Long id ) throws NotFoundException{
        Student student = ofy().load().type(Student.class).id(id).now();
        if(student != null) {
            ofy().delete().type(Student.class).id(id).now();
            return student;
        }
        else{
            throw new NotFoundException("Could not find Student with File Number: " + id);
        }
    }
    // [END delete]
    @ApiMethod(
            name = "students.getall",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List getAllStudents(){
        return ofy().load().type(Student.class).list();
    }
}
