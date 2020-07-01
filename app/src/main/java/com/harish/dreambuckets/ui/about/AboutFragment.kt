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
import com.harish.dreambuckets.ui.activities.DashboardActivity
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element

class AboutFragment : Fragment() {


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
            binding.imgEmail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.about_icon_email, 0, 0, 0);
            binding.imgGithub.setCompoundDrawablesWithIntrinsicBounds(R.drawable.about_icon_github, 0, 0, 0);
            binding.imgInstagram.setCompoundDrawablesWithIntrinsicBounds(R.drawable.about_icon_instagram, 0, 0, 0);
        }





        binding.imgGithub.setOnClickListener {
            val gitIntent = Intent(Intent.ACTION_VIEW)
            gitIntent.data = Uri.parse("http://github.com/HarishVS12")
            startActivity(gitIntent)
        }

        binding.imgInstagram.setOnClickListener {
            val instaIntent = Intent(Intent.ACTION_VIEW)
            instaIntent.data = Uri.parse("https://www.instagram.com/_harish_vs_/")
            startActivity(instaIntent)
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

      /*  val gitElement = Element()
        gitElement.apply {
            iconDrawable = R.drawable.about_icon_github
            title = "Fork this app on Github"
            intent = gitIntent
        }

        val instaElement = Element()
        instaElement.apply {
            iconDrawable = R.drawable.about_icon_instagram
            title = "Follow me on Instagram"
            intent = instaIntent
        }

        val mailElement = Element()
        mailElement.apply {
            iconDrawable = R.drawable.about_icon_email
            title = "Contact me"
            intent = mailIntent
        }

        val aboutPage = AboutPage(requireContext())
        aboutPage.apply {
            isRTL(false)
            setDescription((getText(R.string.about_dev)))
            setImage(R.drawable.dev_pic)
            addGroup("Connect with me")
            addItem(mailElement)
            addItem(gitElement)
            addItem(instaElement)
        }*/

        return binding.root
    }


}