package com.enzoftware.randomuser.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.enzoftware.randomuser.R
import com.enzoftware.randomuser.databinding.ActivityMainBinding
import com.enzoftware.randomuser.domain.model.RandomUser
import com.enzoftware.randomuser.utils.UIViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RandomUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: RandomUserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.fetchRandomUser()
        initUI()
    }

    private fun initUI() {
        binding.randomUserTryAgainButton.setOnClickListener {
            viewModel.fetchRandomUser()
        }
        viewModel.uiViewStateObservable.observe(this, {
            when (it) {
                is UIViewState.Success -> {
                    val randomUser = it.result as RandomUser
                    binding.randomUserProgressBar.visibility = View.GONE
                    binding.randomUserErrorTextView.visibility = View.GONE
                    binding.randomUserResultCard.visibility = View.VISIBLE
                    showRandomUser(randomUser)
                }
                is UIViewState.Error -> {
                    binding.randomUserProgressBar.visibility = View.GONE
                    binding.randomUserResultCard.visibility = View.GONE
                    binding.randomUserErrorTextView.visibility = View.VISIBLE
                    binding.randomUserErrorTextView.text = it.message

                }
                is UIViewState.Loading -> {
                    binding.randomUserResultCard.visibility = View.GONE
                    binding.randomUserErrorTextView.visibility = View.GONE
                    binding.randomUserProgressBar.visibility = View.VISIBLE
                }
            }
        })
    }


    private fun showRandomUser(randomUser: RandomUser) {
        Glide
            .with(this)
            .load(randomUser.picture.medium)
            .centerCrop()
            .placeholder(R.drawable.profile)
            .into(binding.randomUserProfilePicture)
        binding.randomUserFullNameText.text = "${randomUser.name.title} ${randomUser.name.first} ${randomUser.name.last}"
        binding.randomUserEmailText.text = randomUser.email
        binding.randomUserPhoneText.text = randomUser.phone
        binding.randomUserAgeText.text = randomUser.dateOfBirthday.age.toString()
        binding.randomUserAddressText.text = "${randomUser.location.street.name} ${randomUser.location.street.number}, ${randomUser.location.city}, ${randomUser.location.country}"
        binding.randomUserPasswordText.text = randomUser.loginInformation.password

    }
}