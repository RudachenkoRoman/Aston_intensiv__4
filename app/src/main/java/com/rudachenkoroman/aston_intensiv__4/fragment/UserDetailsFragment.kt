package com.rudachenkoroman.aston_intensiv__4.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.CircleCropTransformation
import com.rudachenkoroman.aston_intensiv__4.R
import com.rudachenkoroman.aston_intensiv__4.data.User
import com.rudachenkoroman.aston_intensiv__4.databinding.FragmentUserDetailsBinding
import com.rudachenkoroman.aston_intensiv__4.fragment.UserListFragment.Companion.USER_BUNDLE_KEY
import com.rudachenkoroman.aston_intensiv__4.parcelable

class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {

    private var user: User? = null
    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val user = it.parcelable<User>(USER_BUNDLE_KEY)
            if (user != null) {
                this.user = user
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editName.setText(user?.name)
        binding.editSurname.setText(user?.surname)
        binding.editPhoneNumber.setText(user?.phoneNumber)
        binding.userImage.load(user?.imageUri ?: R.drawable.baseline_perm_identity_24) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }

        binding.userImage.setOnClickListener {
            photoPickerLauncher.launch(
                PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    .build()
            )
        }

        binding.btnSave.setOnClickListener {
            val user = User(
                user!!.id,
                binding.editName.text.toString(),
                binding.editSurname.text.toString(),
                binding.editPhoneNumber.text.toString(),
                user!!.imageUri
            )

            parentFragmentManager.setFragmentResult(
                UserListFragment.FRAGMENT_RESULT_KEY_UPDATE_USER,
                bundleOf(USER_BUNDLE_KEY to user)
            )
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private val photoPickerLauncher =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let {
                user?.let {
                    user = it.copy(imageUri = uri)
                    arguments = bundleOf(USER_BUNDLE_KEY to user!!)
                }
                binding.userImage.load(user?.imageUri ?: R.drawable.baseline_perm_identity_24) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
            }
        }

    companion object {
        fun newInstance(user: User) = UserDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_BUNDLE_KEY, user)
            }
        }

        const val USER_DETAILS_FRAGMENT_TAG = "USER_DETAILS_FRAGMENT_TAG"
    }
}