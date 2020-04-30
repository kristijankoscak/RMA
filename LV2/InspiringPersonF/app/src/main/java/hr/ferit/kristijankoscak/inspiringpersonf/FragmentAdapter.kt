package hr.ferit.kristijankoscak.inspiringpersonf


import android.text.TextUtils
import android.view.ViewGroup
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

    override fun startUpdate(container: ViewGroup) {
        super.startUpdate(container)
        65465
    }
    override fun getCount(): Int {
        return fragments.size;
    }
}
