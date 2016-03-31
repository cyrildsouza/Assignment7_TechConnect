/*
 * Copyright 2016 Len Payne <len.payne@lambtoncollege.ca>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package todo;


import java.io.StringReader;
import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Len Payne <len.payne@lambtoncollege.ca>
 */
@Path("todo")
@ApplicationScoped
public class TodoService {
    private TodoList todoList = new TodoList();
    
    @GET
    @Produces("application/json")    
    public JsonArray getAll() {
        JsonArrayBuilder json = Json.createArrayBuilder();
        for (String todo : todoList.getTodoList()) {
            json.add(todo);
        }
        return json.build();
    }
    
    @POST
    @Consumes("application/json")    
    @Produces("application/json")
    public JsonArray add(String str) {
        JsonObject json = Json.createReader(new StringReader(str)).readObject();
        // Expects { "item": "some todoList entry" }      
        todoList.add(json.getString("item"));
        return getAll();
    }
}
