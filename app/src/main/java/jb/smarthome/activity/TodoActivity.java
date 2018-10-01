package jb.smarthome.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import jb.smarthome.R;
import jb.smarthome.RetrofitClientInstance;
import jb.smarthome.Todo;
import jb.smarthome.adapter.TodoAdapter;
import jb.smarthome.service.TodoService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoActivity extends AppCompatActivity {
    private TodoAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        progressDoalog = new ProgressDialog(TodoActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        TodoService service = RetrofitClientInstance.getRetrofitInstance().create(TodoService.class);
        Call<List<Todo>> call = service.getAllTodos();
        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(TodoActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Todo> photoList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new TodoAdapter(this,photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TodoActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
