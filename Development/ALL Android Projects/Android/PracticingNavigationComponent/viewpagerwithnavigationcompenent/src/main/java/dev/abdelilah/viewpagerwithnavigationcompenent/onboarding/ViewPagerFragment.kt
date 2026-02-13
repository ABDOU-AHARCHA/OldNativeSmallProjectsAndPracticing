package dev.abdelilah.viewpagerwithnavigationcompenent.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.viewpager2.widget.ViewPager2
import dev.abdelilah.viewpagerwithnavigationcompenent.R
import dev.abdelilah.viewpagerwithnavigationcompenent.onboarding.Screens.FirstScreen
import dev.abdelilah.viewpagerwithnavigationcompenent.onboarding.Screens.SecondScreen
import dev.abdelilah.viewpagerwithnavigationcompenent.onboarding.Screens.ThirdScreen

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewPagerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewPagerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_view_pager, container, false)
        var fragments = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(
            fragments,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        var viewpager = view.findViewById<ViewPager2>(R.id.viewpager)
        viewpager.adapter=adapter
        viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL


        return  view
    }


}