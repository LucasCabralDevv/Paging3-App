package com.lucascabral.paging3app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lucascabral.paging3app.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var bindingDetails: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDetails = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(bindingDetails.root)

        val (name: String?, image: String?, species: String?) = getDataExtra()

        setupViews(name, species, image)
    }

    private fun setupViews(name: String?, species: String?, image: String?) {
        bindingDetails.apply {
            nameExtras.text = name
            speciesExtras.text = species
            imageExtras.text = image
        }
    }

    private fun getDataExtra(): Triple<String?, String?, String?> {
        val name: String? = intent.getStringExtra("name")
        val image: String? = intent.getStringExtra("image")
        val species: String? = intent.getStringExtra("species")
        return Triple(name, image, species)
    }
}