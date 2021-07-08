package com.example.mymvvmapplication.ui

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymvvmapplication.adapters.MyAdapter
import com.example.mymvvmapplication.databinding.ActivityMainBinding
import com.example.toastify.Toastify
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var adapter : MyAdapter
    private val viewModel : MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initAdapter()
        observeUIState()
        observeData()

    }


    private fun initAdapter() {
        adapter = MyAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }

    private fun observeUIState() {
        val progressDialog : ProgressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading....")

        viewModel.uiStateLiveData.observe(this, Observer {state ->
            when(state){
                UIState.LoadingState -> {
                   progressDialog.show()
                }

                UIState.DataState -> {
                    Toastify.showToast(this, Toastify.SUCCESS, "Data Loaded", Toast.LENGTH_SHORT)
                    progressDialog.hide()
                }

                UIState.ErrorState -> {
                    Toastify.showToast(this, Toastify.FAILED, "No Data Found", Toast.LENGTH_SHORT)
                    progressDialog.hide()
                }
            }

        })
    }


    private fun observeData() {
        viewModel.covidLiveData.observe(this, Observer { data ->
            try{
                adapter.submitList(data)
            } catch (e : Exception){
                e.stackTrace
            }
        })
    }
}