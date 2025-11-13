package com.harish.dreambuckets.ui.DashFragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.harish.dreambuckets.R
import com.harish.dreambuckets.databinding.FragmentAboutBinding
import com.harish.dreambuckets.ui.activities.DashboardActivity
import com.harish.dreambuckets.utilities.GIT_ACCOUNT
import com.harish.dreambuckets.utilities.INSTA_ACCOUNT


class AboutFragment : Fragment() {

    //This is the about fragment

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false)


        if(DashboardActivity.isNightMode){
            binding.imgEmail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_gmail_night, 0, 0, 0);
            binding.imgGithub.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_github_night, 0, 0, 0);
            binding.imgInstagram.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_instagram, 0, 0, 0);
        }else{
            binding.imgEmail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_about, 0, 0, 0);
            binding.imgGithub.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_about, 0, 0, 0);
            binding.imgInstagram.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_about, 0, 0, 0);
        }




        binding.imgGithub.setOnClickListener {
            intent(GIT_ACCOUNT)
        }

        binding.imgInstagram.setOnClickListener {
            intent(INSTA_ACCOUNT)
        }

        binding.imgEmail.setOnClickListener {
            val mailIntent = Intent().apply {
                setAction(Intent.ACTION_SENDTO)
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("harishthedev@gmail.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Regd Dream Buckets Application")
            }
            startActivity(mailIntent)
        }


        return binding.root
    }

    fun intent(str:String){
        val instaIntent = Intent(Intent.ACTION_VIEW)
        instaIntent.data = Uri.parse(str)
        startActivity(instaIntent)
    }


}