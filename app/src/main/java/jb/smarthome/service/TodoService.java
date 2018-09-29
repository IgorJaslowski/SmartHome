package jb.smarthome.service;

import java.util.List;

import jb.smarthome.Todo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TodoService {
    @GET("/todos")
    Call<List<Todo>> getAllTodos();
}
