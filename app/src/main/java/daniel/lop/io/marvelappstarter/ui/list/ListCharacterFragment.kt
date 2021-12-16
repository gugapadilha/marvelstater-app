package daniel.lop.io.marvelappstarter.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import daniel.lop.io.marvelappstarter.databinding.FragmentListCharacterBinding
import daniel.lop.io.marvelappstarter.ui.base.BaseFragment

class ListCharacterFragment: BaseFragment<FragmentListCharacterBinding, ListCharacterViewModel>() {
    override val viewModel: ListCharacterViewModel by viewModels()


    override fun getViewBlinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListCharacterBinding
    = FragmentListCharacterBinding.inflate(inflater, container, false)
}