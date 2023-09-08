package com.example.githubpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubpractice.adapter.UserAdapter
import com.example.githubpractice.databinding.ActivityMainBinding
import com.example.githubpractice.model.Repo
import com.example.githubpractice.model.UserDto
import com.example.githubpractice.network.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val githubService = retrofit.create(GithubService::class.java)
        githubService.listRepos("square").enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                Log.d("tag", response.body().toString())
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {

            }

        })


        val userAdapter = UserAdapter()
        binding.userRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter

        }


        githubService.searchUsers("squar").enqueue(object : Callback<UserDto> {
            override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                Log.d("tag2", response.body().toString())
                userAdapter.submitList(response.body()?.items)


            }

            override fun onFailure(call: Call<UserDto>, t: Throwable) {

            }

        })


    }
}