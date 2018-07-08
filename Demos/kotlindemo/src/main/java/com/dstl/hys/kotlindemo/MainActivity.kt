package com.dstl.hys.kotlindemo

import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.dstl.hys.kotlindemo.Adapters.KotlinAdapter
import kotlinx.android.synthetic.main.activity_main.*
import com.dstl.hys.kotlindemo.KotlinUtils.*
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class MainActivity : AppCompatActivity() {

    var adapter: KotlinAdapter = KotlinAdapter(R.layout.activity_main, ArrayList<String>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_text_1.text = "test"
        initDatas()
        sum@ sum(1, 2)
//        var intent = Intent(this,MainActivity::class.java)
//        startActivity(intent)
    }

    fun initDatas() {
        var user = User()
        user.name = "a"
        user.name = "b"
        var map = HashMap<String,String>()
        map.put("name","hys")
        map.put("age","21")
        var mapUser = MapUser(map)
    }

    fun sum(a1: Int, a2: Int) {
        Toast.makeText(this, a1 + a2, Toast.LENGTH_LONG).show()
    }

    public fun argSum(vararg a: Int) {
        var sum: Int = 0
        for (i in 0..a.size) {
            Toast.makeText(this, a[i], Toast.LENGTH_SHORT).show()
        }
    }

    private fun ImageView.load(url: String, options: RequestOptions) {
        getImage(url, options).into(this)
    }

    fun getImage(url: String, options: RequestOptions): RequestBuilder<Drawable> = Glide.with(this).load(url).apply(options)
}

class User {
    var name: String by Delegates.observable("default") { kProperty: KProperty<*>, oldValue: String, newValue: String ->
        println(oldValue + newValue)

    }
}

class MapUser(var map: HashMap<String, String>) {
    var name :String by map
    var age :String by map
}
