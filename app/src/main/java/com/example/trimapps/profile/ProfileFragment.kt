package com.example.trimapps.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.trimapps.LoginActivity
import com.example.trimapps.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private var _binding: ActivityProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var logoutButton: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val buttonLogout: Button = binding.keluar
        buttonLogout.setOnClickListener {
            // Logout pengguna saat tombol ditekan
            auth.signOut()

            // Arahkan pengguna ke Activity Profile (bukan LoginActivity)
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()

            // Anda bisa menggantikan R.id.fragment_container dengan ID container Fragment yang sesuai di Activity Anda.
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
