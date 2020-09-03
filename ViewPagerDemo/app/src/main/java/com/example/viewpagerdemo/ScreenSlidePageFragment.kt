package com.example.viewpagerdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [ScreenSlidePageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScreenSlidePageFragment : Fragment() {
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_screen_slide_page, container, false)
        inflate.findViewById<TextView>(R.id.textView).text = getString(R.string.hello_blank_fragment, param1)
        return inflate
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment ScreenSlidePageFragment.
         */
        @JvmStatic
        fun newInstance(param1: String) =
            ScreenSlidePageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}