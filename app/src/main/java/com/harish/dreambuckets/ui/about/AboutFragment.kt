package com.harish.dreambuckets.ui.about

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.harish.dreambuckets.R
import com.harish.dreambuckets.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {


    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_about,container,false)

        binding.imgGithub.setOnClickListener{
           val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://github.com/HarishVS12")
            startActivity(intent)
        }

        binding.imgInstagram.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.instagram.com/_harish_vs_/")
            startActivity(intent)
        }

        binding.imgGmail.setOnClickListener {
            val intent = Intent().apply {
                setAction(Intent.ACTION_SENDTO)
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("harishthedev@gmail.com"))
                putExtra(Intent.EXTRA_SUBJECT,"Regd Dream Buckets Application")
            }
            startActivity(intent)
        }

        return binding.root
    }


}