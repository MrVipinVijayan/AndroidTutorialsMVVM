package com.coderzheaven.tutorialprojects.view//package com.coderzheaven.tutorialprojects
//
//import android.os.Bundle
//import android.view.View
//import android.widget.Button
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers.IO
//import kotlinx.coroutines.Dispatchers.Main
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
//class MainActivity2 : AppCompatActivity() {
//
//    var mListRecyclerView: RecyclerView? = null
//    var mAdapter: ListAdapter? = null
//    lateinit var title: TextView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        title = findViewById(R.id.title)
//        initUI()
//        CoroutineScope(IO).launch {
//            fakeApiRequest()
//        }
//
//    }
//
//    private suspend fun fakeApiRequest() {
//        var result1 = getResult1FromApi()
//        print(result1)
//        CoroutineScope(Main).launch {
//            title.append(result1)
//        }
//        var result2 = getResult2FromApi()
//        print(result2)
//        CoroutineScope(Main).launch {
//            title.append(result2)
//        }
//    }
//
//    private suspend fun getResult1FromApi(): String {
//        delay(2000)
//        return "Result 1"
//    }
//
//    private suspend fun getResult2FromApi(): String {
//        delay(2000)
//        return "Result 2"
//    }
//
//    private fun logThread(methodName: String) {
//        println("debug $methodName : ${Thread.currentThread()}")
//    }
//
//    private fun initUI() {
//        val viewModel = ViewModelProvider(this).get(NamesViewModel::class.java)
//        viewModel.names.observe(this, Observer { names ->
//            setList(names = names)
//        })
//        findViewById<Button>(R.id.btnAddNote).setOnClickListener(View.OnClickListener {
//            viewModel.addPerson("New Name")
//        })
//    }
//
//    private fun setList(names: List<String>) {
//        mListRecyclerView = findViewById(R.id.list_recycler_view)
//        mListRecyclerView?.apply {
//            layoutManager = LinearLayoutManager(this@MainActivity2)
//            if (null == mAdapter) {
//                mAdapter = ListAdapter(names)
//                adapter = mAdapter
//            } else {
//                mAdapter?.notifyDataSetChanged()
//            }
//        }
//    }
//}