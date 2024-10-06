package com.example.moonproject.HomeFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moonproject.Models.KontakModel
import com.example.moonproject.R
import com.example.moonproject.adapters.KontakAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class KontakFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_kontak, container, false)

        // Access the RecyclerView using the inflated view
        val listKontak: RecyclerView = view.findViewById(R.id.list_kontak)
        listKontak.layoutManager = LinearLayoutManager(requireContext())

        // Sample data
        val mahasiswaList = listOf(
            KontakModel("Alice", "081238384852"),  // Pastikan parameter sesuai
            KontakModel("Bob", "081238384823"),
            KontakModel("Charlie", "081238323852")
        )

        // Set adapter
        val adapter = KontakAdapter(mahasiswaList)
        listKontak.adapter = adapter

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            KontakFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
