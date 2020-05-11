package hr.ferit.kristijankoscak.inspiringpersondb

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    val fragments = arrayOf(
        ShowPersonsFragment.newInstance(),
        AddPersonFragment.newInstance()
    )
    val titles = arrayOf("Persons", "Add")
    override fun getItem(position: Int): Fragment {
        return fragments[position] as Fragment
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
    override fun getCount(): Int {
        return fragments.size;
    }
}
